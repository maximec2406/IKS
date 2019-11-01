package ru.abr.dit.Models.Entities;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String bic;

    @Column
    private String extid;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Org org;

    public Account() {
    }

    public Account(String account, String bic) {
        this.account = account;
        this.bic = bic;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getExtid() {
        return extid;
    }

    public void setExtid(String extid) {
        this.extid = extid;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
