package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.Account;

import java.util.List;


@RestController
public class RESTAccountController {

    @Autowired
    private MainDAO dao;

    @PostMapping("/api/addAccount")
    public boolean addAccount(@RequestParam long account, long bic) {

        Account newAccount = new Account(account, bic);
        return (dao.addAccount(newAccount));

    }

    @GetMapping("/api/accounts")
    public List<Account> getAllAccounts(){
        return dao.getAllAccounts();
    }

}
