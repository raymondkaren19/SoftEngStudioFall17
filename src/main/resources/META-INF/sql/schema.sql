CREATE TABLE SUPPLIER (
       ID INT NOT NULL AUTO_INCREMENT
     , NAME VARCHAR(100) NOT NULL
     , STREETNUM INT NOT NULL
     , STREETNAME VARCHAR(200) NOT NULL
     , CITY VARCHAR(100) NOT NULL
     , STATE VARCHAR(2) NOT NULL
     , ZIPCODE VARCHAR(5) NOT NULL
     , PHONE VARCHAR(20) NOT NULL
     , EDI VARCHAR(10) NOT NULL
     , PAYMENT VARCHAR(100) NOT NULL
     , INCOTERMS VARCHAR(250)
     , PRIMARY KEY (ID)
);

CREATE TABLE InventoryItem (
  ItemId int(11) unsigned NOT NULL AUTO_INCREMENT,
  ItemName varchar(255) NOT NULL,
  ItemUPCNumber  varchar(12) NOT NULL,
  ItemSKU  varchar(25) NOT NULL,
  ManufacturerName VARCHAR(50) NOT NULL,
  InventoryOnHand  int(11) NOT NULL,
  WarehouseLocation VARCHAR(50) NOT NULL,
  ReorderQuantity  int(11) NOT NULL,
  ReorderUnit VARCHAR(20) NOT NULL,
  BackorderDate VARCHAR (20) NOT NULL,
  PerItemRetailSalePrice float(11) NOT NULL, 
  SupplierId  INT,   
  PRIMARY KEY (ItemId), 
  FOREIGN KEY (SupplierId) 
      REFERENCES SUPPLIER (ID)
      ON DELETE CASCADE
);

CREATE TABLE PURCHASE_ORDER_HEADER (
    ID int(11) NOT NULL AUTO_INCREMENT
  , SUPPLIER_ID int(11) NOT NULL
  , ORDER_TOTAL_PRICE decimal(19,6) DEFAULT NULL
  , PRIMARY KEY (ID)
  , CONSTRAINT FK_PURCHASE_ORDER_HEADER_1 FOREIGN KEY (SUPPLIER_ID) 
	       REFERENCES SUPPLIER (ID)
);

