package ru.abr.dit.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Entity
public class UPGResponse {


    @Id
    @GeneratedValue(generator ="UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column (nullable = false, updatable = false)
    private Date createDateTime;

    @Column
    private String body;

    @ManyToOne
    private UPGSession session;

    @OneToOne
    private UPGRequest request;

    public UPGResponse() {
    }

    public UPGResponse(String body,  UPGRequest request) {
        this.createDateTime = new Date();
        this.body = body;
        this.request = request;
    }

    public UUID getId() {
        return id;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public UPGSession getSession() {
        return session;
    }

    public void setSession(UPGSession session) {
        this.session = session;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UPGRequest getRequest() {
        return request;
    }

    public void setRequest(UPGRequest request) {
        this.request = request;
    }


}
