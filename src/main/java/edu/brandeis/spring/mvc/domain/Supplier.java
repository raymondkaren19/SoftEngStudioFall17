package edu.brandeis.spring.mvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Supplier")
public class Supplier {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer ID;

    private String name;
    
    private String street ;
    //private String streetName ;
    private String city;
    private String state;
    private String zipcode;
    private int phone;
    private String edi;
    private String payment;
    private String incoterms;
    
    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String sname) {
		name = sname;
	}
	
	/* 
    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "STREETNUM")
	public Integer getSTREETNUM() {
		return streetNum;
	}
	public void setSTREETNUM(Integer sTREETNUM) {
		streetNum = sTREETNUM;
	}*/
	
	// combined streetnumber and street name
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "STREETNAME")
	public String getSTREET() {
		return street;
	}
	public void setSTREET(String sTREETNAME) {
		street = sTREETNAME;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "CITY")
	public String getCITY() {
		return city;
	}
	public void setCITY(String cITY) {
		city = cITY;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "STATE")
	public String getSTATE() {
		return state;
	}
	public void setSTATE(String sTATE) {
		state = sTATE;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "ZIPCODE")
	public String getZIPCODE() {
		return zipcode;
	}
	public void setZIPCODE(String zIPCODE) {
		zipcode = zIPCODE;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "PHONE")
	public int getPHONE() {
		return phone;
	}
	public void setPHONE(int pHONE) {
		phone = pHONE;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "EDI")
	public String getEDI() {
		return edi;
	}
	public void setEDI(String eDI) {
		edi = eDI;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "PAYMENT")
	public String getPAYMENT() {
		return payment;
	}
	public void setPAYMENT(String pAYMENT) {
		payment = pAYMENT;
	}	
	
    @Column(name = "INCOTERMS")
	public String getINCOTERMS() {
		return incoterms;
	}
	public void setINCOTERMS(String iNCOTERMS) {
		incoterms = iNCOTERMS;
	}
    
    
    
    

}
