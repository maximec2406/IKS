package ru.abr.dit.Services.SOAP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abr.dit.DAO.UPGDAO;
import ru.abr.dit.Services.LogService;

import javax.xml.soap.*;

@Service
public class ABRSoapEnvelope {

    @Autowired
    ABRSoapConnection abrSoapConnection;

    @Autowired
    LogService ls;

    @Autowired
    UPGDAO upgdao;


    public SOAPEnvelope createSoapEnvelope(SOAPMessage soapMessage){

        SOAPPart soapPart = soapMessage.getSOAPPart();

        SOAPEnvelope soapEnvelope = null;

        try {
            soapEnvelope = soapPart.getEnvelope();
            soapEnvelope.addNamespaceDeclaration(upgdao.getNamespace(), upgdao.getNamespaceURI());

        } catch (SOAPException e) {
            ls.addErrorLog(e, "Ошибка при создании SoapEnvelope. Класс ABRSoapEnvelope");
        }

        return soapEnvelope;

    }
}
