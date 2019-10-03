package ru.abr.dit.Controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.Account;
import ru.abr.dit.Models.Org;

import java.util.List;

@RestController
@RequestMapping(path = "/api/org")
public class OrgRestController {


    @Autowired
    private MainDAO dao;

    @PostMapping
    public boolean addOrg(@RequestParam String extId, String name, int legacyId) {

        Org newOrg = new Org(extId,name,legacyId);
        return (dao.addOrg(newOrg));

    }

    @DeleteMapping
    public boolean deleteOrg(@RequestParam int id){

        return (dao.deleteOrgById(id));
    }

    @GetMapping("/all")
    public List<Account> getAllOrgs(){
        return dao.getAllAccounts();
    }

    @GetMapping(path = "/{id}/accounts")
    public List<Account> getAllOrgAccount(@PathVariable int id){
        return dao.getAccountsByOrgId(id);
    }

}
