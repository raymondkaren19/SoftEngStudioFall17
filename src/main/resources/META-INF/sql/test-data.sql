-- -----------------------------------------------------
-- Data for table `InventoryDB`.`SUPPLIER`
-- -----------------------------------------------------
START TRANSACTION;
USE `InventoryDB`;
INSERT INTO `InventoryDB`.`SUPPLIER` (`ID`, `NAME`, `STREETNAME`, `CITY`, `STATE`, `ZIPCODE`, `PHONE`, `EDI`, `PAYMENT`, `INCOTERMS`) VALUES (1, 'Littleton Lumber', '55 White Street', 'Littleton', 'MA', '1460', '978-486-9877', 'paper', 'net 30 days', 'none');

COMMIT;


-- -----------------------------------------------------
-- Data for table `InventoryDB`.`InventoryItem`
-- -----------------------------------------------------
START TRANSACTION;
USE `InventoryDB`;
INSERT INTO `InventoryDB`.`InventoryItem` (`ItemId`, `ItemName`, `ItemUPCNumber`, `ItemSKU`, `ManufacturerName`, `InventoryOnHand`, `WarehouseLocation`, `ReorderQuantity`, `ReorderUnit`, `BackorderDate`, `PerItemRetailSalePrice`, `SupplierId`) VALUES (1, 'Battery', '123456780011', '12345', 'Apple', 10, 'Arizona',  5, 'Single', '2017-11-17', 1000, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `InventoryDB`.`PURCHASE_ORDER_HEADER`
-- -----------------------------------------------------
START TRANSACTION;
USE `InventoryDB`;
INSERT INTO `InventoryDB`.`PURCHASE_ORDER_HEADER` (`ID`, `SUPPLIER_ID`, `ORDER_TOTAL_PRICE`) VALUES (1, 1, 1000.00);

COMMIT;


-- -----------------------------------------------------
-- Data for table `InventoryDB`.`PURCHASE_ORDERS`
-- -----------------------------------------------------
START TRANSACTION;
USE `InventoryDB`;
INSERT INTO `InventoryDB`.`PURCHASE_ORDERS` (`ID`, `PURCHASE_ORDER_ID`, `ITEM_ID`, `QTY_ORDERED`, `DELIVERY_DATE`, `UNIT_PRICE`) VALUES (DEFAULT, NULL, NULL, 8, '2017-11-30', 100.00);

COMMIT;

