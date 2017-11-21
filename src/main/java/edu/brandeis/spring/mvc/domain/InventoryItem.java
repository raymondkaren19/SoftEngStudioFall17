package edu.brandeis.spring.mvc.domain;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class InventoryItem {
    private Long Id;
    private String ItemName;
    private String ItemUPCNumber;
    private String ItemSKU;
    private String ManufacturerName;
    private int InventoryOnHand;
    private String WarehouseLocation;
    private int ReorderQuantity;
    private String ReorderUnit;
    private String BackorderDate;
    private float PerItemRetailSalePrice; 
    private int SupplierId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ITEMID")
    public Long getItemId() {
        return Id;
    }

    public void setItemId(Long id) {
        this.Id = id;
    }

    @NotEmpty(message="{validation.itemname.NotEmpty.message}")
    @Column(name = "ITEMNAME")
    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String name) {
        this.ItemName = name;
    }

    @NotEmpty(message="{validation.itemupcnumber.NotEmpty.message}")
    @Column(name = "ITEMUPCNUMBER")
    public String getItemUPCNumber() {
        return ItemUPCNumber;
    }

    public void setItemUPCNumber(String UPC) {
        this.ItemUPCNumber = UPC;
    }

    @NotEmpty(message="{validation.itemsku.NotEmpty.message}")
    @Column(name = "ITEMSKU")
    public String getItemSKU() {
        return ItemSKU;
    }

    public void setItemSKU(String itemSKU) {
        ItemSKU = itemSKU;
    }

    @NotEmpty(message="{validation.manufacturername.NotEmpty.message}")
    @Column(name = "MANUFACTURERNAME")
    public String getManufacturerName() {
        return ManufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        ManufacturerName = manufacturerName;
    }

    @NotEmpty(message="{validation.inventoryonhand.NotEmpty.message}")
    @Column(name = "INVENTORYONHAND")
    public int getInventoryOnHand() {
        return InventoryOnHand;
    }

    public void setInventoryOnHand(int inventoryOnHand) {
        InventoryOnHand = inventoryOnHand;
    }

    @NotEmpty(message="{validation.warehouselocation.NotEmpty.message}")
    @Column(name = "WAREHOUSELOCATION")
    public String getWarehouseLocation() {
        return WarehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        WarehouseLocation = warehouseLocation;
    }

    @NotEmpty(message="{validation.reorderquantity.NotEmpty.message}")
    @Column(name = "REORDERQUANTITY")
    public int getReorderQuantity() {
        return ReorderQuantity;
    }

    public void setReorderQuantity(int reorderQuantity) {
        ReorderQuantity = reorderQuantity;
    }

    @NotEmpty(message="{validation.reorderunit.NotEmpty.message}")
    @Column(name = "REORDERUNIT")
    public String getReorderUnit() {
        return ReorderUnit;
    }

    public void setReorderUnit(String reorderUnit) {
        ReorderUnit = reorderUnit;
    }

    @NotEmpty(message="{validation.backorderdate.NotEmpty.message}")
    @Column(name = "BACKORDERDATE")
    public String getBackorderDate() {
        return BackorderDate;
    }

    public void setBackorderDate(String backorderDate) {
        BackorderDate = backorderDate;
    }

    @NotEmpty(message="{validation.peritemretailsaleprice.NotEmpty.message}")
    @Column(name = "PERITEMRETAILSALEPRICE")
    public float getPerItemRetailSalePrice() {
        return PerItemRetailSalePrice;
    }

    public void setPerItemRetailSalePrice(float perItemRetailSalePrice) {
        PerItemRetailSalePrice = perItemRetailSalePrice;
    }

    @NotEmpty(message="{validation.supplierid.NotEmpty.message}")
    @Column(name = "SUPPLIERID")
    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        SupplierId = supplierId;
    }

}
