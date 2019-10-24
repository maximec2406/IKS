package ru.abr.dit.Services.SOAP.UPGRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.DAO.UPGDAO;
import ru.abr.dit.Models.Entities.UPGRequest;
import ru.abr.dit.Models.Entities.UPGSession;
import ru.abr.dit.Models.XMLTemplate.Model.RequestModelsModel;
import ru.abr.dit.Services.LogService;
import ru.abr.dit.Services.SOAP.ABRSoapConnection;
import ru.abr.dit.Services.SOAP.ABRSoapEnvelope;
import ru.abr.dit.Services.SOAP.ABRSoapMessage;
import ru.abr.dit.Services.SOAP.SessionService;
import ru.abr.dit.Services.SOAP.UPGDocumentBody.StatementRequestBody;
import ru.abr.dit.Services.UPGNamespaceMapper;
import ru.abr.dit.web.clietn.ObjectFactory;
import ru.abr.dit.web.clietn.SendRequests;
import javax.xml.bind.*;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;


@Service
public class SendRequestService {

    @Autowired
    ABRSoapMessage abrSoapMessage;

    @Autowired
    ABRSoapEnvelope abrSoapEnvelope;

    @Autowired
    SessionService sessionService;

    @Autowired
    MainDAO mainDAO;

    @Autowired
    UPGDAO upgdao;

    @Autowired
    StatementRequestBody upgStatementRequest;

    @Autowired
    ABRSoapConnection abrSoapConnection;

    @Autowired
    LogService ls;


//    private final static QName _RequestModelsModel_QNAME = new QName("http://bssys.com/upg/request", "Request");

    public void sendRequest(String requestBody) throws JAXBException, SOAPException, IOException {


        // Сохранение сессий, тела запроса и подготовка полного тела сообщения
        UPGSession session = new UPGSession(sessionService.getSessionId(), new Date(), upgdao.getLogin());
        mainDAO.saveSession(session);

        UPGRequest request = new UPGRequest(requestBody, "StatementRequest");
        request.setSession(session);
        mainDAO.saveRequest(request);

        RequestModelsModel requestModelsModel = new RequestModelsModel();
        requestModelsModel.setRequestId(request.getId().toString());
        requestModelsModel.setModels(new RequestModelsModel.Models(requestBody));


//        JAXBElement<RequestModelsModel> eRequestModelsModel = new JAXBElement<RequestModelsModel>(_RequestModelsModel_QNAME, RequestModelsModel.class, null, requestModelsModel);
//        eRequestModelsModel.getScope();
        StringWriter sw = new StringWriter();
        Marshaller m = JAXBContext.newInstance(RequestModelsModel.class).createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try {
            m.setProperty("com.sun.xml.bind.namespacePrefixMapper", new UPGNamespaceMapper());
            //m.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespaceMapper());
        } catch(PropertyException e) {
            // In case another JAXB implementation is used
        }
        m.marshal(requestModelsModel, sw);
        System.out.println(sw.toString());

//        String fullRequestBody = upgStatementRequest.createFullRequestBody(requestBody, request.getId().toString());


        // создание запроса для отправки в ИКШ

        SOAPMessage soapMessage = abrSoapMessage.createSOAPMessage();

        SOAPMessage soapResp;

        SOAPEnvelope envelope = abrSoapEnvelope.createSoapEnvelope(soapMessage);

        ObjectFactory ofweb = new ObjectFactory();

        SendRequests sendRequests = ofweb.createSendRequests();

        sendRequests.getRequests().add(sw.toString().substring(sw.toString().indexOf("<upg:Request")));

        sendRequests.setSessionId(session.getExtId());

        JAXBElement<SendRequests> eSendRequests = ofweb.createSendRequests(sendRequests);

        JAXBContext.newInstance(SendRequests.class).createMarshaller().marshal(eSendRequests, envelope.getBody());
        soapMessage.saveChanges();
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        try {
            soapResp = abrSoapConnection.createSOAPConnection().call(soapMessage, upgdao.getSourceURI());
        } catch (SOAPException e){
            ls.addErrorLog(e, "Ошибка отправки запроса Prelogin. Возможно сервис недоступен");
            soapResp = null;
        }

        // если сервис доступен
        if (soapResp != null){

            soapResp.writeTo(System.out);

//            // Разбор ответа login начало
//            NodeList nl = soapResp.getSOAPBody().getElementsByTagName("return");
//            if (nl.getLength() == 1) {
//                result = nl.item(1).getTextContent();
//            } else {
//                result = "";
//            }
//             Разбор ответа login конец
        }

//        Запись запроса в лог
        //soapMessage.writeTo(System.out);


    }
}
