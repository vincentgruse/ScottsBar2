INSERT INTO
    Vendor (
        VendorName, VendorAddress, VendorPhoneNumber, VendorEmail
    )
VALUES (
        'Big Brew Co.', '123 Brewery Lane, Brewtown', '555-0101', 'contact@bigbrew.com'
    ),
    (
        'Wine Wonders', '234 Vine St, Vineland', '555-0202', 'info@winewonders.com'
    ),
    (
        'Spirits Solutions', '345 Spirit Blvd, Spirit City', '555-0303', 'support@spiritssolutions.com'
    );

INSERT INTO
    VendorBusiness (VendorID, ContractNumber)
VALUES (1, 1001),
    (2, 1002),
    (3, 1003);

INSERT INTO
    VendorPrivate (VendorID, PersonSSN)
VALUES (1, 111111111),
    (2, 222222222),
    (3, 333333333);

INSERT INTO
    Category (CategoryTitle)
VALUES ('Liquor'),
    ('Wine'),
    ('Beer');

INSERT INTO
    Product (
        SKU, ProductName, Stock, ProductDescription, UnitPrice, Discount, VendorID, CategoryID
    )
VALUES (
        'LIQ1000', 'Vodka', 150, 'Premium distilled vodka.', 25.00, 0.00, 1, 1
    ),
    (
        'WIN2000', 'Chardonnay', 100, 'Aged white wine.', 15.00, 0.05, 2, 2
    ),
    (
        'BEE3000', 'Craft Beer', 200, 'Local craft beer.', 3.00, 0.00, 3, 3
    ),
    (
        'CHIPS00', 'Chips', 50, 'Extra finger chip dust.', 8.00, 0.00, 1,1
    );


INSERT INTO Liquor (CategoryID, LiquorTax) VALUES (1, 0.09);

INSERT INTO
    Grocery (CategoryID, SalesTax)
VALUES (2, 0.06),
    (3, 0.06);

INSERT INTO
    Department (
        DeptartmentName, ManagerStartDate, ManagerEndDate, ManagerSSN
    )
VALUES (
        'Sales', '2020-01-01', NULL, NULL
    ),
    (
        'Warehouse', '2020-01-01', NULL, NULL
    ),
    (
        'Human Resources', '2020-01-01', NULL, NULL
    );

INSERT INTO
    Employee (
        EmployeeSSN, FirstName, LastName, Username, Passwd, StartDate, EndDate, Email, PhoneNumber, EmpAddress, SupervisorSSN, DeptID
    )
VALUES (
        123456789, 'John', 'Doe', 'jdoe', 'password123', '2022-05-10', NULL, 'jdoe@email.com', '555-1234', '456 Main St, Anytown', NULL, 1
    ),
    (
        234567890, 'Jane', 'Smith', 'jsmith', 'securepass', '2021-06-15', NULL, 'jsmith@email.com', '555-5678', '789 Side St, Othertown', 123456789, 2
    ),
    (
        345678901, 'Alice', 'Johnson', 'ajohnson', 'supersecure', '2020-07-20', NULL, 'alicej@email.com', '555-9012', '123 High St, Elsewhere', 123456789, 3
    );

# This will work because it's SERIAL and there's no other attributes. The () functions as a blank value, 
# but the ID will in fact be assigned
INSERT INTO
    Customer
VALUES (),
    (),
    ();

INSERT INTO
    Transactions (
        OccuredAt, Total, PaymentMethod, EmployeeSSN, CustomerID
    )
VALUES (
        '2023-04-01 14:30:00', 150.00, 'Credit Card', 123456789, 1
    ),
    (
        '2023-04-02 16:00:00', 45.00, 'Cash', 234567890, 2
    ),
    (
        '2023-04-03 17:45:00', 9.00, 'Debit Card', 345678901, 3
    );

INSERT INTO
    TransactionProducts (
        TransactionID, SKU, Quantity, OverallDiscount
    )
VALUES (1, 'LIQ1000', 2, 0.00),
    (2, 'WIN2000', 1, 0.05),
    (3, 'BEE3000', 6, 0.00);

INSERT INTO
    LoyaltyMember (
        CustomerID, FirstName, LastName, Email, PhoneNumber
    )
VALUES (
        1, 'Bob', 'Builder', 'bob.builder@email.com', '555-6789'
    );


# Everyone's a customer, but this is customer and nonmember customer
INSERT INTO Nonmember (CustomerID)
VALUES (3);


