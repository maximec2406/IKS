package ru.abr.dit.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.abr.dit.Models.Entities.Account;

import javax.persistence.*;
import java.util.List;

@Entity
public class Org {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;

    @Column(nullable = false, unique = true)
    private String extId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String legacyId;

    @OneToMany(mappedBy = "org", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Account> accounts;

    @Column
    private String inn;

    public Org() {
    }

    public Org(String extId, String name, String legacyId, String inn) {
        this.extId = extId;
        this.name = name;
        this.legacyId = legacyId;
        this.inn = inn;

    }

    public int getId() {
        return id;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }
}
