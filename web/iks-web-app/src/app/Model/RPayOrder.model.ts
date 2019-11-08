export class RPayOrder{

  constructor(){
    {
      this.id = null;
      this.bankMessage = '';
      this.bankMessageAuthor = '';
      this.budgetaryPayment = false; // признак бюджетный платеж
      this.cbc = '';
      this.chargeType = ""; // тип налоговый платеж/код выплат
      this.creditContract = ''; // номер кредитного договора
      this.currencyOperationType = ''; // код вида валютной операции
      this.dealPassport = '';// номер паспорта сделки
      this.deliveredTime = ''; // поступило в банк плательщика
      this.docDate = new Date();//this.getDate(new Date());
      this.docDateDay = ''; // дата налогового документа день
      this.docDateMonth = ''; // дата налогового документа месяц
      this.docDateYear = ''; // дата налогового документа год
      this.docNumber = '1';
      this.documentSum = '100';
      this.drawerStatus = '';// показатель статуса
      this.forAccept = false; // признак к Акцепту
      this.lastModifyDate = '';
      this.mbaAccountId = '';// идентификатор счета плательщика МБА
      this.mbaActionName = ''; // имя действия МБА
      this.mbaCreateDate = ''; // дата и время создания документа
      this.mbaDoc = false; // признак документа МБА
      this.mbaDocId = '';
      this.ocato = '';
      this.operationDate = null;
      this.operationType = '01';
      this.orgName = 'АО "РЗК"';
      this.payReason = ''; // основание платежа
      this.payUntil = '';
      this.payerAccount = '40702810800000005897';
      this.payerBankBic = '';
      // this.payerBankCity = 'Санкт-Петербург';
      // this.payerBankCorrAccount = '30101810800000000861';
      // this.payerBankName = 'АО "АБ "РОССИЯ"';
      // this.payerBankSettlementType = 'г.';
      this.payerINN = '7842170415';
      this.payerKPP = '784201001';
      this.payerName = 'АО "РЗК"';
      this.paymentCode = '1';
      this.paymentKind = 'электронно';
      this.paymentPriority = '5';
      this.paymentPurpose = 'Оплата по счету № 50 от 20.09.2019г за оказание бухгалтерских и юридических услуг за сентябрь 2019г по договору № РА/2018/03-02 от 12.03.2018г. НДС не облагается';
      this.paymentType = '';
      this.receiverAccount = '40702810520020000000';
      this.receiverBankBic = '';
      this.receiverBank = null;
      this.payerBank = null;
      // this.receiverBankCity = 'Санкт-Петербург';
      // this.receiverBankCorrAccount = '30101810800000000858';
      // this.receiverBankName = 'ПЕТЕРБУРГСКИЙ ФИЛИАЛ АО ЮНИКРЕДИТ БАНКА';
      // this.receiverBankSettlementType = 'г.';
      this.receiverINN = '7813634800';
      this.receiverKPP = '781301001';
      this.receiverName = 'АО "РА"';
      this.taxDocNumber = '';
      this.taxOrCustoms = '';
      this.taxPeriodDay = '';
      this.taxPeriodMonth = '';
      this.taxPeriodYear = '';
      this.uip = '';
      this.vatCalculationRule = 'Vat1'; // способ расчета ндс
      this.vatRate = '0';
      this.vatSum = '0';
      this.currentStatus = 'new';
      this.signCollection = '';
      this.signHash = '';
    }
  }

  id: String;
  bankMessage: String;
  bankMessageAuthor:  String;
  budgetaryPayment: boolean; // признак бюджетный платеж
  cbc: String;
  chargeType: String; // тип налоговый платеж/код выплат
  creditContract: String; // номер кредитного договора
  currencyOperationType: String; // код вида валютной операции
  dealPassport: String;// номер паспорта сделки
  deliveredTime:  String; // поступило в банк плательщика
  docDate: Date;
  docDateDay:  String; // дата налогового документа день
  docDateMonth: String; // дата налогового документа месяц
  docDateYear: String; // дата налогового документа год
  docNumber: String;
  documentSum: String;
  drawerStatus:  String;// показатель статуса
  forAccept: boolean; // признак к Акцепту  
  lastModifyDate: String;
  mbaAccountId: String;// идентификатор счета плательщика МБА
  mbaActionName: String; // имя действия МБА
  mbaCreateDate: String; // дата и время создания документа
  mbaDoc: boolean; // признак документа МБА
  mbaDocId:  String;
  ocato: String;
  operationDate: Date;
  operationType:  String;
  orgName: String;
  payReason: String; // основание платежа
  payUntil: String;
  payerAccount: String;
  payerBankBic: String;
  // payerBankCity: String;
  // payerBankCorrAccount: String;
  // payerBankName: String;
  // payerBankSettlementType: String;
  payerINN: String;
  payerKPP: String;
  payerName: String;
  paymentCode: String;
  paymentKind: String;
  paymentPriority: String;
  paymentPurpose: String;
  paymentType: String;
  receiverAccount: String;
  receiverBankBic: String;
  // receiverBankCity: String;
  // receiverBankCorrAccount: String;
  // receiverBankName: String;
  // receiverBankSettlementType: String;
  receiverINN: String;
  receiverKPP: String;
  receiverName: String;
  taxDocNumber: String;
  taxOrCustoms: String;
  taxPeriodDay: String;
  taxPeriodMonth: String;
  taxPeriodYear: String;
  uip: String;
  vatCalculationRule: String; // способ расчета ндс
  vatRate: String;
  vatSum: String;
  currentStatus: String;
  signCollection: String;
  signHash: String;
  orgId: number;
  payerBank: Object;
  receiverBank: Object;
}


