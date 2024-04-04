# Assumptions:
# 1) Discounts are percentage discounts, not absolute discounts (10% off, not $5.00 off)

CREATE TABLE Vendor (
    VendorID SERIAL,
    VendorName VARCHAR(255),
    VendorAddress TEXT,
    VendorPhoneNumber VARCHAR(255),
    VendorEmail VARCHAR(255),
    PRIMARY KEY (VendorID)
);

CREATE TABLE VendorBusiness (
    VendorID BIGINT UNSIGNED,
    ContractNumber INT UNIQUE,
    PRIMARY KEY (VendorID),
    FOREIGN KEY (VendorID) REFERENCES Vendor(VendorID)
);

CREATE TABLE VendorPrivate (
    VendorID BIGINT UNSIGNED,
    PersonSSN INT UNIQUE,
    PRIMARY KEY (VendorID),
    FOREIGN KEY (VendorID) REFERENCES Vendor(VendorID)
);

CREATE TABLE Category (
    CategoryID SERIAL,
    CategoryTitle VARCHAR(255),
    PRIMARY KEY (CategoryID)
);

CREATE TABLE Product (
    SKU VARCHAR(255),
    ProductName VARCHAR(255),
    Stock INT,
    ProductDescription TEXT,
    UnitPrice DECIMAL(8, 2),
    Discount DECIMAL(3, 2),
    VendorID BIGINT UNSIGNED,
    CategoryID BIGINT UNSIGNED,
    PRIMARY KEY (SKU),
    FOREIGN KEY (VendorID) REFERENCES Vendor (VendorID),
    FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID)
);

CREATE TABLE Liquor (
    CategoryID SERIAL,
    LiquorTax DECIMAL(3, 2),
    FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID),
    PRIMARY KEY (CategoryID)
);

CREATE TABLE Grocery (
    CategoryID SERIAL,
    SalesTax DECIMAL(2, 2),
    FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID),
    PRIMARY KEY (CategoryID)
);

CREATE TABLE Department (
    DepartmentID SERIAL,
	DeptartmentName VARCHAR(255),
    ManagerStartDate DATE,
    ManagerEndDate DATE,
    ManagerSSN INT,
    PRIMARY KEY (DepartmentID)
);

CREATE TABLE Employee (
    EmployeeSSN INT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Username VARCHAR(255) UNIQUE,
    Passwd VARCHAR(255),
    StartDate DATE,
    EndDate DATE,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(255),
    EmpAddress TEXT,
    SupervisorSSN INT,
    DeptID BIGINT UNSIGNED,
    FOREIGN KEY (SupervisorSSN) REFERENCES Employee (EmployeeSSN),
    FOREIGN KEY (DeptID) REFERENCES Department (DepartmentID)
);

CREATE TABLE Customer (
	CustomerID SERIAL,
    PRIMARY KEY (CustomerID)
    );

CREATE TABLE Transactions (
    TransactionID SERIAL,
    OccuredAt DATETIME,
    Total DECIMAL(8, 2),
    PaymentMethod VARCHAR(255),
    EmployeeSSN INT,
    CustomerID BIGINT UNSIGNED,
    PRIMARY KEY (TransactionID),
    FOREIGN KEY (EmployeeSSN) REFERENCES Employee (EmployeeSSN),
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);

CREATE TABLE TransactionProducts (
    TransactionID BIGINT UNSIGNED,
    SKU VARCHAR(255),
    Quantity INT,
    OverallDiscount DECIMAL(3, 2),
	PRIMARY KEY (TransactionID, SKU),
    FOREIGN KEY (TransactionID) REFERENCES Transactions (TransactionID),
    FOREIGN KEY (SKU) REFERENCES Product (SKU)
);

CREATE TABLE LoyaltyMember(
    CustomerID BIGINT UNSIGNED,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    PhoneNumber VARCHAR(255),
    PRIMARY KEY (CustomerID),
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);

CREATE TABLE Nonmember(
	CustomerID BIGINT UNSIGNED,
    PRIMARY KEY (CustomerID),
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);

ALTER TABLE Department
ADD FOREIGN KEY (ManagerSSN) REFERENCES Employee (EmployeeSSN);
