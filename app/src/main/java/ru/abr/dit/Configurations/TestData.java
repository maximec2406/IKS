package ru.abr.dit.Configurations;

import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.Entities.Account;
import ru.abr.dit.Models.Entities.Bank;
import ru.abr.dit.Models.Entities.Branch;
import ru.abr.dit.Models.Entities.Org;

public class TestData {



    public TestData(MainDAO dao){
        try {

            Branch b = new Branch("АО \"АБ \"РОССИЯ\"", "fb591ea6-be3c-4918-aab3-b5a0193bd2ef", "044030861", "30101810800000000861");
            dao.addBranch(b);
            dao.addBranch(new Branch("МОСКОВСКИЙ ФИЛИАЛ АБ \"РОССИЯ\"", "9374f636-3fe7-460a-86dd-f7ff624855e5", "044525112", "30101810500000000112"));
            dao.addBranch(new Branch("ЦЕНТРАЛЬНЫЙ ФИЛИАЛ АБ \"РОССИЯ\"", "6539d1f6-ec30-4cd0-81b8-ca93b6f69852", "044525220", "30101810145250000220"));

            dao.addBank(new Bank("АО \"АБ \"РОССИЯ\"", "044030861", "30101810800000000861", "Санкт-Петербург", "г."));
            dao.addBank(new Bank("ЦЕНТРАЛЬНЫЙ ФИЛИАЛ АБ \"РОССИЯ\"", "044525220", "30101810145250000220", "Москва", "г."));
            dao.addBank(new Bank("ПЕТЕРБУРГСКИЙ ФИЛИАЛ АО ЮНИКРЕДИТ БАНКА", "044030858", "30101810800000000858", "Санкт-Петербург", "г."));

            Org o = new Org("0ce353c5-9a53-497d-ad02-df1fb6c37feb", "АО \"РЗК\"", "2000969", "7842170415");
            dao.addOrg(o);

            Account acc = new Account("40702810800000005897", "044030861");
            acc.setOrg(o);
            acc.setBranch(b);
            dao.addAccount(acc);



        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());

        }
    }
}
