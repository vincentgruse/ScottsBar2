# Assumptions:
# 1) Discounts are percentage discounts, not absolute discounts (10% off, not $5.00 off)
# Vendor Branch
CREATE TABLE VendorPrivate (
    VendorID SERIAL PRIMARY KEY,
    PersonSSN INT UNIQUE,
    FOREIGN KEY VendorID SERIAL REFERENCES Vendor(VendorID)
);

CREATE TABLE VendorBusiness (
    VendorID SERIAL PRIMARY KEY,
    ContractNumber INT UNIQUE,
    FOREIGN KEY VendorID SERIAL REFERENCES Vendor(VendorID)
);

CREATE TABLE Vendor (
    VendorID SERIAL PRIMARY KEY,
    VendorName VARCHAR(255),
    VendorAddress TEXT,
    VendorPhoneNumber VARCHAR(255),
    VendorEmail VARCHAR(255)
);

# Product
CREATE TABLE Product (
    SKU INT PRIMARY KEY,
    ProductName VARCHAR(255),
    Stock INT,
    ProductDescription TEXT,
    UnitPrice DECIMAL(8, 2),
    Discount DECIMAL(3, 2),
    VendorID INT,
    CategoryID INT,
    FOREIGN KEY (VendorID) REFERENCES Vendor (VendorID),
    FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID)
);

# Category Branch
CREATE TABLE Category (
    CategoryID SERIAL PRIMARY KEY,
    CategoryTitle VARCHAR(255),
);

CREATE TABLE Liquor (
    CategoryID SERIAL PRIMARY KEY,
    LiquorTax DECIMAL(3, 2),
    FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID)
) CREATE TABLE Grocery (
    CategoryID SERIAL PRIMARY KEY,
    SalesTax DECIMAL(2, 2),
    FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID)
);

# Transaction Branch
CREATE TABLE TransactionProducts (
    TransactionID INT,
    ProductSKU VARCHAR(255),
    PRIMARY KEY (TransactionID, ProductSKU),
    Quantity INT,
    OverallDiscount DECIMAL(3, 2),
    FOREIGN KEY (TransactionID) REFERENCES `Transaction` (TransactionID),
    FOREIGN KEY (ProductSKU) REFERENCES Product (SKU)
);

CREATE TABLE `Transaction` (
    TransactionID SERIAL PRIMARY KEY,
    OccuredAt DATETIME,
    Total DECIMAL(8, 2),
    PaymentMethod VARCHAR(255),
    EmployeeSSN INT,
    CustomerID INT,
    FOREIGN KEY (EmployeeSSN) REFERENCES Employee (EmployeeSSN),
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);

# Customer Branch
CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY,
);

CREATE TABLE LoyaltyMember(
    PRIMARY KEY CustomerID,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    PhoneNumber VARCHAR(255),
    FOREIGN KEY (CustomerID) REFERENCE Customer (CustomerID)
);

CREATE TABLE Nonmember(
    PRIMARY KEY CustomerID,
    FOREIGN KEY (CustomerID) REFERENCE Customer (CustomerID)
);

CREATE TABLE Employee (
    EmployeeSSN VARCHAR(255) PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Username VARCHAR(255) UNIQUE,
    `Password` VARCHAR(255),
    StartDate DATE,
    EndDate DATE,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(255),
    `Address` TEXT,
    SupervisorSSN VARCHAR(255),
    DeptID INT,
    FOREIGN KEY (SupervisorSSN) REFERENCES Employee (EmployeeSSN),
    FOREIGN KEY (DeptID) REFERENCES Department (DepartmentID)
);

CREATE TABLE Department (
    DeptartmentName VARCHAR(255),
    DepartmentID INT PRIMARY KEY,
    ManagerStartDate DATE,
    ManagerEndDate DATE
    ManagerSSN VARCHAR(255),
    FOREIGN KEY (ManagerSSN) REFERENCES Employee (EmployeeSSN)
);