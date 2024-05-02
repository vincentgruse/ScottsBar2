
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
    ),
    (
        'Ales & Tales', '789 Ale Trail, Hopsville', '555-0404', 'brews@alesntales.com'
    ),
    (
        'Cider Siders', '567 Orchard Rd, Cidertown', '555-0505', 'contact@cidersiders.com'
    ),
    (
        'Grapevine Vintners', '678 Vineyard Way, Grapeville', '555-0606', 'help@grapevinevintners.com'
    );

INSERT INTO
    VendorBusiness (VendorID, ContractNumber)
VALUES (1, 1001),
    (2, 1002),
    (3, 1003),
    (4, 1004),
    (5, 1005),
    (6, 1006);

INSERT INTO
    VendorPrivate (VendorID, PersonSSN)
VALUES (1, 111111111),
    (2, 222222222),
    (3, 333333333),
    (4, 444444444),
    (5, 555555555),
    (6, 666666666);

INSERT INTO
    Category (CategoryTitle)
VALUES ('Liquor'),
    ('Wine'),
    ('Beer'),
    ('Cider'),
    ('Snacks'),
    ('Hot food'),
    ('Non-food');

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
        'BEE3000', 'Simple Beer', 200, 'Local craft beer.', 3.00, 0.00, 3, 3
    ),
    (
        'CHIPS00', 'Chips', 50, 'Extra finger chip dust.', 8.00, 0.00, 1, 1
    ),
    (
        'CID4000', 'Apple Cider', 120, 'Sweet apple cider.', 4.00, 0.00, 4, 4
    ),
    (
        'SNK5000', 'Pretzels', 150, 'Crunchy salted pretzels.', 1.50, 0.00, 5, 5
    ),
    (
        'ACC6000', 'Corkscrew', 80, 'Stainless steel corkscrew.', 7.00, 0.10, 6, 6
    ),
    (
        'LIQ2000', 'Gin', 90, 'Dry gin with herbal notes.', 20.00, 0.00, 1, 1
    ),
    (
        'LIQ3000', 'Rum', 130, 'Aged dark rum.', 22.00, 0.00, 1, 1
    ),
    (
        'WIN3000', 'Merlot', 110, 'Full-bodied red wine.', 18.00, 0.00, 2, 2
    ),
    (
        'WIN4000', 'Sauvignon Blanc', 95, 'Crisp white wine.', 16.00, 0.00, 2, 2
    ),
    (
        'BEE4000', 'Lager', 180, 'Smooth and crisp lager.', 2.50, 0.00, 3, 3
    ),
    (
        'BEE5000', 'Stout', 150, 'Dark stout with coffee undertones.', 4.00, 0.00, 3, 3
    ),
    (
        'BEE6000', 'Pale Ale', 160, 'Fruity pale ale with a bitter finish.', 3.50, 0.00, 3, 3
    ),
    (
        'LIQ4000', 'Whiskey', 100, 'Rich oak-aged whiskey.', 40.00, 0.05, 1, 1
    ),
    (
        'LIQ5000', 'Tequila', 70, 'Smooth silver tequila.', 30.00, 0.00, 1, 1
    ),
    (
        'WIN5000', 'Pinot Noir', 85, 'Elegant and versatile red wine.', 25.00, 0.05, 2, 2
    ),
    (
        'WIN6000', 'Riesling', 95, 'Sweet and aromatic white wine.', 12.00, 0.00, 2, 2
    );

INSERT INTO Liquor (CategoryID, LiquorTax) VALUES (1, 0.09);

INSERT INTO Grocery (CategoryID, SalesTax) VALUES (2, 0.06);

INSERT INTO Department (DeptartmentName, ManagerStartDate, ManagerEndDate, ManagerSSN) 
VALUES ('Sales', '2020-01-01', NULL, NULL),
    (
        'Warehouse', '2020-01-01', NULL, NULL
    ),
    (
        'Human Resources', '2020-01-01', NULL, NULL
    ),
    (
        'Marketing', '2022-01-01', NULL, NULL
    ),
    (
        'IT', '2022-01-01', NULL, NULL
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
    ),
    (
        456789012, 'Mark', 'Lee', 'mlee', 'markpass', '2023-01-10', NULL, 'mlee@email.com', '555-2345', '987 New Way, Yourtown', NULL, 4
    ),
    (
        567890123, 'Sara', 'Connor', 'sconnor', 'terminator', '2022-12-15', NULL, 'sconnor@email.com', '555-3456', '321 Circle Dr, Roundtown', 456789012, 5
    );

#This will insert about 200 customers so there's PKs to work with
INSERT INTO Customer VALUES (), (), (), (), (), (), (), (), (), (), (), (), (),
(), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (),
(), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (),
(), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (),
(), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (),
(), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (),
(), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (),
(), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (),
(), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), (), ();



INSERT INTO
    Transactions (
        OccuredAt, Total, PaymentMethod, EmployeeSSN, CustomerID
    )
VALUES (
        '2023-04-01 14:30:00', 150.10, 'Credit Card', 123456789, 1
    ),
    (
        '2023-04-02 16:00:00', 45.00, 'Cash', 234567890, 2
    ),
    (
        '2023-04-03 17:45:00', 9.96, 'Debit Card', 345678901, 3
    ),
    (
        '2024-04-04 12:00:00', 36.00, 'Credit Card', 456789012, 4
    ),
    (
        '2024-04-05 15:00:00', 18.50, 'Cash', 567890123, 5
    ),
    (
        '2024-01-05 10:15:00', 60.80, 'Debit Card', 123456789, 1
    ),
    (
        '2024-01-12 13:20:00', 85.00, 'Credit Card', 234567890, 2
    ),
    (
        '2024-01-18 16:00:00', 120.00, 'Cash', 345678901, 3
    ),
    (
        '2024-01-25 11:45:00', 30.00, 'Credit Card', 456789012, 4
    ),
    (
        '2024-02-02 15:10:00', 50.11, 'Debit Card', 567890123, 5
    ),
    (
        '2024-02-14 09:30:00', 45.50, 'Cash', 123456789, 1
    ),
    (
        '2024-02-20 17:05:00', 75.00, 'Credit Card', 234567890, 2
    ),
    (
        '2024-02-28 14:25:00', 90.43, 'Debit Card', 345678901, 3
    ),
    (
        '2024-03-03 10:30:00', 110.00, 'Credit Card', 456789012, 4
    ),
    (
        '2024-03-15 12:45:00', 70.00, 'Cash', 567890123, 5
    ),
    (
        '2024-03-22 15:20:00', 40.43, 'Debit Card', 123456789, 1
    ),
    (
        '2024-03-30 11:00:00', 95.00, 'Credit Card', 234567890, 2
    ),
    (
        '2024-04-06 16:40:00', 55.00, 'Cash', 345678901, 3
    ),
    (
        '2024-04-13 10:50:00', 20.70, 'Debit Card', 456789012, 4
    ),
    (
        '2024-04-20 18:30:00', 65.23, 'Credit Card', 567890123, 5
    ),
    (
        '2024-04-27 14:15:00', 130.320, 'Cash', 123456789, 1
    ),
    (
        '2024-05-01 12:10:00', 100.20, 'Credit Card', 234567890, 2
    ),
    (
        '2023-01-04 09:20:00', 25.00, 'Credit Card', 123456789, 1
    ),
    (
        '2023-01-10 11:30:00', 45.00, 'Debit Card', 234567890, 2
    ),
    (
        '2023-01-15 14:00:00', 35.00, 'Cash', 345678901, 3
    ),
    (
        '2023-01-22 12:45:00', 55.00, 'Credit Card', 456789012, 4
    ),
    (
        '2023-02-05 16:20:00', 75.00, 'Debit Card', 567890123, 5
    ),
    (
        '2023-02-12 13:00:00', 20.00, 'Cash', 123456789, 1
    ),
    (
        '2023-02-19 10:30:00', 90.00, 'Credit Card', 234567890, 2
    ),
    (
        '2023-02-25 15:15:00', 60.00, 'Debit Card', 345678901, 3
    ),
    (
        '2023-03-03 10:10:00', 40.00, 'Cash', 456789012, 4
    ),
    (
        '2023-03-11 14:40:00', 85.00, 'Credit Card', 567890123, 5
    ),
    (
        '2023-03-17 09:50:00', 30.00, 'Debit Card', 123456789, 1
    ),
    (
        '2023-03-23 17:05:00', 50.00, 'Cash', 234567890, 2
    ),
    (
        '2023-04-01 12:00:00', 65.00, 'Credit Card', 345678901, 3
    ),
    (
        '2023-04-08 15:30:00', 25.00, 'Debit Card', 456789012, 4
    ),
    (
        '2023-04-16 11:45:00', 95.00, 'Cash', 567890123, 5
    ),
    (
        '2023-05-02 16:30:00', 80.00, 'Credit Card', 123456789, 1
    ),
    (
        '2023-05-09 10:20:00', 55.00, 'Debit Card', 234567890, 2
    ),
    (
        '2023-05-15 13:55:00', 45.00, 'Cash', 345678901, 3
    ),
    (
        '2023-05-21 12:00:00', 70.00, 'Credit Card', 456789012, 4
    ),
    (
        '2023-06-01 17:20:00', 30.00, 'Debit Card', 567890123, 5
    ),
    (
        '2023-06-08 15:00:00', 100.00, 'Cash', 123456789, 1
    ),
    (
        '2023-06-14 09:25:00', 20.00, 'Credit Card', 234567890, 2
    ),
    (
        '2023-06-20 14:10:00', 75.00, 'Debit Card', 345678901, 3
    ),
    (
        '2023-06-27 11:30:00', 55.00, 'Cash', 456789012, 4
    ),
    (
        '2023-07-04 16:15:00', 65.00, 'Credit Card', 567890123, 5
    ),
    (
        '2023-07-10 14:30:00', 35.00, 'Debit Card', 123456789, 1
    ),
    (
        '2023-07-18 10:05:00', 50.00, 'Cash', 234567890, 2
    ),
    (
        '2023-07-24 15:45:00', 90.00, 'Credit Card', 345678901, 3
    ),
    (
        '2023-08-01 12:10:00', 40.00, 'Debit Card', 456789012, 4
    ),
    (
        '2023-08-09 18:00:00', 85.00, 'Cash', 567890123, 5
    ),
    (
        '2023-08-16 11:40:00', 60.00, 'Credit Card', 123456789, 1
    ),
    (
        '2023-08-23 13:30:00', 25.00, 'Debit Card', 234567890, 2
    ),
    (
        '2023-09-02 15:25:00', 35.00, 'Cash', 345678901, 3
    ),
    (
        '2023-09-10 10:20:00', 120.00, 'Credit Card', 456789012, 4
    ),
    (
        '2023-09-17 17:35:00', 45.00, 'Debit Card', 567890123, 5
    ),
    (
        '2023-10-01 14:00:00', 110.00, 'Cash', 123456789, 1
    ),
    (
        '2023-10-07 12:30:00', 50.00, 'Credit Card', 234567890, 2
    ),
    (
        '2023-10-13 16:45:00', 75.00, 'Debit Card', 345678901, 3
    ),
    (
        '2023-10-19 14:25:00', 65.00, 'Cash', 456789012, 4
    ),
    (
        '2023-11-01 09:15:00', 55.00, 'Credit Card', 567890123, 5
    ),
    (
        '2023-11-08 16:30:00', 40.00, 'Debit Card', 123456789, 1
    ),
    (
        '2023-11-14 12:00:00', 25.00, 'Cash', 234567890, 2
    ),
    (
        '2023-11-21 17:45:00', 35.00, 'Credit Card', 345678901, 3
    ),
    (
        '2023-11-28 11:50:00', 70.00, 'Debit Card', 456789012, 4
    ),
    (
        '2023-12-05 16:10:00', 95.00, 'Cash', 567890123, 5
    ),
    (
        '2023-12-12 10:30:00', 30.00, 'Credit Card', 123456789, 1
    ),
    (
        '2023-12-19 13:45:00', 85.00, 'Debit Card', 234567890, 2
    ),
    (
        '2023-12-26 15:00:00', 45.00, 'Cash', 345678901, 3
    );


INSERT INTO
    TransactionProducts (
        TransactionID, SKU, Quantity, OverallDiscount
    )
VALUES (1, 'LIQ1000', 2, 0.00),
    (2, 'WIN2000', 1, 0.05),
    (3, 'BEE3000', 6, 0.00),
    (6, 'LIQ3000', 1, 0.00),
    (7, 'WIN3000', 2, 0.00),
    (8, 'BEE4000', 4, 0.00),
    (9, 'LIQ2000', 3, 0.00),
    (10, 'WIN4000', 1, 0.00),
    (11, 'BEE5000', 2, 0.00),
    (12, 'LIQ4000', 1, 0.05),
    (13, 'LIQ5000', 2, 0.00),
    (14, 'WIN5000', 1, 0.05),
    (15, 'WIN6000', 3, 0.00),
    (16, 'BEE6000', 5, 0.00),
    (17, 'LIQ1000', 3, 0.00),
    (18, 'WIN2000', 2, 0.05),
    (19, 'BEE3000', 3, 0.00),
    (20, 'CID4000', 4, 0.00),
    (21, 'SNK5000', 5, 0.00),
    (22, 'ACC6000', 1, 0.10),
    (23, 'LIQ2000', 1, 0.00),
    (24, 'LIQ3000', 1, 0.00),
    (25, 'WIN3000', 2, 0.00),
    (26, 'WIN4000', 1, 0.00),
    (27, 'BEE4000', 2, 0.00),
    (28, 'BEE5000', 3, 0.00),
    (29, 'BEE6000', 4, 0.00),
    (30, 'LIQ4000', 1, 0.05),
    (31, 'LIQ5000', 2, 0.00),
    (32, 'WIN5000', 1, 0.05),
    (33, 'WIN6000', 2, 0.00),
    (34, 'BEE6000', 6, 0.00),
    (35, 'LIQ1000', 4, 0.00),
    (36, 'WIN2000', 3, 0.05),
    (37, 'BEE3000', 7, 0.00),
    (38, 'CID4000', 5, 0.00),
    (39, 'SNK5000', 4, 0.00),
    (40, 'ACC6000', 1, 0.10);

INSERT INTO
    LoyaltyMember (
        CustomerID, FirstName, LastName, Email, PhoneNumber
    )
VALUES (1, 'Bob', 'Builder', 'bob.builder@email.com', '555-6789'),
    (4, 'Gary', 'Gadget', 'gary.gadget@email.com', '555-4321'),
    (2, 'Alice', 'Adventurer', 'alice.adventurer@email.com', '555-2345'),
    (3, 'Charles', 'Champion', 'charles.champion@email.com', '555-3456'),
    (5, 'Diana', 'Dynamo', 'diana.dynamo@email.com', '555-4567'),
    (6, 'Evan', 'Explorer', 'evan.explorer@email.com', '555-5678'),
    (7, 'Fiona', 'Fire', 'fiona.fire@email.com', '555-6789'),
    (8, 'George', 'Giant', 'george.giant@email.com', '555-7890'),
    (9, 'Hanna', 'Hawk', 'hanna.hawk@email.com', '555-8901'),
    (10, 'Ivan', 'Igloo', 'ivan.igloo@email.com', '555-9012'),
    (11, 'Julia', 'Jumper', 'julia.jumper@email.com', '555-0123'),
    (12, 'Kevin', 'Kite', 'kevin.kite@email.com', '555-1234'),
    (13, 'Lily', 'Light', 'lily.light@email.com', '555-2345'),
    (14, 'Mason', 'Mountain', 'mason.mountain@email.com', '555-3456'),
    (15, 'Nina', 'Navigator', 'nina.navigator@email.com', '555-4567'),
    (16, 'Oliver', 'Owl', 'oliver.owl@email.com', '555-5678'),
    (17, 'Penny', 'Pilot', 'penny.pilot@email.com', '555-6789'),
    (18, 'Quinn', 'Quartz', 'quinn.quartz@email.com', '555-7890'),
    (19, 'Rachel', 'Racer', 'rachel.racer@email.com', '555-8901'),
    (20, 'Steve', 'Sailor', 'steve.sailor@email.com', '555-9012'),
    (21, 'Yuri', 'Yacht', 'yuri.yacht@email.com', '555-0123'),
    (22, 'Zoe', 'Zenith', 'zoe.zenith@email.com', '555-1234');


# Everyone's a customer, but this is customer and nonmember customer
INSERT INTO Nonmember (CustomerID) VALUES (23), (24), (25), (26), (27), (28), (29), (30), (31), (32), 

(33), (34), (35), (36), (37), (38), (39), (40), (41), (42),

(43), (44), (45), (46), (47), (48), (49), (50), (51), (52),

(53), (54), (55), (56), (57), (58), (59), (60), (61), (62),

(63), (64), (65), (66), (67), (68), (69), (70), (71), (72),

(73), (74), (75), (76), (77), (78), (79), (80), (81), (82),

(83), (84), (85), (86), (87), (88), (89), (90), (91), (92),

(93), (94), (95), (96), (97), (98), (99), (100), (101), (102),

(103), (104), (105), (106), (107), (108), (109), (110), (111), (112),

(113), (114), (115), (116), (117), (118), (119), (120), (121), (122),

(123), (124), (125), (126), (127), (128), (129), (130), (131), (132),

(133), (134), (135), (136), (137), (138), (139), (140), (141), (142),

(143), (144), (145), (146), (147), (148), (149), (150), (151), (152),

(153), (154), (155), (156), (157), (158), (159), (160), (161), (162),

(163), (164), (165), (166), (167), (168), (169), (170), (171), (172),

(173), (174), (175), (176), (177), (178), (179), (180), (181), (182),

(183), (184), (185), (186), (187), (188), (189), (190), (191), (192),

(193), (194), (195), (196), (197), (198), (199), (200), (201), (202)