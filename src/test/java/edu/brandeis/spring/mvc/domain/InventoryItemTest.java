package edu.brandeis.spring.mvc.domain;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.*;

 /*   public class InventoryItemTest {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private InventoryItem item  = new InventoryItem();

    private Long   expId        = new Long(1);
    private String expName      = "name";
    private String expUpc       = "upc";
    private String expSku       = "sku";
    private String expMfg       = "mfg";
    private int    expInven     = 10;
    private String expLoc       = "loc";
    private int    expQty       = 20;
    private String expUnit      = "unit";
    private String expDate      = "date";
    private float  expPrice     = (float) 1.1;
    private int    expSupplier  = 1;

    @Before
    public void setUp() throws Exception {
        setDefaults();
    }

    @Test
    public void testGetSetInventoryItem() {
        setDefaults();
        Assert.assertEquals(expId,      item.getItemId());
        Assert.assertEquals(expName,    item.getItemName());
        Assert.assertEquals(expUpc,     item.getItemUPCNumber());
        Assert.assertEquals(expSku,     item.getItemSKU());
        Assert.assertEquals(expMfg,     item.getManufacturerName());
        Assert.assertEquals(expInven,   item.getInventoryOnHand());
        Assert.assertEquals(expLoc,     item.getWarehouseLocation());
        Assert.assertEquals(expQty,     item.getReorderQuantity());
        Assert.assertEquals(expUnit,    item.getReorderUnit());
        Assert.assertEquals(expDate,    item.getBackorderDate());
        Assert.assertEquals(expPrice,   item.getPerItemRetailSalePrice(), 0.01);
        Assert.assertEquals(expSupplier,item.getSupplierId());

        setAlternates();
        Assert.assertNotEquals(expId,      item.getItemId());
        Assert.assertNotEquals(expName,    item.getItemName());
        Assert.assertNotEquals(expUpc,     item.getItemUPCNumber());
        Assert.assertNotEquals(expSku,     item.getItemSKU());
        Assert.assertNotEquals(expMfg,     item.getManufacturerName());
        Assert.assertNotEquals(expInven,   item.getInventoryOnHand());
        Assert.assertNotEquals(expLoc,     item.getWarehouseLocation());
        Assert.assertNotEquals(expQty,     item.getReorderQuantity());
        Assert.assertNotEquals(expUnit,    item.getReorderUnit());
        Assert.assertNotEquals(expDate,    item.getBackorderDate());
        Assert.assertNotEquals(expPrice,   item.getPerItemRetailSalePrice(), 0.01);
        Assert.assertNotEquals(expSupplier,item.getSupplierId());

        setDefaults();
    }

    @Test
    public void testInventoryItemNotEmptyValidation() {
        setDefaults();
        Set<ConstraintViolation<InventoryItem>> constraintViolations = validator.validate(item);
        Assert.assertEquals(0, constraintViolations.size());

        setFailingNull();
        constraintViolations = validator.validate(item);
        Assert.assertEquals(7, constraintViolations.size());

        setDefaults();
    }

    @Test
    public void testInventoryItemRangeValidation() {
        setDefaults();
        Set<ConstraintViolation<InventoryItem>> constraintViolations = validator.validate(item);
        Assert.assertEquals(0, constraintViolations.size());

        setFailingRangeLow();
        constraintViolations = validator.validate(item);
        Assert.assertEquals(3, constraintViolations.size());

        setDefaults();
    }

    private void setDefaults() {
        item.setItemId(expId);
        item.setItemName(expName);
        item.setItemUPCNumber(expUpc);
        item.setItemSKU(expSku);
        item.setManufacturerName(expMfg);
        item.setInventoryOnHand(expInven);
        item.setWarehouseLocation(expLoc);
        item.setReorderQuantity(expQty);
        item.setReorderUnit(expUnit);
        item.setBackorderDate(expDate);
        item.setPerItemRetailSalePrice(expPrice);
        item.setSupplierId(expSupplier);
    }

    private void setAlternates() {
        item.setItemId(expId + 1);
        item.setItemName(expName + "Alternate");
        item.setItemUPCNumber(expUpc + "Alternate");
        item.setItemSKU(expSku + "Alternate");
        item.setManufacturerName(expMfg + "Alternate");
        item.setInventoryOnHand(expInven + 1);
        item.setWarehouseLocation(expLoc + "Alternate");
        item.setReorderQuantity(expQty + 1);
        item.setReorderUnit(expUnit + "Alternate");
        item.setBackorderDate(expDate + "Alternate");
        item.setPerItemRetailSalePrice(expPrice + 1);
        item.setSupplierId(expSupplier + 1);
    }

    private void setFailingNull() {
        item.setItemName(null);
        item.setItemUPCNumber(null);
        item.setItemSKU(null);
        item.setManufacturerName(null);
        item.setWarehouseLocation(null);
        item.setReorderUnit(null);
        item.setBackorderDate(null);
    }

    private void setFailingRangeLow() {
        item.setItemId((long) -1);
        item.setInventoryOnHand(-1);
        item.setSupplierId(-1);
    }
}

*/