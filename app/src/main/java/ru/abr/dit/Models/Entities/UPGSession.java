package ru.abr.dit.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.abr.dit.Models.Entities.UPGRequest;
import ru.abr.dit.Models.Entities.UPGResponse;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table
@Entity
public class UPGSession {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String extId;

    @Column(nullable = false, updatable = false)
    private Date dateTime;

    @Column(nullable = false, updatable = false)
    private String login;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UPGRequest> requests;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UPGResponse> responses;

    public UPGSession() {
    }

    public UPGSession(String extId, Date dateTime, String login) {
        this.extId = extId;
        this.dateTime = dateTime;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<UPGRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<UPGRequest> requests) {
        this.requests = requests;
    }

    public List<UPGResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<UPGResponse> responses) {
        this.responses = responses;
    }
}
