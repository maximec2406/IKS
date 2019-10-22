package ru.abr.dit.Services.SOAP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.abr.dit.Services.LogService;


import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;

@Service
@PropertySource("classpath:corrconn.properties")
public class ABRSoapConnection {

    @Autowired
    LogService logService;

    @Autowired
    SOAPConnectionFactory soapConnectionFactory;

    public SOAPConnection createSOAPConnection(){

       SOAPConnection soapConnection = null;

        try {
             soapConnection = soapConnectionFactory.createConnection();
        } catch (SOAPException e) {
            logService.addErrorLog(e,"Не удалось создать SOAPConnection");
        }
        return soapConnection;
    }

}
