package ru.abr.dit.Controllers.REST;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.Entities.Account;
import ru.abr.dit.Models.Entities.Bank;
import ru.abr.dit.Models.Entities.Branch;
import ru.abr.dit.Models.Entities.Org;
import ru.abr.dit.Services.SOAP.UPGDocumentBody.PayDocRuBody;
import ru.abr.dit.Services.SOAP.UPGDocumentBody.StatementRequestBody;
import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;



@RestController
@RequestMapping(path="/api/account")
public class AccountRestController {

    @Autowired
    private MainDAO dao;

    @Autowired
    private StatementRequestBody upgStatementRequest;

    @PostMapping
    public boolean saveAccount(@RequestBody LinkedHashMap<String, String> rbm) {

        int accId = Integer.valueOf(rbm.get("id"));
        String bic = rbm.get("bic");
        String account = rbm.get("account").trim();
        Branch b = dao.findBranchById(Integer.valueOf(rbm.get("branchId")));
        Org org = dao.findOrgById(Integer.valueOf(rbm.get("orgId")));

        boolean result = false;

        // Если id == 0, то счет новый
        if (accId == 0){

            Account acc = new Account(account, bic);
            //acc.setBranch(dao.extBranchName);
            acc.setOrg(org);
            acc.setBranch(b);
            result = dao.addAccount(acc);

        // Иначе счет существующий
        } else {

            Account acc = dao.findAccountById(accId);
            acc.setOrg(org);
            //acc.setExtBranchName(extBranchName);
            acc.setAccount(account);
            acc.setBic(bic);
            result =  dao.updateAccount(acc);

        }

        return result;

    }

    @DeleteMapping("/{id}")
    public boolean deleteAccount(@PathVariable int id){

        return (dao.deleteAccountById(id));
    }

    @GetMapping("/all")
    public List<Account> getAllAccounts(){
        return dao.getAllAccounts();
    }

    @PostMapping("/stmtreq")
    public boolean getStmtReq(@RequestBody LinkedHashMap<String, String> rbm) throws ParseException, SOAPException, JAXBException, IOException {

        upgStatementRequest.sendStatementRequest(Integer.valueOf(rbm.get("id")), rbm.get("dateFrom"), rbm.get("dateTo"));
        return true;

    }

    @GetMapping("/filials")
    public List<Branch> getFilials(){

        return dao.getAllBranch();

    }

    @GetMapping("/banks")
    public List<Bank> getBank(){

        return dao.getAllBank();

    }

}
