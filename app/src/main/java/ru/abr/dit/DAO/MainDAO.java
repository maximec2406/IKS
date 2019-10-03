package ru.abr.dit.DAO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abr.dit.Models.Account;

import javax.persistence.EntityManager;
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

    public boolean addAccount(Account account){
        try {
            em.persist(account);
            return true;
        } catch (Exception e){
            log.error(e.getMessage() + "\n" + e.getStackTrace());
            return false;
        }
    }

    public List<Account> getAllAccounts(){
        try{
            return em.createQuery("from Account").getResultList();
        } catch (Exception e) {
            log.error(e.getMessage() + "\n" + e.getStackTrace());
            return null;
        }
    }
}
