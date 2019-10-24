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

    @Column
    private String extBranchName;


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

    public String getExtBranchName() {
        return extBranchName;
    }

    public void setExtBranchName(String extBranchName) {
        this.extBranchName = extBranchName;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }
}
