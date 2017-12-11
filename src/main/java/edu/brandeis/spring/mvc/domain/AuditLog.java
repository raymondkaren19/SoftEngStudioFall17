package edu.brandeis.spring.mvc.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "AuditLog")

public class AuditLog {

	
    private Long id;   

    @Length(max = 10)
    private String modifiedBy;   
    
    private String modifiedDate;
    
    private String eventType;
    
    @Length(max = 10)
    private String createdBy = "Auto";   // will modify these when security is implemented
    
    private String createdDate = "Today";
    
    private String detail;

    public AuditLog() {
    }

        
    public AuditLog( String event, String detail, String username) {        
        this.eventType = event;
        this.detail = detail;
        this.modifiedBy =  username;
        this.modifiedDate =  DateTime.now().toString();
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "ModifiedBy")    
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "ModifiedDate")
    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    
    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "CreatedBy")    
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "CreatedDate")
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "EventType")
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String action) {
        this.eventType = action;
    }
    
   
    @Column(name = "Detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String eDetail) {
        this.detail = eDetail;
    }
}
