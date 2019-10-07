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
    public boolean addOrg(@RequestBody  Org o) {

        return (dao.addOrg(o));

    }

    @DeleteMapping
    public boolean deleteOrg(@RequestParam int id){

        return (dao.deleteOrgById(id));
    }

    @GetMapping("/all")
    public List<Org> getAllOrg(){
        return dao.getAllOrg();
    }

    @GetMapping(path = "/{id}/accounts")
    public List<Account> getAllOrgAccount(@PathVariable int id){
        return dao.getAccountsByOrgId(id);
    }

}
