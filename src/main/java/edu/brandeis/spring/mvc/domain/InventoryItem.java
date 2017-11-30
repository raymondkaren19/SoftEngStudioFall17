package edu.brandeis.spring.mvc.domain;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

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

    @Id
    @GeneratedValue(strategy = IDENTITY)
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

//    @NotEmpty(message="{validation.inventoryonhand.NotEmpty.message}")
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

//    @NotEmpty(message="{validation.reorderquantity.NotEmpty.message}")
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

//    @NotEmpty(message="{validation.peritemretailsaleprice.NotEmpty.message}")
    @Column(name = "PERITEMRETAILSALEPRICE")
    public float getPerItemRetailSalePrice() {
        return perItemRetailSalePrice;
    }

    public void setPerItemRetailSalePrice(float salePrice) {
        perItemRetailSalePrice = salePrice;
    }

//    @NotEmpty(message="{validation.supplierid.NotEmpty.message}")
    @Column(name = "SUPPLIERID")
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int sId) {
    	supplierId = sId;
    }

}
