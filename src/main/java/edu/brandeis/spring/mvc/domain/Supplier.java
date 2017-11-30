package edu.brandeis.spring.mvc.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier")
public class Supplier {
    private Long ID;
    private String name;
    private String street ;
    private String city;
    private String state;
    private String zipcode;
    private String phone;
    private String edi;
    private String payment;
    private String incoterms;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getID() {
        return ID;
    }
    public void setID(Long id) {
        this.ID = id;
    }

//    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "NAME")
    public String getName() {
        return name;
    }
    public void setName(String sname) {
        name = sname;
    }
    
//    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "STREETNAME")
    public String getStreet() {
        return street;
    }
    public void setStreet(String sTREETNAME) {
        street = sTREETNAME;
    }
    
//    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }
    public void setCity(String cITY) {
        city = cITY;
    }
    
//    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "STATE")
    public String getState() {
        return state;
    }
    public void setState(String sTATE) {
        state = sTATE;
    }
    
//    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "ZIPCODE")
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zIPCODE) {
        zipcode = zIPCODE;
    }
    
//    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String pHONE) {
        phone = pHONE;
    }
    
//    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "EDI")
    public String getEdi() {
        return edi;
    }
    public void setEdi(String eDI) {
        edi = eDI;
    }
    
//    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "PAYMENT")
    public String getPayment() {
        return payment;
    }
    public void setPayment(String pAYMENT) {
        payment = pAYMENT;
    }    
    
    @Column(name = "INCOTERMS")
    public String getIncoterms() {
        return incoterms;
    }
    public void setIncoterms(String iNCOTERMS) {
        incoterms = iNCOTERMS;
    }
}
