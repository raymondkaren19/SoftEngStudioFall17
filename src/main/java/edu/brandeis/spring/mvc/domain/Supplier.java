package edu.brandeis.spring.mvc.domain;

import static javax.persistence.GenerationType.IDENTITY;

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
    private Integer id;

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
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer sid) {
        this.id = sid;
    }
    
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String sTREETNAME) {
		street = sTREETNAME;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String cITY) {
		city = cITY;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "STATE")
	public String getState() {
		return state;
	}
	public void setState(String sTATE) {
		state = sTATE;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "ZIPCODE")
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zIPCODE) {
		zipcode = zIPCODE;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "PHONE")
	public int getPhone() {
		return phone;
	}
	public void setPhone(int pHONE) {
		phone = pHONE;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "EDI")
	public String getEdi() {
		return edi;
	}
	public void setEdi(String eDI) {
		edi = eDI;
	}
	
	@NotEmpty(message="{validation.itemname.NotEmpty.message}")
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
