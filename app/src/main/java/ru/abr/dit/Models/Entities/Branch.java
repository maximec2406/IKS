package ru.abr.dit.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;

    @Column (nullable = false, unique = false)
    private String name;

    @Column (nullable = false, unique = true)
    private String extId;

    @Column (nullable = false, unique = true)
    private String bic;

    @Column (nullable = false, unique = false)
    private String corrAccount;

    @OneToMany (mappedBy = "branch", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Account> accounts;

    public Branch() {
    }

    public Branch(String name, String extId, String bic, String corrAccount) {
        this.name = name;
        this.extId = extId;
        this.bic = bic;
        this.corrAccount = corrAccount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getCorrAccount() {
        return corrAccount;
    }

    public void setCorrAccount(String corrAccount) {
        this.corrAccount = corrAccount;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
