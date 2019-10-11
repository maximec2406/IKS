package ru.abr.dit.Services.SOAP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abr.dit.Services.LogService;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

@Service
public class ABRSoapMessage {

    @Autowired
    MessageFactory messageFactory;

    @Autowired
    LogService logService;

    public SOAPMessage createSOAPMessage() {

        SOAPMessage soapMessage = null;

        try {
            soapMessage = messageFactory.createMessage();
        } catch (SOAPException e) {
            logService.addErrorLog(e,"Не удалось создать SOAPConnection");
        }

        return soapMessage;
    }
}
