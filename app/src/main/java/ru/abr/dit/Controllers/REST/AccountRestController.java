package ru.abr.dit.Controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.Account;

import java.util.List;


@RestController
@RequestMapping(path="/api/account")
public class AccountRestController {

    @Autowired
    private MainDAO dao;

    @PostMapping
    public boolean addAccount(@RequestParam long account, long bic) {

        Account newAccount = new Account(account, bic);
        return (dao.addAccount(newAccount));

    }

    @DeleteMapping
    public boolean deleteAccount(@RequestParam int id){

        return (dao.deleteAccountById(id));
    }

    @GetMapping("/all")
    public List<Account> getAllAccounts(){
        return dao.getAllAccounts();
    }

}
