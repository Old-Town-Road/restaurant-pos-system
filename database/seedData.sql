Insert into pizzaposdb.menu
	(ID,
    UUID,
    sortValue,
    isActive,
    StoreID,
    MenuType,
    MenuName)
    values
    (1,UUID(), 1, 1, 1, 1, "Pizzas"),
    (2,UUID(), 1, 1, 1, 2, "Sides"),
    (3,UUID(), 1, 1, 1, 3, "Drinks");

Insert into pizzaposdb.menuitem
	(ID,
    UUID,
    sortValue,
    isActive,
    MenuID,
    ItemName,
    Price,
    PriorityScore,
    ExecutionTIme)
    values
    (1, UUID(), 1, 1, 1, "L Hawaiian", 20.00, 0, 0),
    (2, UUID(), 2, 1, 1, "S Hawaiian", 12.00, 0, 0),
    (3, UUID(), 3, 1, 1, "L North Carolinian", 24.00, 0, 0),
    (4, UUID(), 4, 1, 1, "S North Carolinian", 16.00, 0, 0),
    (5, UUID(), 5, 1, 1, "L Italy Fav", 22.00, 0, 0),
    (6, UUID(), 6, 1, 1, "S Italy Fav", 15.00, 0, 0),
    (7, UUID(), 7, 1, 1, "L UNCG Classic", 20.00, 0, 0),
    (8, UUID(), 8, 1, 1, "L UNCG Classic", 13.00, 0, 0),
    (9, UUID(), 9, 1, 1, "L Welcome to the South", 21.00, 0, 0),
    (10, UUID(), 10, 1, 1, "S Welcome to the South", 14.00, 0, 0),
    (11, UUID(), 11, 1, 1, "L Ians Homemade", 26.00, 0, 0),
    (12, UUID(), 12, 1, 1, "S Ians Homemade", 17.00, 0, 0),
    (13, UUID(), 13, 1, 1, "L Chef Special", 32.00, 0, 0),
    (14, UUID(), 14, 1, 1, "S Chef Special", 20.00, 0, 0),
    (15, UUID(), 15, 1, 1, "L Sultans Magic", 26.00, 0, 0),
    (16, UUID(), 16, 1, 1, "S Sultans Magic", 17.00, 0, 0),
    (17, UUID(), 1, 1, 2, "L Fries", 8.00, 0, 0),
    (18, UUID(), 2, 1, 2, "S Fries", 5.00, 0, 0),
    (19, UUID(), 3, 1, 2, "L Chicken Nuggets", 13.00, 0, 0),
    (20, UUID(), 4, 1, 2, "S Chicken Nuggets", 8.00, 0, 0),
    (21, UUID(), 6, 1, 2, "L Meatballs", 12.00, 0, 0),
    (22, UUID(), 7, 1, 2, "S Meatballs", 7.00, 0, 0),
    (23, UUID(), 8, 1, 2, "Chocolate Lave", 6.00, 0, 0),
    (24, UUID(), 9, 1, 2, "Brownie Sundae", 6.00, 0, 0),
    (25, UUID(), 10, 1, 2, "Raianas Choc Chip", 5.00, 0, 0),
    (26, UUID(), 1, 1, 3, "Soda", 3.00, 0, 0),
    (27, UUID(), 2, 1, 3, "Coke Float", 5.00, 0, 0),
    (28, UUID(), 3, 1, 3, "Juice", 5.00, 0, 0),
    (29, UUID(), 3, 1, 3, "Soda with flavor", 4.00, 0, 0);

INSERT INTO `pizzaposdb`.`postables`
(`ID`,
`UUID`,
`sortValue`,
`isActive`,
`TableName`,
`StoreID`)
VALUES
(1, UUID(), 1, 1, "Dine In 1", 1),
(2, UUID(), 1, 1, "Dine In 2", 1),
(3, UUID(), 1, 1, "Take Out 1", 1),
(4, UUID(), 1, 1, "Take Out 2", 1),
(5, UUID(), 1, 1, "Bar 1", 1),
(6, UUID(), 1, 1, "Bar 2", 1);
