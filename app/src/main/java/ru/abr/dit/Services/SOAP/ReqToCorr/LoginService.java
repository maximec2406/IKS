package ru.abr.dit.Services.SOAP.ReqToCorr;

import com.bssys.srp.Engine;
import com.bssys.srp.Return;
import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;
import ru.abr.dit.DAO.UPGDAO;
import ru.abr.dit.Services.LogService;
import ru.abr.dit.Services.SOAP.ABRSoapConnection;
import ru.abr.dit.Services.SOAP.ABRSoapEnvelope;
import ru.abr.dit.Services.SOAP.ABRSoapMessage;
import ru.abr.dit.web.clietn.Login;
import ru.abr.dit.web.clietn.ObjectFactory;

import javax.script.ScriptException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.util.Base64;

@Service
public class LoginService {

    @Autowired
    ABRSoapMessage abrSoapMessage;

    @Autowired
    ABRSoapEnvelope abrSoapEnvelope;

    @Autowired
    UPGDAO upgdao;

    @Autowired
    ABRSoapConnection abrSoapConnection;

    @Autowired
    LogService ls;

    public String sendLoginRequest(String salt,     // соль
                                   String bfs,      // BytesFromServer, верификатор пароля
                                   String pLB64,    // preloginIdBase64 - идентификатор запроса prelogin
                                   String userHash  // хеш идентификатор логина
    ) {

        String result = null;

        SOAPMessage soapMessage = abrSoapMessage.createSOAPMessage();

        SOAPMessage soapResp;

        SOAPEnvelope envelope = abrSoapEnvelope.createSoapEnvelope(soapMessage);

        Return ret = null;
        try {
            ret = Engine.eval( new String(Base64.getDecoder().decode(userHash)),
                    new String(upgdao.getPass().getBytes()),
                    new String(salt.getBytes()),
                    new String(bfs.getBytes()), Boolean.valueOf("true"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        // Готовим параметры для LoginRequest конец

        ObjectFactory of = new ObjectFactory();
        Login login = of.createLogin();
        login.setUserLogin(upgdao.getLogin());
        login.setPreloginId(new String(Base64.getDecoder().decode(pLB64)));

        // тут дополнительно раскодируем из Base64 параметры, т.к. при создании JAXBElement массив байт будет собран в строку и еще раз закодирован в base64
        // если предварительно не раскодировать, получится двойная кодировка строк в base64
        login.getClientAuthData().add(new String(Base64.getDecoder().decode(ret.getPasswordHash())).getBytes());
        login.getClientAuthData().add(new String(Base64.getDecoder().decode(ret.getExtPasswordData())).getBytes());
        JAXBElement eLogin = of.createLogin(login);



        try {
            JAXBContext.newInstance(Login.class).createMarshaller().marshal(eLogin, envelope.getBody());
            soapMessage.saveChanges();
            soapMessage.writeTo(System.out);

            // проверяем, доступен ли сервис
            try {
                soapResp = abrSoapConnection.createSOAPConnection().call(soapMessage, upgdao.getSourceURI());
            } catch (SOAPException e){
                ls.addErrorLog(e, "Ошибка отправки запроса Prelogin. Возможно сервис недоступен");
                soapResp = null;
            }

            // если сервис доступен
            if (soapResp != null){

                soapResp.writeTo(System.out);

                // Разбор ответа login начало
                NodeList nl = soapResp.getSOAPBody().getElementsByTagName("return");
                if (nl.getLength() == 1) {
                    result = nl.item(1).getTextContent();
                } else {
                    result = "";
                }


                // Разбор ответа login конец
            }


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }
}
