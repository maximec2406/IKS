package ru.abr.dit.Services.SOAP.UPGDocumentBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abr.dit.Models.XMLTemplate.PayDocRu.ObjectFactory;
import ru.abr.dit.Models.XMLTemplate.PayDocRu.PayDocRu;
import ru.abr.dit.Models.XMLTemplate.StatementRequest.StatementRequest;
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

    private ObjectFactory of = new ObjectFactory();
    private final static QName _PayDocRu_QNAME = new QName("http://bssys.com/sbns/integration", "PayDocRu");

    public void sendPayDocRu() throws ParseException, JAXBException, SOAPException, IOException {

        //sendRequestService.sendRequest(createPayDocRu());
        createPayDocRu();
    }

    public String createPayDocRu() throws JAXBException, IOException {


        PayDocRu payDocRu = of.createPayDocRu();
        payDocRu.setMbaDoc(false);
        payDocRu.setBudgetaryPayment(false);
        payDocRu.setAccountId("e0e91a9c-f448-4779-8267-921c350617b0");
        payDocRu.setNotifyByEmail(false);
        payDocRu.setNotifyBySMS(false);
        payDocRu.setCbc("");
        payDocRu.setChargeType("");
//        payDocRu.setCommission(new BigDecimal(0));
//        payDocRu.setCreditContract("");
//        payDocRu.setCurrencyOperationType("01"); //код вида валютной операции
        payDocRu.setDocDate(xmlGregorianCalendarConverter.asXMLGregorianCalendar(new Date()));
        payDocRu.setDocDateDay("");
        payDocRu.setDocDateMonth("");
        payDocRu.setDocDateYear("");
        payDocRu.setDocId(UUID.randomUUID().toString());
        payDocRu.setDocNumber("2");
        payDocRu.setDocumentSum(new BigDecimal(1.00));
//        payDocRu.setDrawerStatus("");// показатель статуса
//        payDocRu.setExternalId(payDocRu.getDocId());
//        payDocRu.setForAccept(false);
        payDocRu.setOcato("");
        payDocRu.setOperationType("01"); // вид операции
        payDocRu.setOrgId("0ce353c5-9a53-497d-ad02-df1fb6c37feb");
        payDocRu.setOrgName("АО \"РЗК\"");
        payDocRu.setPayReason(""); // основание платежа (бюдж)
        payDocRu.setPayerAccount("40702810800000005897");
        payDocRu.setPayerBankBic("044030861");
        payDocRu.setPayerBankCity("САНКТ-ПЕТЕРБУРГ");
        payDocRu.setPayerBankSettlementType("г.");
        payDocRu.setPayerBankCorrAccount("30101810800000000861");
        payDocRu.setPayerBankName("АО \"АБ \"РОССИЯ\"");
        payDocRu.setPayerINN("7842170415");
        payDocRu.setPayerKPP("783901001");
        payDocRu.setPayerName("АО \"РЗК\"");
        payDocRu.setPaymentCode("1"); // код вида платежа
        payDocRu.setPaymentKind("электронно");
        payDocRu.setPaymentPriority("5"); // очередность платежа
        payDocRu.setPaymentPurpose("назначение платежа"); // назначение платежа
        payDocRu.setReceiverAccount("40702810900000007630");
        payDocRu.setReceiverBankBic("044030861");
        payDocRu.setReceiverBankCity("Санкт-Петербург");
        payDocRu.setReceiverBankSettlementType("г.");
        payDocRu.setReceiverBankCorrAccount("30101810800000000861");
        payDocRu.setReceiverBankName("АО \"АБ \"РОССИЯ\"");
        payDocRu.setReceiverINN("7839095359");
        payDocRu.setReceiverKPP("783901001");
        payDocRu.setReceiverName("ООО \"КВАРТА\"");
        payDocRu.setTaxDocNumber(""); // номер налогового документа
        payDocRu.setTaxOrCustoms("");
        payDocRu.setTaxPeriodDay("");
        payDocRu.setTaxPeriodMonth("");
        payDocRu.setTaxPeriodYear("");
        payDocRu.setUip("");
        payDocRu.setUrgent(false); // срочность
//        payDocRu.setVatCalculationRule("VatZero"); // способ расчета ндс
//        payDocRu.setVatRate(new BigDecimal(0)); // сумма ндс
//        payDocRu.setVatSum(new BigDecimal(0));

//       PayDocRu.PayDocRuSignCollection payDocRuSignCollection = of.createPayDocRuSignCollection();
//       payDocRu.setSignCollection(payDocRuSignCollection);
        this.createDigest(payDocRu);

        PayDocRu.PayDocRuSignCollection payDocRuSignCollection = createPayDocRuSignCollection(of);

        JAXBElement<PayDocRu> ePayDocRu = new JAXBElement<PayDocRu>(_PayDocRu_QNAME, PayDocRu.class, null, payDocRu);
        StringWriter sw = new StringWriter();
        JAXBContext.newInstance(PayDocRu.class).createMarshaller().marshal(ePayDocRu, sw);


        return sw.toString().substring(sw.toString().indexOf("<PayDocRu"));
    }

    private PayDocRu.PayDocRuSignCollection createPayDocRuSignCollection(ObjectFactory of){

        PayDocRu.PayDocRuSignCollection payDocRuSignCollection = of.createPayDocRuSignCollection();
        PayDocRu.PayDocRuSignCollection.pdrSignCollection pdrSignCollection = of.createPayDocRuSignCollectionSignCollection();
        PayDocRu.PayDocRuSignCollection.pdrSignCollection.Signs signs = of.createPayDocRuSignCollectionSignCollectionSigns();
        PayDocRu.PayDocRuSignCollection.pdrSignCollection.Signs.pdrSign pdrSign = of.createPayDocRuSignCollectionSignCollectionSignsSign();


        pdrSign.setCertificateGuid("");
        pdrSign.setContent("");
        pdrSign.setDigestScheme("com.bssys.sbns.dbo.rur.payment.R030SignDigest");
        pdrSign.setDigestSchemeFormat("");
        pdrSign.setDigestSchemeVersion(1);
        pdrSign.setDtCreate(xmlGregorianCalendarConverter.asXMLGregorianCalendar(new Date()));
        pdrSign.setOrgId("0ce353c5-9a53-497d-ad02-df1fb6c37feb");
        pdrSign.setOrgName("АО \"РЗК\"");
        pdrSign.setSignAuthorityId("2a67e143-5bad-4beb-95cc-dbf7f197b714");
        payDocRuSignCollection.getSignCollection();

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
