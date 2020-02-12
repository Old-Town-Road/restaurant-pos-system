USE [PizzaPOS]
GO

INSERT INTO [dbo].[CheckStatusLU]
           ([ID]
           ,[CheckStatus])
     VALUES
           (1,'OPEN'),
		   (2,'CLOSED')
GO

INSERT INTO dbo.MenuTypeLU
		(ID,MenuType)
	Values
		(1, 'Pizza'),
		(2, 'Sides'),
		(3, 'Drinks')
GO

Insert into dbo.PaymentStatusLU
		(ID, PaymentStatus)
	values
		(1, 'OPEN'),
		(2, 'PAID'),
		(3, 'COMP'),
		(4, 'VOID')
GO

Insert into dbo.PaymentTypeLU
		(ID, PaymentType)
	values
		(1, 'CASH'),
		(2, 'CARD'),
		(3, 'Check'),
		(4, 'GiftCard')
Go

Insert into dbo.RoleLU
		(ID, RoleType)
	values
		(1, 'Server'),
		(2, 'Cook'),
		(3, 'Bartender'),
		(4, 'Manager')
go

Insert into dbo.TicketStatusLU
		(ID, TicketStatus)
	values
		(1, 'OPEN'),
		(2, 'SENT'),
		(3, 'WORKING'),
		(4, 'CLOSED')
go

Insert into dbo.TicketTypeLU
		(ID,TicketType)
	values
		(1,'KITCHEN'),
		(2,'BAR')
go