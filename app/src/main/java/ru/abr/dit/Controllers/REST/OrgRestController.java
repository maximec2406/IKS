package ru.abr.dit.Controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.Entities.Account;
import ru.abr.dit.Models.Entities.Docs.RPayOrder;
import ru.abr.dit.Models.Entities.Org;

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

    @GetMapping(path = "/{id}/rpayorder")
    public List<RPayOrder> getAllOrgRPayOrder(@PathVariable int id){ return dao.getRPayOrderByOrgId(id);
    }

}
