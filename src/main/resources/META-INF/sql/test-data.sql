-- -----------------------------------------------------
-- Data for table `InventoryDB`.`SUPPLIER`
-- -----------------------------------------------------
START TRANSACTION;
USE `InventoryDB`;
INSERT INTO `InventoryDB`.`SUPPLIER` (`ID`, `NAME`, `STREETNAME`, `CITY`, `STATE`, `ZIPCODE`, `PHONE`, `EDI`, `PAYMENT`, `INCOTERMS`) VALUES (1, 'Littleton Lumber', '55 White Street', 'Littleton', 'MA', '1460', '978-486-9877', 'paper', 'net 30 days', 'none');
INSERT INTO `InventoryDB`.`SUPPLIER` (`ID`, `NAME`, `STREETNAME`, `CITY`, `STATE`, `ZIPCODE`, `PHONE`, `EDI`, `PAYMENT`, `INCOTERMS`) VALUES (2, 'Littleton Lumber 2', '55 White Street', 'Littleton', 'MA', '1460', '978-486-9877', 'paper', 'net 30 days', 'none');
COMMIT;


-- -----------------------------------------------------
-- Data for table `InventoryDB`.`InventoryItem`
-- -----------------------------------------------------
START TRANSACTION;
USE `InventoryDB`;
INSERT INTO `InventoryDB`.`InventoryItem` (`ItemId`, `ItemName`, `ItemUPCNumber`, `ItemSKU`, `ManufacturerName`, `InventoryOnHand`, `WarehouseLocation`, `ReorderQuantity`, `ReorderUnit`, `BackorderDate`, `PerItemRetailSalePrice`, `SupplierId`) VALUES (1, 'Battery',  '123456780011', '12345', 'Apple', 10, 'Arizona',  5, 'Single', '2017-11-17', 1000, 1);
INSERT INTO `InventoryDB`.`InventoryItem` (`ItemId`, `ItemName`, `ItemUPCNumber`, `ItemSKU`, `ManufacturerName`, `InventoryOnHand`, `WarehouseLocation`, `ReorderQuantity`, `ReorderUnit`, `BackorderDate`, `PerItemRetailSalePrice`, `SupplierId`) VALUES (2, 'Keyboard', '123456780012', '127895', 'Google', 10, 'CA', '5', 'Single', ' 12/04/2017', 100.00, 1);
INSERT INTO `InventoryDB`.`InventoryItem` (`ItemId`, `ItemName`, `ItemUPCNumber`, `ItemSKU`, `ManufacturerName`, `InventoryOnHand`, `WarehouseLocation`, `ReorderQuantity`, `ReorderUnit`, `BackorderDate`, `PerItemRetailSalePrice`, `SupplierId`) VALUES (3, 'Soundbar', '123985678002', '120895', 'Bose', 1, 'MA', '3', 'Single', ' 1/04/2018', 600.00, 1);
INSERT INTO `InventoryDB`.`InventoryItem` (`ItemId`, `ItemName`, `ItemUPCNumber`, `ItemSKU`, `ManufacturerName`, `InventoryOnHand`, `WarehouseLocation`, `ReorderQuantity`, `ReorderUnit`, `BackorderDate`, `PerItemRetailSalePrice`, `SupplierId`) VALUES (4, 'Tablet',    '123256780012', '127825', 'Microsoft', 5, 'AN', '6', 'Single', ' 12/04/2017', 300.00, 1);
INSERT INTO `InventoryDB`.`InventoryItem` (`ItemId`, `ItemName`, `ItemUPCNumber`, `ItemSKU`, `ManufacturerName`, `InventoryOnHand`, `WarehouseLocation`, `ReorderQuantity`, `ReorderUnit`, `BackorderDate`, `PerItemRetailSalePrice`, `SupplierId`) VALUES (5, 'Headphone', '123498780012', '187895', 'Beats', 4, 'NC', '5', 'Single', ' 12/04/2017', 210.00, 2);
INSERT INTO `InventoryDB`.`InventoryItem` (`ItemId`, `ItemName`, `ItemUPCNumber`, `ItemSKU`, `ManufacturerName`, `InventoryOnHand`, `WarehouseLocation`, `ReorderQuantity`, `ReorderUnit`, `BackorderDate`, `PerItemRetailSalePrice`, `SupplierId`) VALUES (6, 'Echo',       '120956780012', '167895', 'Amazon', 3, 'GA', '5', 'Single', ' 12/04/2017', 89.00, 2);
INSERT INTO `InventoryDB`.`InventoryItem` (`ItemId`, `ItemName`, `ItemUPCNumber`, `ItemSKU`, `ManufacturerName`, `InventoryOnHand`, `WarehouseLocation`, `ReorderQuantity`, `ReorderUnit`, `BackorderDate`, `PerItemRetailSalePrice`, `SupplierId`) VALUES (7, 'Chromecast', '121856780012', '127395', 'Google', 8, 'CA', '5', 'Single', ' 12/04/2017', 30.00, 2);


COMMIT;


-- -----------------------------------------------------
-- Data for table `InventoryDB`.`PURCHASE_ORDER_HEADER`
-- -----------------------------------------------------
START TRANSACTION;
USE `InventoryDB`;
INSERT INTO `InventoryDB`.`PURCHASE_ORDER_HEADER` (`ID`, `SUPPLIER_ID`, `ORDER_TOTAL_PRICE`) VALUES (1, 1, 1000.00);
INSERT INTO `InventoryDB`.`PURCHASE_ORDER_HEADER` (`ID`, `SUPPLIER_ID`, `ORDER_TOTAL_PRICE`) VALUES (2, 1, 100.00);

COMMIT;


-- -----------------------------------------------------
-- Data for table `InventoryDB`.`PURCHASE_ORDERS`
-- -----------------------------------------------------
START TRANSACTION;
USE `InventoryDB`;
INSERT INTO `InventoryDB`.`PURCHASE_ORDERS` (`ID`, `PURCHASE_ORDER_ID`, `ITEM_ID`, `QTY_ORDERED`, `DELIVERY_DATE`, `UNIT_PRICE`) VALUES (DEFAULT, 1, 1, 8, '2017-11-30', 100.00);
INSERT INTO `InventoryDB`.`PURCHASE_ORDERS` (`ID`, `PURCHASE_ORDER_ID`, `ITEM_ID`, `QTY_ORDERED`, `DELIVERY_DATE`, `UNIT_PRICE`) VALUES (DEFAULT, 2, 1, 8, '2017-12-22', 200.00);

COMMIT;
