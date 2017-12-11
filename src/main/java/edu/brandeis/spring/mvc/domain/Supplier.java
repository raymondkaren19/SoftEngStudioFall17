package edu.brandeis.spring.mvc.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "SUPPLIER")
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
    private Set<PurchaseOrderHeader> purchaseOrderHeader = new HashSet<PurchaseOrderHeader>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Range(min=1, message="{validation.id.Size.message}")
    @Column(name = "ID")
    public Long getID() {
        return ID;
    }
    public void setID(Long id) {
        this.ID = id;
    }

    @NotEmpty(message="{validation.name.NotEmpty.message}")
    @Column(name = "NAME")
    public String getName() {
        return name;
    }
    public void setName(String sname) {
        name = sname;
    }
    
    @NotEmpty(message="{validation.street.NotEmpty.message}")
    @Column(name = "STREETNAME")
    public String getStreet() {
        return street;
    }
    public void setStreet(String sTREETNAME) {
        street = sTREETNAME;
    }
    
    @NotEmpty(message="{validation.city.NotEmpty.message}")
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }
    public void setCity(String cITY) {
        city = cITY;
    }
    
    @NotEmpty(message="{validation.state.NotEmpty.message}")
    @Column(name = "STATE")
    public String getState() {
        return state;
    }
    public void setState(String sTATE) {
        state = sTATE;
    }
    
    @NotEmpty(message="{validation.zipcode.NotEmpty.message}")
    @Column(name = "ZIPCODE")
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zIPCODE) {
        zipcode = zIPCODE;
    }
    
    @NotEmpty(message="{validation.phone.NotEmpty.message}")
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String pHONE) {
        phone = pHONE;
    }
    
    @NotEmpty(message="{validation.edi.NotEmpty.message}")
    @Column(name = "EDI")
    public String getEdi() {
        return edi;
    }
    public void setEdi(String eDI) {
        edi = eDI;
    }
    
    @NotEmpty(message="{validation.payment.NotEmpty.message}")
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
    
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
   	public Set<PurchaseOrderHeader> getPurchaseOrderHeader() {
   		return this.purchaseOrderHeader;
   	}

   	@Transactional
   	public void purchaseOrderHeader(Set<PurchaseOrderHeader> purchaseOrderHeader) {
   		this.purchaseOrderHeader = purchaseOrderHeader;
   	}

   	@Transactional
   	public void addPurchaseOrders(PurchaseOrderHeader purchaseOrderHeader) {
   		purchaseOrderHeader.setSupplier(this);
   		getPurchaseOrderHeader().add(purchaseOrderHeader);
   	}

   	public void removePurchaseOrders(PurchaseOrderHeader purchaseOrderHeader) {
   		getPurchaseOrderHeader().remove(purchaseOrderHeader);
   	}
}
