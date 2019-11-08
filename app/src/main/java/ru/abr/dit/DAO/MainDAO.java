package ru.abr.dit.DAO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abr.dit.Models.Entities.*;
import ru.abr.dit.Models.Entities.Docs.RPayOrder;
import sun.text.bidi.BidiRun;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RepositoryRestResource
public class MainDAO {

    @PersistenceContext
    protected EntityManager em;

    @Qualifier("root")
    private Logger log;

    // Счета начало
    public boolean addAccount(Account account){
        try {
            em.persist(account);
            return true;
        } catch (Exception e){
            this.addErrorLog(e);
            return false;
        }
    }

    public List<Account> getAllAccounts(){
        try{
            return em.createQuery("from Account").getResultList();
        } catch (Exception e) {
            this.addErrorLog(e);
            return null;
        }
    }

    public boolean deleteAccountById(int id){

        try{
            em.remove(this.findAccountById(id));
            return true;
        } catch (Exception e){
            this.addErrorLog(e);
            return false;
        }

    }

    public Account findAccountById(int id){

        try{
            return (Account) em.createQuery("from Account where id=:id").setParameter("id", id).getSingleResult();
        } catch (EntityNotFoundException e){
            log.warn("Счет с id " + id + " не найден");
            return null;
        } catch (Exception e){
            this.addErrorLog(e);
            return null;
        }
    }

    public boolean updateAccount(Account acc){
        try{
            em.merge(acc);
            return true;
        } catch (Exception e){
            this.addErrorLog(e);
            return false;
        }
    }

    public List<Branch> getAllBranch(){
        try{
            return em.createQuery("from Branch ").getResultList();
        } catch (Exception e) {
            this.addErrorLog(e);
            return null;
        }
    }

    public boolean addBranch(Branch b){
        try {
            em.persist(b);
            return true;
        } catch (Exception e){
            this.addErrorLog(e);
            return false;
        }
    }

    public Branch findBranchById(int id){
        try{
            return (Branch) em.createQuery("from Branch where id=:id").setParameter("id", id).getSingleResult();
        } catch (EntityNotFoundException e){
            log.warn("Филиал с id " + id + " не найден");
            return null;
        } catch (Exception e){
            this.addErrorLog(e);
            return null;
        }

    }

    public String getAccIdByNumber(String accNumber){
        try{
            return (String) em.createQuery("select extid from Account where account=:accNumber").setParameter("accNumber", accNumber).getSingleResult();
        } catch (EntityNotFoundException e){
            log.warn("Счет с номером " + accNumber + " не найден");
            return null;
        } catch (Exception e){
            this.addErrorLog(e);
            return null;
        }
    }

    public Bank getBankByBic(String bic){
        try{
            return (Bank) em.createQuery("from Bank where bic=:bic").setParameter("bic", bic).getSingleResult();
        } catch (EntityNotFoundException e){
            log.warn("Банк с Биком " + bic + " не найден");
            return null;
        } catch (Exception e){
            this.addErrorLog(e);
            return null;
        }
    }

    // Счета конец


    // Орг начало
    public boolean addOrg(Org org){
        try {
            em.persist(org);
            return true;
        } catch (Exception e){
            this.addErrorLog(e);
            return false;
        }
    }

    public List<Org> getAllOrg(){
        try{
            return em.createQuery("from Org").getResultList();
        } catch (Exception e) {
            this.addErrorLog(e);
            return null;
        }
    }

    public boolean deleteOrgById(int id){

        try{
            em.remove(this.findOrgById(id));
            return true;
        } catch (Exception e){
            this.addErrorLog(e);
            return false;
        }

    }

    public Org findOrgById(int id){

        try{
            return (Org) em.createQuery("from Org where id=:id").setParameter("id", id).getSingleResult();
        } catch (EntityNotFoundException e){
            log.warn("Счет с id " + id + " не найден");
            return null;
        } catch (Exception e){
            this.addErrorLog(e);
            return null;
        }
    }

    public List<Account> getAccountsByOrgId(int id){

        try{
            return em.createQuery("select accounts from Org o where o.id=:id").setParameter("id", id).getResultList();
        } catch (EmptyResultDataAccessException e){
            log.warn("1. Счета организации с id " + id + " не найдены");
            return null;
        } catch (EntityNotFoundException e){
            log.warn("2. Счета организации с id " + id + " не найдены");
            return null;
        } catch (Exception e){
            this.addErrorLog(e);
            return null;
        }

    }

    public boolean saveRequest(UPGRequest upgRequest){

        try{
            em.persist(upgRequest);
            return true;
        }  catch (Exception e){
            this.addErrorLog(e);
            return false;
        }
    }

    public boolean saveSession(UPGSession upgSession){

        try{
            em.persist(upgSession);
            return true;
        }  catch (Exception e){
            this.addErrorLog(e);
            return false;
        }
    }

    public UPGRequest getUPGRequestById(String id){

        try{
            return (UPGRequest) em.createQuery("from UPGRequest where id=:id").setParameter("id",id).getSingleResult();
        } catch (Exception e){
        this.addErrorLog(e);
        return null;
    }
    }




    public void addErrorLog(Exception e){
        log.error(e.getMessage() + "\n" + e.getStackTrace());
    }

    // Орг конец

    // Банк начало

    public boolean addBank(Bank b){
        try{
            em.persist(b);
            return true;
        }  catch (Exception e){
            this.addErrorLog(e);
            return false;
        }
    }

    public List<Bank> getAllBank(){
        try{
            return em.createQuery("from Bank").getResultList();
        } catch (Exception e) {
            this.addErrorLog(e);
            return null;
        }
    }

    // Банк конец


    // Документы начало


    // Платежные поручения начало

    public UUID addRPayOrder(RPayOrder rpo){
        try{
            em.persist(rpo);
            return rpo.getId();
        }  catch (Exception e){
            this.addErrorLog(e);
            return null;
        }
    }

    public boolean saveRPayOrder(RPayOrder newRpo){
        try{
            em.merge(newRpo);
            return true;
        }  catch (Exception e){
            this.addErrorLog(e);
            return false;
        }
    }

    public List<RPayOrder> getRPayOrderByOrgId(int id){
        try{
            List<RPayOrder> l =  em.createQuery("from RPayOrder where orgId=:id").setParameter("id",String.valueOf(id)).getResultList();
            return l;
        }  catch (Exception e){
            this.addErrorLog(e);
            return null;
        }
    }

    public RPayOrder findRPayOrderById(String id){
        try{
            return (RPayOrder) em.createQuery("from RPayOrder where id=:id").setParameter("id",UUID.fromString(id)).getSingleResult();
        }  catch (Exception e){
            this.addErrorLog(e);
            return null;
        }
    }

    // Платежные поручения конец

    // Документы конец
}
