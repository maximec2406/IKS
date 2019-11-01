package ru.abr.dit.Models.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Entity
public class UPGResponse {


    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column (nullable = false, updatable = false)
    private Timestamp createDateTime;

    @Lob
    @Column //(columnDefinition = "CLOB")
    private String body;

    @ManyToOne
    private UPGSession session;

    @OneToOne
    private UPGRequest request;

    public UPGResponse() {
    }

    public UPGResponse(String body,  UPGRequest request) {
        this.createDateTime = Timestamp.valueOf(LocalDateTime.now());
        this.body = body;
        this.request = request;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
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
