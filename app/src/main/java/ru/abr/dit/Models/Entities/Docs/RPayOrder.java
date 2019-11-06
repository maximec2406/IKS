package ru.abr.dit.Models.Entities.Docs;
import ru.abr.dit.Models.Entities.Bank;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class RPayOrder {

    @Id
//    @GeneratedValue(generator ="UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
//    @Column(updatable = false, nullable = false, unique = true)
    @GeneratedValue
    private UUID id;

    @Column
    private Timestamp absAcceptDate; //время принятия в АБС

    @Column
    private boolean acceptIndividual; // Платеж не связан с предпринимательской деятельностью

    @Column
    private String accountId; // счет организации

    @Column
    private Timestamp bankAcceptDate; // время принятия в банке

    @Lob
    @Column //(columnDefinition = "CLOB")
    private String bankMessage;

    @Column
    private String bankMessageAuthor;

    @Column
    private boolean budgetaryPayment; // признак бюджетный платеж

    @Column
    private String cbc;

    @Column
    private String chargeType; // тип налоговый платеж/код выплат

    @Column
    private String creditContract; // номер кредитного договора

    @Column
    private String currencyOperationType; // код вида валютной операции

    @Column
    private String dealPassport; // номер паспорта сделки

    @Column
    private Date deliveredTime; // поступило в банк плательщика

    @Column
    private Date docDate;

    @Column
    private String docDateDay; // дата налогового документа день

    @Column
    private String docDateMonth; // дата налогового документа месяц

    @Column
    private String docDateYear; // дата налогового документа год

    @Column
    private String docNumber;

    @Column
    private String documentSum;

    @Column
    private String drawerStatus;// показатель статуса

    @Column
    private boolean forAccept; // признак к Акцепту

    @Column
    private String globalId; //

    @Column
    private Timestamp lastModifyDate;

    @Column
    private String mbaAccountId; // идентификатор счета плательщика МБА

    @Column
    private String mbaActionName; // имя действия МБА

    @Column
    private Timestamp mbaCreateDate; // дата и время создания документа

    @Column
    private boolean mbaDoc; // признак документа МБА

    @Column
    private String mbaDocId;

    @Column
    private String mbaMessageId; // id сообщения МБА

    @Column
    private String mbaStateName; // Дополнительный статус МБА

    @Column
    private String messageFA; // сообщение от FA

    @Column
    private String messageFAAuthor;

    @Column
    private boolean notifyByEmail;

    @Column
    private boolean notifyBySMS;

    @Column
    private String ocato;

    @Column
    private Date operationDate;

    @Column
    private String operationType;

    @Column
    private String orgId;

    @Column
    private String orgName;
    @Column
    private String payReason; // основание платежа

    @Column
    private Date payUntil;

    @Column
    private String payerAccount;

    @ManyToOne
    private Bank payerBank;

    @ManyToOne
    private Bank receiverBank;
    @Column
    private String payerBankBic;
//
//    @Column
//    private String payerBankCity;
//
//    @Column
//    private String payerBankCorrAccount;
//
//    @Column
//    private String payerBankName;
//
//    @Column
//    private String payerBankSettlementType;

    @Column
    private String payerINN;

    @Column
    private String payerKPP;

    @Column
    private String payerName;

    @Column
    private String paymentCode;

    @Column
    private String paymentKind;

    @Column
    private String paymentPriority;

    @Column
    private String paymentPurpose;

    @Column
    private String paymentType;

    @Column
    private String phoneForSMS;

    @Column
    private boolean press; // неотложность

    @Column
    private String product;

    @Column
    private String receiverAccount;

    @Column
    private String receiverBankBic;
//
//    @Column
//    private String receiverBankCity;
//
//    @Column
//    private String receiverBankCorrAccount;
//
//    @Column
//    private String receiverBankName;
//
//    @Column
//    private String receiverBankSettlementType;

    @Column
    private String receiverINN;

    @Column
    private String receiverKPP;

    @Column
    private String receiverName;

    @Column
    private String targetAssignment; // целевое поручение

    @Column
    private String taxDocNumber;

    @Column
    private String taxOrCustoms;

    @Column
    private String taxPeriodDay;

    @Column
    private String taxPeriodMonth;

    @Column
    private String taxPeriodYear;

    @Column
    private String uip;

    @Column
    private boolean urgent; // срочность

    @Column
    private boolean useOwnMeans; // использовать собственные средства

    @Column
    private String vatCalculationRule; // способ расчета ндс

    @Column
    private int vatRate;

    @Column
    private double vatSum;

    @Column
    private String currentStatus;

    @Lob
    @Column
    private String signCollection;

    public RPayOrder() {
    }

    public UUID getId() {
        return id;
    }

    public boolean isAcceptIndividual() {
        return acceptIndividual;
    }

    public void setAcceptIndividual(boolean acceptIndividual) {
        this.acceptIndividual = acceptIndividual;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBankMessage() {
        return bankMessage;
    }

    public void setBankMessage(String bankMessage) {
        this.bankMessage = bankMessage;
    }

    public String getBankMessageAuthor() {
        return bankMessageAuthor;
    }

    public void setBankMessageAuthor(String bankMessageAuthor) {
        this.bankMessageAuthor = bankMessageAuthor;
    }

    public boolean isBudgetaryPayment() {
        return budgetaryPayment;
    }

    public void setBudgetaryPayment(boolean budgetaryPayment) {
        this.budgetaryPayment = budgetaryPayment;
    }

    public String getCbc() {
        return cbc;
    }

    public void setCbc(String cbc) {
        this.cbc = cbc;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getCreditContract() {
        return creditContract;
    }

    public void setCreditContract(String creditContract) {
        this.creditContract = creditContract;
    }

    public String getCurrencyOperationType() {
        return currencyOperationType;
    }

    public void setCurrencyOperationType(String currencyOperationType) {
        this.currencyOperationType = currencyOperationType;
    }

    public String getDealPassport() {
        return dealPassport;
    }

    public void setDealPassport(String dealPassport) {
        this.dealPassport = dealPassport;
    }

    public Date getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(Date deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getDocDateDay() {
        return docDateDay;
    }

    public void setDocDateDay(String docDateDay) {
        this.docDateDay = docDateDay;
    }

    public String getDocDateMonth() {
        return docDateMonth;
    }

    public void setDocDateMonth(String docDateMonth) {
        this.docDateMonth = docDateMonth;
    }

    public String getDocDateYear() {
        return docDateYear;
    }

    public void setDocDateYear(String docDateYear) {
        this.docDateYear = docDateYear;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocumentSum() {
        return documentSum;
    }

    public void setDocumentSum(String documentSum) {
        this.documentSum = documentSum;
    }

    public String getDrawerStatus() {
        return drawerStatus;
    }

    public void setDrawerStatus(String drawerStatus) {
        this.drawerStatus = drawerStatus;
    }

    public boolean isForAccept() {
        return forAccept;
    }

    public void setForAccept(boolean forAccept) {
        this.forAccept = forAccept;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getMbaAccountId() {
        return mbaAccountId;
    }

    public void setMbaAccountId(String mbaAccountId) {
        this.mbaAccountId = mbaAccountId;
    }

    public String getMbaActionName() {
        return mbaActionName;
    }

    public void setMbaActionName(String mbaActionName) {
        this.mbaActionName = mbaActionName;
    }

    public boolean isMbaDoc() {
        return mbaDoc;
    }

    public void setMbaDoc(boolean mbaDoc) {
        this.mbaDoc = mbaDoc;
    }

    public String getMbaDocId() {
        return mbaDocId;
    }

    public void setMbaDocId(String mbaDocId) {
        this.mbaDocId = mbaDocId;
    }

    public String getMbaMessageId() {
        return mbaMessageId;
    }

    public void setMbaMessageId(String mbaMessageId) {
        this.mbaMessageId = mbaMessageId;
    }

    public String getMbaStateName() {
        return mbaStateName;
    }

    public void setMbaStateName(String mbaStateName) {
        this.mbaStateName = mbaStateName;
    }

    public String getMessageFA() {
        return messageFA;
    }

    public void setMessageFA(String messageFA) {
        this.messageFA = messageFA;
    }

    public String getMessageFAAuthor() {
        return messageFAAuthor;
    }

    public void setMessageFAAuthor(String messageFAAuthor) {
        this.messageFAAuthor = messageFAAuthor;
    }

    public boolean isNotifyByEmail() {
        return notifyByEmail;
    }

    public void setNotifyByEmail(boolean notifyByEmail) {
        this.notifyByEmail = notifyByEmail;
    }

    public boolean isNotifyBySMS() {
        return notifyBySMS;
    }

    public void setNotifyBySMS(boolean notifyBySMS) {
        this.notifyBySMS = notifyBySMS;
    }

    public String getOcato() {
        return ocato;
    }

    public void setOcato(String ocato) {
        this.ocato = ocato;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPayReason() {
        return payReason;
    }

    public void setPayReason(String payReason) {
        this.payReason = payReason;
    }

    public Date getPayUntil() {
        return payUntil;
    }

    public void setPayUntil(Date payUntil) {
        this.payUntil = payUntil;
    }

    public String getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }

    public String getPayerINN() {
        return payerINN;
    }

    public void setPayerINN(String payerINN) {
        this.payerINN = payerINN;
    }

    public String getPayerKPP() {
        return payerKPP;
    }

    public void setPayerKPP(String payerKPP) {
        this.payerKPP = payerKPP;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentKind() {
        return paymentKind;
    }

    public void setPaymentKind(String paymentKind) {
        this.paymentKind = paymentKind;
    }

    public String getPaymentPriority() {
        return paymentPriority;
    }

    public void setPaymentPriority(String paymentPriority) {
        this.paymentPriority = paymentPriority;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPhoneForSMS() {
        return phoneForSMS;
    }

    public void setPhoneForSMS(String phoneForSMS) {
        this.phoneForSMS = phoneForSMS;
    }

    public boolean isPress() {
        return press;
    }

    public void setPress(boolean press) {
        this.press = press;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getReceiverINN() {
        return receiverINN;
    }

    public void setReceiverINN(String receiverINN) {
        this.receiverINN = receiverINN;
    }

    public String getReceiverKPP() {
        return receiverKPP;
    }

    public void setReceiverKPP(String receiverKPP) {
        this.receiverKPP = receiverKPP;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getTargetAssignment() {
        return targetAssignment;
    }

    public void setTargetAssignment(String targetAssignment) {
        this.targetAssignment = targetAssignment;
    }

    public String getTaxDocNumber() {
        return taxDocNumber;
    }

    public void setTaxDocNumber(String taxDocNumber) {
        this.taxDocNumber = taxDocNumber;
    }

    public String getTaxOrCustoms() {
        return taxOrCustoms;
    }

    public void setTaxOrCustoms(String taxOrCustoms) {
        this.taxOrCustoms = taxOrCustoms;
    }

    public String getTaxPeriodDay() {
        return taxPeriodDay;
    }

    public void setTaxPeriodDay(String taxPeriodDay) {
        this.taxPeriodDay = taxPeriodDay;
    }

    public String getTaxPeriodMonth() {
        return taxPeriodMonth;
    }

    public void setTaxPeriodMonth(String taxPeriodMonth) {
        this.taxPeriodMonth = taxPeriodMonth;
    }

    public String getTaxPeriodYear() {
        return taxPeriodYear;
    }

    public void setTaxPeriodYear(String taxPeriodYear) {
        this.taxPeriodYear = taxPeriodYear;
    }

    public String getUip() {
        return uip;
    }

    public void setUip(String uip) {
        this.uip = uip;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isUseOwnMeans() {
        return useOwnMeans;
    }

    public void setUseOwnMeans(boolean useOwnMeans) {
        this.useOwnMeans = useOwnMeans;
    }

    public String getVatCalculationRule() {
        return vatCalculationRule;
    }

    public void setVatCalculationRule(String vatCalculationRule) {
        this.vatCalculationRule = vatCalculationRule;
    }

    public int getVatRate() {
        return vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    public double getVatSum() {
        return vatSum;
    }

    public void setVatSum(double vatSum) {
        this.vatSum = vatSum;
    }

    public String getSignCollection() {
        return signCollection;
    }

    public void setSignCollection(String signCollection) {
        this.signCollection = signCollection;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Timestamp getAbsAcceptDate() {
        return absAcceptDate;
    }

    public void setAbsAcceptDate(Timestamp absAcceptDate) {
        this.absAcceptDate = absAcceptDate;
    }

    public Timestamp getBankAcceptDate() {
        return bankAcceptDate;
    }

    public void setBankAcceptDate(Timestamp bankAcceptDate) {
        this.bankAcceptDate = bankAcceptDate;
    }

    public Timestamp getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Timestamp lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Timestamp getMbaCreateDate() {
        return mbaCreateDate;
    }

    public void setMbaCreateDate(Timestamp mbaCreateDate) {
        this.mbaCreateDate = mbaCreateDate;
    }

    public Bank getPayerBank() {
        return payerBank;
    }

    public void setPayerBank(Bank payerBank) {
        this.payerBank = payerBank;
    }

    public Bank getReceiverBank() {
        return receiverBank;
    }

    public void setReceiverBank(Bank receiverBank) {
        this.receiverBank = receiverBank;
    }

    public String getPayerBankBic() {
        return payerBankBic;
    }

    public void setPayerBankBic(String payerBankBic) {
        this.payerBankBic = payerBankBic;
    }

    public String getReceiverBankBic() {
        return receiverBankBic;
    }

    public void setReceiverBankBic(String receiverBankBic) {
        this.receiverBankBic = receiverBankBic;
    }


}
