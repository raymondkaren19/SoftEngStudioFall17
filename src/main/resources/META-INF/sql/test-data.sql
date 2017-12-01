insert into supplier
(name,
streetname,
city,
state,
zipcode,
phone,
edi,
payment,
incoterms)
values
('Littleton Lumber', '55 White Street', 'Littleton', 'MA', '01460', '978-486-9877', 'paper', 'net 30 days', 'none');

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

insert into PURCHASE_ORDER_HEADER (order_total_price) values (1000.00);
insert into PURCHASE_ORDERS (qty_ordered, delivery_date, unit_price) values ('8', 11/30/2017, 100.00);