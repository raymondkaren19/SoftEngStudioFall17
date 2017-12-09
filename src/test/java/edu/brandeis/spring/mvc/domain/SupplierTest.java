package edu.brandeis.spring.mvc.domain;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SupplierTest {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private Supplier supplier = new Supplier();

    private Long   expId        = new Long(1);
    private String expName      = "name";
    private String expStreet    = "street";
    private String expCity      = "city";
    private String expState     = "state";
    private String expZip       = "zip";
    private String expPhone     = "phone";
    private String expEdi       = "edi";
    private String expPayment   = "payment";
    private String expIncoterms = "incoterms";

    @Before
    public void setUp() throws Exception {
        setDefaults();
    }

    @Test
    public void testGetSetSupplier() {
        setDefaults();
        Assert.assertEquals(expId,          supplier.getID());
        Assert.assertEquals(expName,        supplier.getName());
        Assert.assertEquals(expStreet,      supplier.getStreet());
        Assert.assertEquals(expCity,        supplier.getCity());
        Assert.assertEquals(expState,       supplier.getState());
        Assert.assertEquals(expZip,         supplier.getZipcode());
        Assert.assertEquals(expPhone,       supplier.getPhone());
        Assert.assertEquals(expEdi,         supplier.getEdi());
        Assert.assertEquals(expPayment,     supplier.getPayment());
        Assert.assertEquals(expIncoterms,   supplier.getIncoterms());

        setAlternates();
        Assert.assertNotEquals(expId,          supplier.getID());
        Assert.assertNotEquals(expName,        supplier.getName());
        Assert.assertNotEquals(expStreet,      supplier.getStreet());
        Assert.assertNotEquals(expCity,        supplier.getCity());
        Assert.assertNotEquals(expState,       supplier.getState());
        Assert.assertNotEquals(expZip,         supplier.getZipcode());
        Assert.assertNotEquals(expPhone,       supplier.getPhone());
        Assert.assertNotEquals(expEdi,         supplier.getEdi());
        Assert.assertNotEquals(expPayment,     supplier.getPayment());
        Assert.assertNotEquals(expIncoterms,   supplier.getIncoterms());

        setDefaults();
    }

    @Test
    public void testSupplierNotEmptyValidation() {
        setDefaults();
        Set<ConstraintViolation<Supplier>> constraintViolations = validator.validate(supplier);
        Assert.assertEquals(0, constraintViolations.size());

        setFailingNull();
        constraintViolations = validator.validate(supplier);
        Assert.assertEquals(8, constraintViolations.size());

        setDefaults();
    }

    @Test
    public void testSupplierRangeValidation() {
        setDefaults();
        Set<ConstraintViolation<Supplier>> constraintViolations = validator.validate(supplier);
        Assert.assertEquals(0, constraintViolations.size());

        setFailingRangeLow();
        constraintViolations = validator.validate(supplier);
        Assert.assertEquals(1, constraintViolations.size());

        setDefaults();
    }

    private void setDefaults() {
        supplier.setID(expId);
        supplier.setName(expName);
        supplier.setStreet(expStreet);
        supplier.setCity(expCity);
        supplier.setState(expState);
        supplier.setZipcode(expZip);
        supplier.setPhone(expPhone);
        supplier.setEdi(expEdi);
        supplier.setPayment(expPayment);
        supplier.setIncoterms(expIncoterms);
    }

    private void setAlternates() {
        supplier.setID(expId + 1);
        supplier.setName(expName + "Alternate");
        supplier.setStreet(expStreet + "Alternate");
        supplier.setCity(expCity + "Alternate");
        supplier.setState(expState + "Alternate");
        supplier.setZipcode(expZip + "Alternate");
        supplier.setPhone(expPhone + "Alternate");
        supplier.setEdi(expEdi + "Alternate");
        supplier.setPayment(expPayment + "Alternate");
        supplier.setIncoterms(expIncoterms + "Alternate");
    }

    private void setFailingNull() {
        supplier.setName(null);
        supplier.setStreet(null);
        supplier.setCity(null);
        supplier.setState(null);
        supplier.setZipcode(null);
        supplier.setPhone(null);
        supplier.setEdi(null);
        supplier.setPayment(null);
        supplier.setIncoterms(null);
    }

    private void setFailingRangeLow() {
        supplier.setID((long) -1);
    }
}
