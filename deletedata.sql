SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM category;
DELETE FROM customer;
DELETE FROM department;
DELETE FROM employee;
DELETE FROM grocery;
DELETE FROM liquor;
DELETE FROM loyaltymember;
DELETE FROM nonmember;
DELETE FROM product;
DELETE FROM transactionproducts;
DELETE FROM transactions;
DELETE FROM vendor;
DELETE FROM vendorbusiness;
DELETE FROM vendorprivate;

ALTER TABLE vendor AUTO_INCREMENT = 1;
ALTER TABLE category AUTO_INCREMENT = 1;
ALTER TABLE Liquor AUTO_INCREMENT = 1;
ALTER TABLE Grocery AUTO_INCREMENT = 1;
ALTER TABLE Department AUTO_INCREMENT = 1;
ALTER TABLE Customer AUTO_INCREMENT = 1;
ALTER TABLE Transactions AUTO_INCREMENT = 1;

