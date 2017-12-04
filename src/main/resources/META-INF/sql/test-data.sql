

INSERT INTO `SUPPLIER` (`ID`,`NAME`,`STREETNAME`,`CITY`,`STATE`,`ZIPCODE`,`PHONE`,`EDI`,`PAYMENT`,`INCOTERMS`) VALUES (1,'Littleton Lumber','55 White Street','Littleton','MA','1460','978-486-9877','paper','net 30 days','none');
INSERT INTO `InventoryItem` (`ItemId`,`ItemName`,`ItemUPCNumber`,`ItemSKU`,`ManufacturerName`,`InventoryOnHand`,`WarehouseLocation`,`ReorderQuantity`,`ReorderUnit`,`BackorderDate`,`PerItemRetailSalePrice`,`SupplierId`) VALUES (1,'Battery',' 123456780011',' 12345',' Apple',10,' Arizona', 5,' Single',' 11/17/2017',1000,1);
INSERT INTO `PURCHASE_ORDER_HEADER` (`ID`,`SUPPLIER_ID`,`ORDER_TOTAL_PRICE`) VALUES (1,1,1000.00);
INSERT INTO `PURCHASE_ORDERS` (`ID`,`PURCHASE_ORDER_ID`,`ITEM_ID`,`QTY_ORDERED`,`DELIVERY_DATE`,`UNIT_PRICE`) VALUES (NULL,1,1,8,'11/30/2017',100.00);
