package ru.abr.dit.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table
@Entity
public class UPGRequest {

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

    @Column (nullable = false)
    private String status;

    @Column(length = 3000)
    private String body;

    @OneToOne(mappedBy = "request")
    private UPGResponse response ;

    @ManyToOne
    private UPGSession session;

    @Column
    private String type;

    public UPGRequest() {
    }

    public UPGRequest(String body, String type) {
        this.createDateTime = new Date();
        this.status = "new";
        this.type = type;
        this.body = body;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public UPGResponse getResponse() {
        return response;
    }

    public void setResponse(UPGResponse response) {
        this.response = response;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
