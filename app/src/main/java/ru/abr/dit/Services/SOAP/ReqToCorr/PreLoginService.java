package ru.abr.dit.Services.SOAP.ReqToCorr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;
import ru.abr.dit.DAO.UPGDAO;
import ru.abr.dit.Services.LogService;
import ru.abr.dit.Services.SOAP.ABRSoapConnection;
import ru.abr.dit.Services.SOAP.ABRSoapEnvelope;
import ru.abr.dit.Services.SOAP.ABRSoapMessage;
import ru.abr.dit.web.clietn.ObjectFactory;
import ru.abr.dit.web.clietn.PreLogin;

import javax.xml.bind.*;
import javax.xml.soap.*;
import java.io.IOException;

@Service
public class PreLoginService {

    @Autowired
    ABRSoapConnection abrSoapConnection;

    @Autowired
    ABRSoapMessage abrSoapMessage;

    @Autowired
    ABRSoapEnvelope abrSoapEnvelope;

    @Autowired
    LogService ls;

    @Autowired
    UPGDAO upgdao;

    @Autowired
    LoginService loginService;

    public String sendPreLoginRequest() {

        String result = null;

        SOAPMessage soapMessage = abrSoapMessage.createSOAPMessage();

        SOAPMessage soapResp;

        SOAPEnvelope envelope = abrSoapEnvelope.createSoapEnvelope(soapMessage);


        ObjectFactory of = new ObjectFactory();
        PreLogin preLogin = of.createPreLogin();
        preLogin.setUserLogin(upgdao.getLogin());
        JAXBElement ePreLogin = of.createPreLogin(preLogin);


        try {
            JAXBContext.newInstance(PreLogin.class).createMarshaller().marshal(ePreLogin, envelope.getBody());
            soapMessage.saveChanges();
            //soapMessage.writeTo(System.out);


            // проверяем, доступен ли сервис
            try {
                soapResp = abrSoapConnection.createSOAPConnection().call(soapMessage, upgdao.getSourceURI());
            } catch (SOAPException e){
                ls.addErrorLog(e, "Ошибка отправки запроса Prelogin. Возможно сервис недоступен");
                soapResp = null;
            }

            // если сервис доступен и вернул данные для запроса Login
            if (soapResp != null){

                soapResp.writeTo(System.out);
                // Разбор ответа prelogin начало
                NodeList nl = soapResp.getSOAPBody().getElementsByTagName("return");
                if (nl.getLength() == 5){
                    loginService.sendLoginRequest(nl.item(0).getTextContent(),
                            nl.item(1).getTextContent(),
                            nl.item(3).getTextContent(),
                            nl.item(4).getTextContent());
                } else {
                    ls.addWarnLog("Сервер ИКШ вернул не 5 значений на запрос PreLogin");
                }

                // Разбор ответа prelogin конец
            }
        } catch (SOAPException e) {
            ls.addErrorLog(e, "Не удалось создать soapBody. Сервис Prelogin");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }






}
