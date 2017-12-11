package edu.brandeis.spring.mvc.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PURCHASE_ORDERS")
public class PurchaseOrders implements Serializable {
	private Long ID;
	private PurchaseOrderHeader purchaseOrderHeader;
	private InventoryItem inventoryItem;
	private Long itemId;
	private int qtyOrdered;
	private Date deliveryDate;
	private float unitPrice;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PURCHASE_ORDER_ID")
	public PurchaseOrderHeader getPurchaseOrderHeader() {
		return this.purchaseOrderHeader;
	}

	public void setPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader) {
		this.purchaseOrderHeader = purchaseOrderHeader;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	public InventoryItem getInventoryItem() {
		return this.inventoryItem;
	}

	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}
	
	@Range(min = 1, message = "{validation.id.Size.message}")
	@Column(name = "ITEM_ID")
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "QTY_ORDERED")
	public int getQtyOrdered() {
		return this.qtyOrdered;
	}

	public void setQtyOrdered(int qtyOrdered) {
		this.qtyOrdered = qtyOrdered;

	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DELIVERY_DATE")
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "UNIT_PRICE")
	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "PurchaseOrders [id=" + ID + ", purchaseOrderHeader=" + purchaseOrderHeader + ", inventoryItem=" + inventoryItem
				+ ", qtyOrdered=" + qtyOrdered + ", deliveryDate=" + deliveryDate + ", unitPrice=" + unitPrice + "]";
	}



}