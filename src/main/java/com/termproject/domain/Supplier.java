package com.termproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supplier {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer ID;

    private String Name;
    
    private Integer STREETNUM ;
    private String STREETNAME ;
    private String CITY;
    private String STATE;
    private String ZIPCODE;
    private int PHONE;
    private String EDI;
    private String PAYMENT;
    private String INCOTERMS;
    
    
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getSTREETNUM() {
		return STREETNUM;
	}
	public void setSTREETNUM(Integer sTREETNUM) {
		STREETNUM = sTREETNUM;
	}
	public String getSTREETNAME() {
		return STREETNAME;
	}
	public void setSTREETNAME(String sTREETNAME) {
		STREETNAME = sTREETNAME;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getZIPCODE() {
		return ZIPCODE;
	}
	public void setZIPCODE(String zIPCODE) {
		ZIPCODE = zIPCODE;
	}
	public int getPHONE() {
		return PHONE;
	}
	public void setPHONE(int pHONE) {
		PHONE = pHONE;
	}
	public String getEDI() {
		return EDI;
	}
	public void setEDI(String eDI) {
		EDI = eDI;
	}
	public String getPAYMENT() {
		return PAYMENT;
	}
	public void setPAYMENT(String pAYMENT) {
		PAYMENT = pAYMENT;
	}
	public String getINCOTERMS() {
		return INCOTERMS;
	}
	public void setINCOTERMS(String iNCOTERMS) {
		INCOTERMS = iNCOTERMS;
	}
    
    
    
    

}
