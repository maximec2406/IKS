package ru.abr.dit.Controllers.REST;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.Account;
import ru.abr.dit.Models.Org;
import ru.abr.dit.Services.SOAP.ReqToCorr.PreLoginService;

import java.util.LinkedHashMap;
import java.util.List;



@RestController
@RequestMapping(path="/api/account")
public class AccountRestController {

    @Autowired
    private MainDAO dao;

    @Autowired
    private PreLoginService prelogin;

    @PostMapping
    public boolean saveAccount(@RequestBody LinkedHashMap<String, String> rbm) {

        int accId = Integer.valueOf(rbm.get("id"));
        int bic = Integer.valueOf(rbm.get("bic"));
        String account = rbm.get("account").trim();
        String extBranchName = rbm.get("extBranchName");
        Org org = dao.findOrgById(Integer.valueOf(rbm.get("orgId")));

        boolean result = false;

        // Если id == 0, то счет новый
        if (accId == 0){

            Account acc = new Account(account, bic);
            acc.setExtBranchName(extBranchName);
            acc.setOrg(org);
            result = dao.addAccount(acc);

        // Иначе счет существующий
        } else {

            Account acc = dao.findAccountById(accId);
            acc.setOrg(org);
            acc.setExtBranchName(extBranchName);
            acc.setAccount(account);
            acc.setBic(bic);
            result =  dao.updateAccount(acc);

        }

        return result;

    }
//    public boolean addAccount(@RequestParam long account, long bic) {
//
//        Account newAccount = new Account(account, bic);
//        return (dao.addAccount(newAccount));
//
//    }

    @DeleteMapping("/{id}")
    public boolean deleteAccount(@PathVariable int id){

        return (dao.deleteAccountById(id));
    }

    @GetMapping("/all")
    public List<Account> getAllAccounts(){
        return dao.getAllAccounts();
    }

    @PostMapping("/stmtreq")
    public boolean getStmtReq(LinkedHashMap<String, String> rbm){

        prelogin.sendPreLoginRequest();

        return true;

    }

}
