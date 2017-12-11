package edu.brandeis.spring.mvc.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PURCHASE_ORDER_HEADER")
public class PurchaseOrderHeader implements Serializable {
	private Long ID;
	private float orderTotalPrice;
	private Set<PurchaseOrders> purchaseOrderDetails = new HashSet<PurchaseOrders>();
	private Supplier supplier;
	private Long supplierId;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Range(min = 1, message = "{validation.id.Size.message}")
	@Column(name = "ID")
	public Long getId() {
		return ID;
	}

	public void setId(Long ID) {
		this.ID = ID;
	}

	@OneToMany(mappedBy = "purchaseOrderHeader", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	public Set<PurchaseOrders> getPurchaseOrderDetails() {
		return this.purchaseOrderDetails;
	}

	@Transactional
	public void setPurchaseOrderDetails(Set<PurchaseOrders> purchaseOrderDetails) {
		this.purchaseOrderDetails = purchaseOrderDetails;
	}

	@Transactional
	public void addPurchaseOrderDetails(PurchaseOrders purchaseOrderDetails) {
		purchaseOrderDetails.setPurchaseOrderHeader(this);
		getPurchaseOrderDetails().add(purchaseOrderDetails);
	}

	public void removePurchaseOrderDetails(PurchaseOrders purchaseOrderDetail) {
		getPurchaseOrderDetails().remove(purchaseOrderDetail);
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUPPLIER_ID")
	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	@Range(min = 1, message = "{validation.id.Size.message}")
	@Column(name = "SUPPLIER_ID")
	public Long getSupplierId() {
		return supplierId;
	}

	public void setItemId(Long itemId) {
		this.supplierId = itemId;
	}

}