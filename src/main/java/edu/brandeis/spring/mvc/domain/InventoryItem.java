package edu.brandeis.spring.mvc.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "InventoryItem")
public class InventoryItem {
    private Long itemId;
    private String itemName;
    private String itemUPCNumber;
    private String itemSKU;
    private String manufacturerName;
    private int inventoryOnHand;
    private String warehouseLocation;
    private int reorderQuantity;
    private String reorderUnit;
    private String backorderDate;
    private float perItemRetailSalePrice; 
    private int supplierId;
    private Set<PurchaseOrders> purchaseOrders= new HashSet<PurchaseOrders>();
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Range(min=1, message="{validation.id.Size.message}")
    @Column(name = "ITEMID")
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long id) {
        this.itemId = id;
    }

    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "ITEMNAME")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }

    @NotEmpty(message="{validation.itemupcnumber.NotEmpty.message}")
    @Column(name = "ITEMUPCNUMBER")
    public String getItemUPCNumber() {
        return itemUPCNumber;
    }

    public void setItemUPCNumber(String UPC) {
        this.itemUPCNumber = UPC;
    }

    @NotEmpty(message="{validation.itemsku.NotEmpty.message}")
    @Column(name = "ITEMSKU")
    public String getItemSKU() {
        return itemSKU;
    }

    public void setItemSKU(String SKU) {
        itemSKU = SKU;
    }

    @NotEmpty(message="{validation.manufacturername.NotEmpty.message}")
    @Column(name = "MANUFACTURERNAME")
    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufName) {
    	manufacturerName = manufName;
    }

    @Range(min=0, message="{validation.inventoryonhand.Size.message}")
    @Column(name = "INVENTORYONHAND")
    public int getInventoryOnHand() {
        return inventoryOnHand;
    }

    public void setInventoryOnHand(int invenOnHand) {
        inventoryOnHand = invenOnHand;
    }

    @NotEmpty(message="{validation.warehouselocation.NotEmpty.message}")
    @Column(name = "WAREHOUSELOCATION")
    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String location) {
        warehouseLocation = location;
    }

    @Range(min=0, message="{validation.reorderquantity.Size.message}")
    @Column(name = "REORDERQUANTITY")
    public int getReorderQuantity() {
        return reorderQuantity;
    }

    public void setReorderQuantity(int quantity) {
        reorderQuantity = quantity;
    }

    @NotEmpty(message="{validation.reorderunit.NotEmpty.message}")
    @Column(name = "REORDERUNIT")
    public String getReorderUnit() {
        return reorderUnit;
    }

    public void setReorderUnit(String rUnit) {
        reorderUnit = rUnit;
    }

    @NotEmpty(message="{validation.backorderdate.NotEmpty.message}")
    @Column(name = "BACKORDERDATE")
    public String getBackorderDate() {
        return backorderDate;
    }

    public void setBackorderDate(String bDate) {
        backorderDate= bDate;
    }

    @Column(name = "PERITEMRETAILSALEPRICE")
    public float getPerItemRetailSalePrice() {
        return perItemRetailSalePrice;
    }

    public void setPerItemRetailSalePrice(float salePrice) {
        perItemRetailSalePrice = salePrice;
    }

    @Range(min=0, message="{validation.supplierid.Size.message}")
    @Column(name = "SUPPLIERID")
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int sId) {
    	supplierId = sId;
    }
    
    @OneToMany(mappedBy = "inventoryItem", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  	public Set<PurchaseOrders> getPurchaseOrders() {
  		return this.purchaseOrders;
  	}

  	@Transactional
  	public void setPurchaseOrders(Set<PurchaseOrders> purchaseOrders) {
  		this.purchaseOrders = purchaseOrders;
  	}

  	@Transactional
  	public void addPurchaseOrders(PurchaseOrders purchaseOrders) {
  		purchaseOrders.setInventoryItem(this);
  		getPurchaseOrders().add(purchaseOrders);
  	}

  	public void removePurchaseOrders(PurchaseOrders purchaseOrders) {
  		getPurchaseOrders().remove(purchaseOrders);
  	}

}
