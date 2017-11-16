insert into supplier (name, streetnum, streetname, city, state, zipcode, phone, edi, payment, incoterms) values ('Littleton Lumber', '55', 'White Street', 'Littleton', 'MA', '01460', '978-486-9877', 'paper', 'net 30 days', 'none');
INSERT INTO inventoryitem 
(`ItemName`,
`ItemUPCNumber`,
`ItemSKU`,
`ManufacturerName`,
`InventoryOnHand`,
`WarehouseLocation`,
`ReorderQuantity`,
`ReorderUnit`,
`BackorderDate`,
`PerItemRetailSalePrice`,
`SupplierId`)
VALUES
('Battery', '123456780011', '12345', 'Apple', 10, 'Arizona', '5', 'Single', ' 11/17/2017', 1000.00, 1);