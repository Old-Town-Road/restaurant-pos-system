INSERT INTO pizzaposdb.CheckStatusLU
           (ID
           ,CheckStatus)
     VALUES
           (1,'OPEN'),
		   (2,'CLOSED');

INSERT INTO pizzaposdb.MenuTypeLU
		(ID,MenuType)
	Values
		(1, 'Pizza'),
		(2, 'Sides'),
		(3, 'Drinks');

Insert into pizzaposdb.PaymentStatusLU
		(ID, PaymentStatusType)
	values
		(1, 'OPEN'),
		(2, 'PAID'),
		(3, 'COMP'),
		(4, 'VOID');

Insert into pizzaposdb.PaymentTypeLU
		(ID, PaymentType)
	values
		(1, 'CASH'),
		(2, 'CARD'),
		(3, 'Check'),
		(4, 'GiftCard');

Insert into pizzaposdb.RoleLU
		(ID, RoleType)
	values
		(1, 'Server'),
		(2, 'Cook'),
		(3, 'Bartender'),
		(4, 'Manager');

Insert into pizzaposdb.TicketStatusLU
		(ID, TicketStatus)
	values
		(1, 'OPEN'),
		(2, 'SENT'),
		(3, 'WORKING'),
		(4, 'CLOSED');

Insert into pizzaposdb.TicketTypeLU
		(ID,TicketType)
	values
		(1,'KITCHEN'),
		(2,'BAR');

INSERT INTO `pizzaposdb`.`store`
(`ID`,
`StoreName`)
VALUES
(1,
'POS');

