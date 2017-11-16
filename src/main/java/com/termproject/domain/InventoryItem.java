package com.termproject.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InventoryItem {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer ItemId;

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
    

	public Integer getItemId() {
		return ItemId;
	}

	public void setItemId(Integer id) {
		this.ItemId = id;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String name) {
		this.ItemName = name;
	}

	public String getItemUPCNumber() {
		return ItemUPCNumber;
	}

	public void setItemUPCNumber(String UPC) {
		this.ItemUPCNumber = UPC;
	}

	public String getItemSKU() {
		return ItemSKU;
	}

	public void setItemSKU(String itemSKU) {
		ItemSKU = itemSKU;
	}

	public String getManufacturerName() {
		return ManufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		ManufacturerName = manufacturerName;
	}

	public int getInventoryOnHand() {
		return InventoryOnHand;
	}

	public void setInventoryOnHand(int inventoryOnHand) {
		InventoryOnHand = inventoryOnHand;
	}

	public String getWarehouseLocation() {
		return WarehouseLocation;
	}

	public void setWarehouseLocation(String warehouseLocation) {
		WarehouseLocation = warehouseLocation;
	}

	public int getReorderQuantity() {
		return ReorderQuantity;
	}

	public void setReorderQuantity(int reorderQuantity) {
		ReorderQuantity = reorderQuantity;
	}

	public String getReorderUnit() {
		return ReorderUnit;
	}

	public void setReorderUnit(String reorderUnit) {
		ReorderUnit = reorderUnit;
	}

	public String getBackorderDate() {
		return BackorderDate;
	}

	public void setBackorderDate(String backorderDate) {
		BackorderDate = backorderDate;
	}

	public float getPerItemRetailSalePrice() {
		return PerItemRetailSalePrice;
	}

	public void setPerItemRetailSalePrice(float perItemRetailSalePrice) {
		PerItemRetailSalePrice = perItemRetailSalePrice;
	}

	public int getSupplierId() {
		return SupplierId;
	}

	public void setSupplierId(int supplierId) {
		SupplierId = supplierId;
	}

}
