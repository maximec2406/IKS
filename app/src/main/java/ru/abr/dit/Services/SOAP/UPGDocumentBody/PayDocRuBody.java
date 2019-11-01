package ru.abr.dit.Services.SOAP.UPGDocumentBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.Entities.Bank;
import ru.abr.dit.Models.Entities.Docs.RPayOrder;
import ru.abr.dit.Models.Entities.Org;
import ru.abr.dit.Models.XMLTemplate.PayDocRu.ObjectFactory;
import ru.abr.dit.Models.XMLTemplate.PayDocRu.PayDocRu;
import ru.abr.dit.Services.SOAP.UPGRequest.SendRequestService;
import ru.abr.dit.Services.XMLGregorianCalendarConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Service
public class PayDocRuBody {

    @Autowired
    XMLGregorianCalendarConverter xmlGregorianCalendarConverter;

    @Autowired
    SendRequestService sendRequestService;

    @Autowired
    MainDAO dao;

    private ObjectFactory of = new ObjectFactory();
    private final static QName _PayDocRu_QNAME = new QName("http://bssys.com/sbns/integration", "PayDocRu");

    public void sendPayDocRu(RPayOrder rpo) throws ParseException, JAXBException, SOAPException, IOException {

        //sendRequestService.sendRequest(createPayDocRu(rpo));
        //createPayDocRu(rpo);
    }

    public UUID createPayDocRu(RPayOrder rpo){

        Org o = dao.findOrgById(Integer.valueOf(rpo.getOrgId()));
        Bank payerBank = dao.getBankByBic(rpo.getPayerBankBic());
        Bank receiverBank = dao.getBankByBic(rpo.getPayerBankBic());

        rpo.setAccountId(dao.getAccIdByNumber(rpo.getPayerAccount()));
        rpo.setNotifyByEmail(false);
        rpo.setNotifyBySMS(false);
        rpo.setPayerBank(payerBank);
        rpo.setReceiverBank(receiverBank);

        return dao.addRPayOrder(rpo);

    }

    public String createPayDocRu2(RPayOrder rpo) throws JAXBException, IOException {

        PayDocRu payDocRu = of.createPayDocRu();
        payDocRu.setMbaDoc(rpo.isMbaDoc());//false);
        payDocRu.setBudgetaryPayment(rpo.isBudgetaryPayment() );//false);
        payDocRu.setAccountId(rpo.getAccountId());
        payDocRu.setNotifyByEmail(rpo.isNotifyByEmail() );
        payDocRu.setNotifyBySMS(rpo.isNotifyBySMS());
        payDocRu.setCbc(rpo.getCbc());
        payDocRu.setChargeType(rpo.getChargeType());
//        payDocRu.setCommission(new BigDecimal(0));
//        payDocRu.setCreditContract("");
//        payDocRu.setCurrencyOperationType("01"); //код вида валютной операции
        payDocRu.setDocDate(xmlGregorianCalendarConverter.asXMLGregorianCalendar(rpo.getDocDate()));
        payDocRu.setDocDateDay(rpo.getDocDateDay());
        payDocRu.setDocDateMonth(rpo.getDocDateMonth());
        payDocRu.setDocDateYear(rpo.getDocDateYear());
        payDocRu.setDocId(rpo.getId().toString());
        payDocRu.setDocNumber(rpo.getDocNumber());
        payDocRu.setDocumentSum(new BigDecimal(rpo.getDocumentSum()));
//        payDocRu.setDrawerStatus("");// показатель статуса
//        payDocRu.setExternalId(payDocRu.getDocId());
//        payDocRu.setForAccept(false);
        payDocRu.setOcato(rpo.getOcato());
        payDocRu.setOperationType(rpo.getOperationType()); // вид операции
        payDocRu.setOrgId(dao.findOrgById(Integer.valueOf(rpo.getOrgId())).getExtId());
        payDocRu.setOrgName(rpo.getOrgName());//rpo.getOrgName());
        payDocRu.setPayReason(rpo.getPayReason()); // основание платежа (бюдж)
        payDocRu.setPayerAccount(rpo.getPayerAccount());
        payDocRu.setPayerBankBic(rpo.getPayerBank().getBic());
        payDocRu.setPayerBankCity(rpo.getPayerBank().getCity());
        payDocRu.setPayerBankSettlementType(rpo.getPayerBank().getSettlementType());
        payDocRu.setPayerBankCorrAccount(rpo.getPayerBank().getCorrAccount());
        payDocRu.setPayerBankName(rpo.getPayerBank().getName());
        payDocRu.setPayerINN(rpo.getPayerINN());
        payDocRu.setPayerKPP(rpo.getPayerKPP());
        payDocRu.setPayerName(rpo.getPayerName());//rpo.getPayerName());
        payDocRu.setPaymentCode(rpo.getPaymentCode()); // код вида платежа
        payDocRu.setPaymentKind(rpo.getPaymentKind());
        payDocRu.setPaymentPriority(rpo.getPaymentPriority()); // очередность платежа
        payDocRu.setPaymentPurpose(rpo.getPaymentPurpose()); // назначение платежа
        payDocRu.setReceiverAccount(rpo.getReceiverAccount());
        payDocRu.setReceiverBankBic(rpo.getReceiverBankBic());
        payDocRu.setReceiverBankCity(rpo.getReceiverBank().getCity());
        payDocRu.setReceiverBankSettlementType(rpo.getReceiverBank().getSettlementType());
        payDocRu.setReceiverBankCorrAccount(rpo.getReceiverBank().getCorrAccount());
        payDocRu.setReceiverBankName(rpo.getReceiverBank().getName());
        payDocRu.setReceiverINN(rpo.getReceiverINN());
        payDocRu.setReceiverKPP(rpo.getReceiverKPP());
        payDocRu.setReceiverName(rpo.getReceiverName());
        payDocRu.setTaxDocNumber(rpo.getTaxDocNumber()); // номер налогового документа
        payDocRu.setTaxOrCustoms(rpo.getTaxOrCustoms());
        payDocRu.setTaxPeriodDay(rpo.getTaxPeriodDay());
        payDocRu.setTaxPeriodMonth(rpo.getTaxPeriodMonth());
        payDocRu.setTaxPeriodYear(rpo.getTaxPeriodYear());
        payDocRu.setUip(rpo.getUip());
        payDocRu.setUrgent(false); // срочность
        payDocRu.setVatCalculationRule(rpo.getVatCalculationRule()); // способ расчета ндс
        payDocRu.setVatRate(new BigDecimal(rpo.getVatRate())); // сумма ндс
        payDocRu.setVatSum(new BigDecimal(rpo.getVatSum()));

//       PayDocRu.PayDocRuSignCollection payDocRuSignCollection = of.createPayDocRuSignCollection();
//       payDocRu.setSignCollection(payDocRuSignCollection);

        PayDocRu.PayDocRuSignCollection payDocRuSignCollection = createPayDocRuSignCollection(of, rpo);
        payDocRu.setSignCollection(payDocRuSignCollection);

        JAXBElement<PayDocRu> ePayDocRu = new JAXBElement<PayDocRu>(_PayDocRu_QNAME, PayDocRu.class, null, payDocRu);
        StringWriter sw = new StringWriter();
        JAXBContext.newInstance(PayDocRu.class).createMarshaller().marshal(ePayDocRu, sw);

        System.out.println(sw.toString());

        return sw.toString().substring(sw.toString().indexOf("<PayDocRu"));
    }

    private PayDocRu.PayDocRuSignCollection createPayDocRuSignCollection(ObjectFactory of, RPayOrder rpo){

        PayDocRu.PayDocRuSignCollection payDocRuSignCollection = of.createPayDocRuSignCollection();
        PayDocRu.PayDocRuSignCollection.pdrSignCollection pdrSignCollection = of.createPayDocRuSignCollectionSignCollection();
        PayDocRu.PayDocRuSignCollection.pdrSignCollection.Signs signs = of.createPayDocRuSignCollectionSignCollectionSigns();
        PayDocRu.PayDocRuSignCollection.pdrSignCollection.Signs.pdrSign pdrSign = of.createPayDocRuSignCollectionSignCollectionSignsSign();


        pdrSign.setCertificateGuid("468dd0db-60b3-429c-895b-6f4dacce9063");
        pdrSign.setContent(rpo.getSignCollection().replaceAll("\n",""));
        //pdrSign.setContentLarge(rpo.getSignCollection());
        pdrSign.setDigestScheme("com.bssys.sbns.dbo.rur.payment.R030SignDigest");
        pdrSign.setUserName("test9");
        pdrSign.setSignType("SINGLE");
        pdrSign.setSignerFullName("Образцов Михаил Юрьевич");
        pdrSign.setSafeTouchAutoSign(false);
        pdrSign.setDigestSchemeVersion(14);
        //pdrSign.setSignHash();
        pdrSign.setDigestSchemeFormat("");
        pdrSign.setDigestSchemeVersion(1);
        pdrSign.setDtCreate(xmlGregorianCalendarConverter.asXMLGregorianCalendar(new Date()));
        pdrSign.setOrgId("0ce353c5-9a53-497d-ad02-df1fb6c37feb");
        pdrSign.setOrgName("АО \"РЗК\"");
        pdrSign.setSignAuthorityId("2a67e143-5bad-4beb-95cc-dbf7f197b714");
        payDocRuSignCollection.getSignCollection();

        signs.getSign().add(pdrSign);
        pdrSignCollection.setSigns(signs);
        payDocRuSignCollection.setSignCollection(pdrSignCollection);

        return payDocRuSignCollection;
    }

    private String createDigest(PayDocRu payDocRu) throws IOException {

        String result = null;

        File file = new File("C:\\Temp", "Digest.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write("[Платежное поручение]\n");
        fileWriter.write("Номер документа=" + payDocRu.getDocNumber() + "\n");
        fileWriter.write("Наименование организации автора документа=" + payDocRu.getOrgName() + "\n");
        fileWriter.write("Сумма документа, которым было сформирована данная операция=" + payDocRu.getDocumentSum() + "\n");
        fileWriter.write("Дата документа=" + payDocRu.getDocDate().getDay() + "." + payDocRu.getDocDate().getMonth() + "." + payDocRu.getDocDate().getYear() + "\n");
        fileWriter.write("Название получателя=" + payDocRu.getReceiverName() + "\n");
        fileWriter.write("Счет получателя=" + payDocRu.getReceiverAccount() + "\n");
        fileWriter.write("ИНН получателя=" + payDocRu.getReceiverINN() + "\n");
        fileWriter.write("Банк получателя=" + payDocRu.getReceiverBankName() + "\n");
        fileWriter.write("БИК банка получателя=" + payDocRu.getReceiverBankBic() + "\n");
        fileWriter.write("Кор. счет банка получателя=" + payDocRu.getReceiverBankCorrAccount() + "\n");
        fileWriter.write("Населенный пункт получателя=" + payDocRu.getReceiverBankCity() + "\n");
        fileWriter.write("Тип населенного пункта банка получателя=" + payDocRu.getReceiverBankSettlementType() + "\n");
        fileWriter.write("КПП получателя (103)=" + payDocRu.getReceiverKPP() + "\n");
        fileWriter.write("Название плательщика=" + payDocRu.getPayerName() + "\n");
        fileWriter.write("ИНН плательщика=" + payDocRu.getPayerINN() + "\n");
        fileWriter.write("Банк плательщика=" + payDocRu.getPayerBankName() + "\n");
        fileWriter.write("БИК банка плательщика=" + payDocRu.getPayerBankBic() + "\n");
        fileWriter.write("Кор. счет банка плательщика=" + payDocRu.getPayerBankCorrAccount() + "\n");
        fileWriter.write("Населенный пункт плательщика=" + payDocRu.getPayerBankCity() + "\n");
        fileWriter.write("Тип населенного пункта банка плательщика=" + payDocRu.getPayerBankSettlementType() + "\n");
        fileWriter.write("Счет плательщика=" + payDocRu.getPayerAccount() + "\n");
        fileWriter.write("КПП плательщика (102)=" + payDocRu.getPayerKPP() + "\n");
        fileWriter.write("Назначение платежа=" + payDocRu.getPaymentPurpose() + "\n");
        fileWriter.write("Вид платежа=" + payDocRu.getPaymentKind() + "\n");
        fileWriter.write("Код вида платежа=" + payDocRu.getPaymentCode() + "\n");
        fileWriter.write("Вид операции=" + payDocRu.getOperationType() + "\n");
        fileWriter.write("Показатель статуса=" + payDocRu.getDrawerStatus() + "\n");
        fileWriter.write("КБК=" + payDocRu.getCbc() + "\n");
        fileWriter.write("ОКТМО=" + payDocRu.getOcato() + "\n");
        fileWriter.write("Основание платежа=" + payDocRu.getPayReason() + "\n");
        fileWriter.write("Очередность платежа=" + payDocRu.getPaymentPriority() + "\n");
        fileWriter.write("Налоговый период/Код таможенного органа=" + payDocRu.getTaxPeriodDay() + "\n");
        fileWriter.write("Налоговый период (месяц)=" + payDocRu.getTaxPeriodMonth() + "\n");
        fileWriter.write("Налоговый период (год)=" + payDocRu.getTaxPeriodYear() + "\n");
        fileWriter.write("Дата налогового документа (день)=" + payDocRu.getDocDateDay() + "\n");
        fileWriter.write("Дата налогового документа (месяц)=" + payDocRu.getDocDateMonth() + "\n");
        fileWriter.write("Дата налогового документа (год)=" + payDocRu.getDocDateYear() + "\n");
        fileWriter.write("Номер налогового документа=" + payDocRu.getTaxDocNumber() + "\n");
        fileWriter.write("Код выплат=" + payDocRu.getChargeType() + "\n");
        fileWriter.write("Уникальный идентификатор платежа (УИП)=" + payDocRu.getUip());
        fileWriter.close();


        return result;
    }

}
