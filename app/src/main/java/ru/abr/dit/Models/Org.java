package ru.abr.dit.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private int legacyId;

    @OneToMany(mappedBy = "org", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Account> accounts;

    public Org() {
    }

    public Org(String extId, String name, int legacyId) {
        this.extId = extId;
        this.name = name;
        this.legacyId = legacyId;
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

    public int getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(int legacyId) {
        this.legacyId = legacyId;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
