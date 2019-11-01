package ru.abr.dit.Models.Entities;

import javax.persistence.*;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;

    @Column (nullable = false, unique = false)
    private String name;

    @Column (nullable = false, unique = true)
    private String bic;

    @Column (nullable = false, unique = false)
    private String corrAccount;

    @Column
    private String city;

    @Column
    private String settlementType;

    public Bank() {
    }

    public Bank(String name, String bic, String corrAccount, String city, String settlementType) {
        this.name = name;
        this.bic = bic;
        this.corrAccount = corrAccount;
        this.city = city;
        this.settlementType = settlementType;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }
}
