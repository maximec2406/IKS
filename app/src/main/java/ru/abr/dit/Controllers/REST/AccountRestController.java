package ru.abr.dit.Controllers.REST;


import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.Account;

import java.util.LinkedHashMap;
import java.util.List;



@RestController
@RequestMapping(path="/api/account")
public class AccountRestController {

    @Autowired
    private MainDAO dao;

    @PostMapping
    public boolean addAccount(@RequestBody LinkedHashMap<String, String> rbm) {

        Account acc = new Account(Long.valueOf(rbm.get("account")).longValue(), Long.valueOf(rbm.get("bic")).longValue());
        acc.setExtBranchName(rbm.get("extBranchName"));
        acc.setExtid(rbm.get("extid"));
        acc.setOrg(dao.findOrgById(Integer.valueOf(rbm.get("orgId")).intValue()));
        return (dao.addAccount(acc));

    }
//    public boolean addAccount(@RequestParam long account, long bic) {
//
//        Account newAccount = new Account(account, bic);
//        return (dao.addAccount(newAccount));
//
//    }

    @DeleteMapping
    public boolean deleteAccount(@RequestParam int id){

        return (dao.deleteAccountById(id));
    }

    @GetMapping("/all")
    public List<Account> getAllAccounts(){
        return dao.getAllAccounts();
    }

}
