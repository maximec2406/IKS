package ru.abr.dit.Services.SOAP.UPGDocumentBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.Entities.Account;
import ru.abr.dit.Models.Entities.Org;
import ru.abr.dit.Models.XMLTemplate.StatementRequest.*;
import ru.abr.dit.Services.LogService;
import ru.abr.dit.Services.SOAP.ABRSoapEnvelope;
import ru.abr.dit.Services.SOAP.ABRSoapMessage;
import ru.abr.dit.Services.SOAP.UPGRequest.SendRequestService;
import ru.abr.dit.Services.XMLGregorianCalendarConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class StatementRequestBody {

    @Autowired
    XMLGregorianCalendarConverter xmlGregorianCalendarConverter;

    @Autowired
    SimpleDateFormat simpleDateFormat;

    @Autowired
    MainDAO mainDAO;

    @Autowired
    ABRSoapMessage abrSoapMessage;

    @Autowired
    ABRSoapEnvelope abrSoapEnvelope;

    @Autowired
    LogService ls;

    @Autowired
    SendRequestService sendRequestService;

    private final static QName _StatementRequest_QNAME = new QName("http://bssys.com/sbns/integration", "StatementRequest");

    public void sendStatementRequest(int accoutId, String dateFrom, String dateTo) throws ParseException, JAXBException, SOAPException, IOException {

        sendRequestService.sendRequest(createStatementRequest(accoutId, dateFrom, dateTo));

    }

    protected String createStatementRequest(int accoutId, String dateFrom, String dateTo) throws ParseException, JAXBException {

        Account account = mainDAO.findAccountById(accoutId);
        Org org = account.getOrg();


        ObjectFactory of = new ObjectFactory();

        StatementRequest statementRequest = of.createStatementRequest();

        statementRequest.setDocDate(xmlGregorianCalendarConverter.asXMLGregorianCalendar(new Date()));
        statementRequest.setDocNumber("1");
        statementRequest.setFromDate(xmlGregorianCalendarConverter.asXMLGregorianCalendar(simpleDateFormat.parse(dateFrom)));
        statementRequest.setToDate(xmlGregorianCalendarConverter.asXMLGregorianCalendar(simpleDateFormat.parse(dateTo)));
        statementRequest.setOrgId(account.getOrg().getExtId());
        statementRequest.setOrgInn(account.getOrg().getInn());
        statementRequest.setOrgName(account.getOrg().getName());
        statementRequest.setDocId(UUID.randomUUID().toString());

        StatementRequest.Accounts accounts = of.createStatementRequestAccounts();

        StatementRequest.Accounts.Acc acc = of.createStatementRequestAccountsAcc();
        acc.setAccount(account.getAccount());
        acc.setBankBIC(String.valueOf(account.getBic()));
        acc.setOrgName(account.getOrg().getName());
        acc.setBankName(account.getBranch().getExtId());

        accounts.getAcc().add(acc);

        statementRequest.setAccounts(accounts);

        JAXBElement<StatementRequest> eStatementRequest = new JAXBElement<StatementRequest>(_StatementRequest_QNAME, StatementRequest.class, null, statementRequest);
        StringWriter sw = new StringWriter();
        JAXBContext.newInstance(StatementRequest.class).createMarshaller().marshal(eStatementRequest, sw);


        return sw.toString().substring(sw.toString().indexOf("<StatementRequest"));

    }

//    public String createFullRequestBody(String bodyPart, String requestId){
//
//         return "<upg:Request xmlns:upg=\"http://bssys.com/upg/request\" xmlns:upgRaif=\"http://bssys.com/upg/request/raif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" requestId=\"" + requestId + "\" version=\"1\">" +
//                "<upg:Models><upg:Model>" + bodyPart + "</upg:Model></upg:Models></upg:Request>";
//
//    }

}
