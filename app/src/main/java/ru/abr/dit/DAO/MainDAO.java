package ru.abr.dit.DAO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abr.dit.Models.Account;
import ru.abr.dit.Models.Org;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@RepositoryRestResource
public class MainDAO {

    @PersistenceContext
    protected EntityManager em;

    @Autowired
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



    public void addErrorLog(Exception e){
        log.error(e.getMessage() + "\n" + e.getStackTrace());
    }

    // Орг конец
}
