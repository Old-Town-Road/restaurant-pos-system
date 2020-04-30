use pizzaposdb;
Delimiter $$
CREATE FUNCTION `pizzaposdb`.`successValue` ()
RETURNS INTEGER DETERMINISTIC
BEGIN
	RETURN 1;
END$$
drop procedure if exists pizzaposdb.create_Menu$$
create procedure create_Menu 
(
	IN p_StoreID varchar(64),
    IN p_MenuName varchar(64),
    IN p_MenuType varchar(64),
	IN p_UUID varchar(36),
	IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retID int
)
BEGIN
	declare StoreID int;
    declare MenuName varchar(64);
    declare MenuType int;
	declare UUID varchar(36);
	declare IsActive int;
    declare SortValue int;
    set StoreID = cast(p_StoreID as unsigned);
    set MenuName = p_MenuName;
    set MenuType = cast(p_MenuType as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
    
	INSERT INTO `pizzaposdb`.`Menu`
    (
		`UUID`,
        `SortValue`,
        `IsActive`,
        `StoreID`,
        `MenuType`,
        `MenuName`
	) VALUES
    (
		UUID,
        SortValue, 
        IsActive,
        StoreID, 
        MenuType,
        MenuName
	);
	set retID = last_insert_id();
END$$

drop procedure if exists read_Menu$$
create procedure read_Menu
(
    in p_IsActive varchar(64),
    in p_StoreID varchar(64)
)
BEGIN
	declare IsActive int;
    declare StoreID int;
    set IsActive = cast(p_IsActive as unsigned);
    set StoreID = cast(p_StoreID as unsigned);
    
	select * from `pizzaposdb`.`Menu`
    WHERE `Menu`.`IsActive` = IsActive and
		`Menu`.`StoreID` = StoreID;
END$$

drop procedure if exists update_Menu$$
create procedure update_Menu
(
	IN p_StoreID varchar(64),
    IN p_MenuName varchar(64),
    IN p_MenuType varchar(64),
	IN p_ID varchar(64),
    IN p_UUID varchar(36),
	IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retVal int
)
BEGIN
	declare StoreID int;
    declare MenuName varchar(64);
    declare MenuType int;
	declare ID int;
    declare UUID varchar(36);
	declare IsActive int;
    declare SortValue int;
    set StoreID = cast(p_StoreID as unsigned);
    set MenuName = p_MenuName;
    set MenuType = cast(p_MenuType as unsigned);
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	UPDATE `pizzaposdb`.`Menu` SET
		`SortValue` = SortValue,
		`IsActive` = IsActive,
		`StoreID` = StoreID,
		`MenuType` = MenuType,
		`MenuName` = MenuName
	WHERE `UUID` = UUID and `ID` = ID;
	set retVal = successValue();
END$$

drop procedure if exists delete_Menu$$
create procedure delete_Menu
(
	in p_ID varchar(64),
    in p_UUID varchar(36),
    in p_IsActive varchar(64),
    OUT retVal int
)
BEGIN
	declare ID int;
    declare UUID varchar(36);
    declare IsActive bit;
    set ID = cast(p_ID as unsigned);
    set IsActive = cast(p_IsActive as unsigned);
    set UUID = p_UUID;
	UPDATE `pizzaposdb`.`Menu` SET
		`IsActive` = IsActive
	where `UUID` = UUID and `ID` = ID;
    SET retVal = successValue();
END$$

drop procedure if exists create_MenuItem$$
create procedure create_MenuItem
(
	IN p_ItemName varchar(64),
    IN p_MenuID varchar(64),
    IN p_Price varchar(64),
    IN p_PriorityScore varchar(64),
    IN p_ExecutionTime varchar(64),
    IN p_UUID varchar(64),
	IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retID int
)
BEGIN
	declare ItemName varchar(64);
    declare MenuID int;
    declare Price double;
    declare PriorityScore int;
    declare ExecutionTime int;
    declare UUID varchar(36);
	declare IsActive int;
    declare SortValue int;
    set ItemName = p_ItemName;
    set MenuID = cast(p_MenuID as unsigned);
    set Price = cast(p_Price as decimal(10,2));
    set PriorityScore = cast(p_PriorityScore as unsigned);
    set ExecutionTime = cast(p_ExecutionTime as unsigned);
    set UUID = p_UUID;
	set IsActive = cast(p_IsActive as binary);
    set SortValue = cast(p_SortValue as unsigned);
	INSERT INTO `pizzaposdb`.`MenuItem`
    (
		`UUID`,
        `SortValue`,
        `IsActive`,
        `MenuID`,
        `ItemName`,
        `Price`,
        `PriorityScore`,
        `ExecutionTime`
	) VALUES
    (
		UUID,
        SortValue, 
        IsActive,
        MenuID,
		ItemName,
		Price,
		PriorityScore,
		ExecutionTime
	);
    set retID = last_insert_id();
END$$

drop procedure if exists read_MenuItem$$
create procedure read_MenuItem
(
	in p_MenuID varchar(64),
    in p_IsActive varchar(64)
)
BEGIN
	declare MenuID int;
    declare IsActive int;
    set MenuID = cast(p_MenuID as unsigned);
    set IsActive = cast(p_IsActive as unsigned);
	select * from `pizzaposdb`.`MenuItem`
    WHERE `MenuItem`.`IsActive` = IsActive and
		`Menu`.`MenuID` = MenuID;
END$$

drop procedure if exists update_MenuItem$$
create procedure update_MenuItem
(
	IN p_ItemName varchar(64),
    IN p_MenuID varchar(64),
    IN p_Price varchar(64),
    IN p_PriorityScore varchar(64),
    IN p_ExecutionTime varchar(64),
	IN p_ID varchar(64),
    IN p_UUID varchar(36),
	IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retVal int
)
BEGIN
	declare ItemName varchar(64);
    declare MenuID int;
    declare Price double;
    declare PriorityScore int;
    declare ExecutionTime int;
	declare ID int;
    declare UUID varchar(36);
	declare IsActive int;
    declare SortValue int;
    set ItemName = p_ItemName;
    set MenuID = cast(p_MenuID as unsigned);
    set Price = cast(p_Price as decimal(10,2));
    set PriorityScore = cast(p_PriorityScore as unsigned);
    set ExecutionTime = cast(p_ExecutionTIme as unsigned);
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	UPDATE `pizzaposdb`.`MenuItem` SET
		`SortValue` = SortValue,
		`IsActive` = IsActive,
		`MenuID` = MenuID,
		`ItemName` = ItemName,
		`Price` = MenuName
	WHERE `UUID` = UUID and `ID` = ID;
	set retVal = successValue();
END$$

drop procedure if exists delete_MenuItem$$
create procedure delete_MenuItem
(
	in ID varchar(64),
    in UUID varchar(36),
    in IsActive varchar(64),
    OUT retVal int
)
BEGIN
	declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
	UPDATE `pizzaposdb`.`MenuItem` SET
		`IsActive` = IsActive
	where `UUID` = UUID and `ID` = ID;
    SET retVal = successValue();
END$$

drop procedure if exists create_PosCheck$$
create procedure create_PosCheck
(
	IN p_TableID varchar(64),
    IN p_UserID varchar(64),
    IN p_CheckStatus varchar(64),
    IN p_DateStarted varchar(64),
    IN p_DateClosed varchar(64),
	IN p_UUID varchar(36),
    IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retID int
)
BEGIN
	declare TableID int;
    declare UserID int;
    declare CheckStatus int;
    declare DateStarted datetime;
    declare DateClosed datetime;
	declare UUID varchar(36);
    declare IsActive int;
    declare SortValue int;
    set TableID = cast(p_TableID as unsigned);
    set UserID = cast(p_UserID as unsigned);
    set CheckStatus = cast(p_CheckStatus as unsigned);
    set DateStarted = cast(p_DateStarted as datetime);
    set DateClosed = cast(p_DateClosed as datetime);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	INSERT INTO `pizzaposdb`.`poscheck`
	(
		`UUID`,
        `IsActive`,
        `SortValue`,
		`TableID`,
		`UserID`,
		`CheckStatus`,
		`DateStarted`,
		`DateClosed`
	) VALUES
	(
		UUID,
        SortValue,
        IsActive,
        TableID,
        UserID,
        CheckStatus,
        DateStarted,
        DateClosed
    );
	set retID = last_insert_id();
END$$

drop procedure if exists read_PosCheck$$
create procedure read_PosCheck
(
	IN p_IsActive varchar(64),
    IN p_TableID varchar(64),
    IN p_UserID varchar(64),
    IN p_CheckStatus varchar(64)
)
BEGIN
	declare IsActive int;
    declare TableID int;
    declare UserID int;
    declare CheckStatus int;
    set IsActive = cast(p_IsActive as unsigned);
    set TableID = cast(p_TableID as unsigned);
    set UserID = cast(p_UserID as unsigned);
    set CheckStatus = cast(p_CheckStatus as unsigned);
	select * from pizzaposdb.PosCheck
    where `IsActive` = IsActive and
    `TableID` = TableID and
    `UserID` = UserID and
    `CheckStatus` = CheckStatus;
END$$

drop procedure if exists delete_PosCheck$$
create procedure delete_PosCheck
(
	in p_ID varchar(64),
    in p_UUID varchar(36),
    in p_IsActive varchar(64),
    OUT retVal int
)
BEGIN
	declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
	UPDATE `pizzaposdb`.`poscheck`
	SET
		`IsActive` = IsActive
	WHERE `ID` = ID and `UUID` = UUID;
    SET retVal = successValue();
END$$

drop procedure if exists create_PosTables$$
create procedure create_PosTables
(
	IN p_TableName varchar(64),
    IN p_StoreID varchar(64),
	IN p_UUID varchar(36),
    IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retID int
)
Begin
	declare TableName varchar(64);
    declare StoreID int;
	declare UUID varchar(36);
    declare IsActive int;
    declare SortValue int;
    set TableName = p_TableName;
    set StoreID = cast(p_StoreID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	INSERT INTO `pizzaposdb`.`postables`
		(`UUID`,
		`SortValue`,
		`IsActive`,
		`TableName`,
		`StoreID`)
	VALUES
		(UUID,
        SortValue,
		IsActive,
		TableName,
		StoreID);
	SET retID = last_insert_id();
END$$

Drop procedure if exists read_PosTable$$
create procedure read_PosTable
(
	IN p_IsActive varchar(64),
    IN p_StoreID varchar(64)
)
BEGIN
	declare IsActive int;
    declare StoreID int;
    set IsActive = cast(p_IsActive as unsigned);
    set StoreID = cast(p_StoreID as unsigned);
	SELECT * FROM `pizzaposdb`.`postables`
    WHERE `StoreID` = StoreID and
		`IsActive` = IsActive;
END$$

Drop procedure if exists update_PosTable$$
create procedure update_PosTable
(
	IN p_TableName varchar(64),
    IN p_StoreID varchar(64),
    IN p_ID varchar(64),
    IN p_UUID varchar(36),
    IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retVal int
)
BEGIN
	declare TableName varchar(64);
    declare StoreID int;
    declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    declare SortValue int;
    set TableName = p_TableName;
    set StoreID = cast(p_StoreID as unsigned);
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	UPDATE `pizzaposdb`.`postables`
		SET
			`sortValue` = SortValue,
			`isActive` = IsActive,
			`TableName` = TableName,
			`StoreID` = StoreID
		WHERE `ID` = ID AND `UUID` = UUID;
	SET retVal = successValue();
END$$

Drop procedure if exists delete_PosTable$$
create procedure delete_PosTable
(
	in p_ID varchar(64),
    in p_UUID varchar(36),
    in p_IsActive varchar(64),
    OUT retVal int
)
BEGIN
	declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
	UPDATE `pizzaposdb`.`postables`
		SET
			`IsActive` = IsActive
		WHERE `ID` = ID AND `UUID` = UUID;
	SET retVal = successValue();
END$$

drop procedure if exists create_Ticket$$
create procedure create_Ticket
(
	IN p_DateStarted varchar(64),
    IN p_UserID varchar(64),
    IN p_TableID varchar(64),
    IN p_TicketStatus varchar(64),
    IN p_TicketType varchar(64),
	IN p_UUID varchar(36),
    IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retID int
)
BEGIN
	declare DateStarted date;
    declare UserID int;
    declare TableID int;
    declare TicketStatus int;
    declare TicketType int;
    declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    declare SortValue int;
    set DateStarted = cast(p_DateStarted as date);
    set UserID = cast(p_UserID as unsigned);
    set TableID = cast(p_TableID as unsigned);
    set TicketStatus = cast(p_TicketStatus as unsigned);
    set TicketType = cast(p_TicketType as unsigned);
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	INSERT INTO `pizzaposdb`.`ticket`
		(`UUID`,
		`SortValue`,
		`IsActive`,
		`DateStarted`,
		`UserID`,
		`TableID`,
		`TicketStatus`,
		`TicketType`)
	VALUES
		(UUID,
        SortValue,
        IsActive,
        DateStarted,
        UserID,
        TableID,
        TicketStatus,
        TicketType);
	SET retID = last_insert_id();
END$$

drop procedure if exists read_Ticket$$
create procedure read_Ticket
(
	IN UserID int,
    IN TableID int,
    IN IsActive int
)
BEGIN
	Select * from `pizzaposdb`.`Ticket`
    WHERE `UserID` = UserID and
    `TableID` = TableID and
    `IsActive` = IsActive;
END$$

drop procedure if exists update_Ticket$$
create procedure update_Ticket
(
	IN p_DateStarted varchar(64),
    IN p_UserID varchar(64),
    IN p_TableID varchar(64),
    IN p_TicketStatus varchar(64),
    IN p_TicketType varchar(64),
	IN p_ID varchar(64),
    IN p_UUID varchar(36),
    IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retVal int
)
BEGIN
	declare DateStarted date;
    declare UserID int;
    declare TableID int;
    declare TicketStatus int;
    declare TicketType int;
    declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    declare SortValue int;
    set DateStarted = cast(p_DateStarted as date);
    set UserID = cast(p_UserID as unsigned);
    set TableID = cast(p_TableID as unsigned);
    set TicketStatus = cast(p_TicketStatus as unsigned);
    set TicketType = cast(p_TicketType as unsigned);
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	UPDATE `pizzaposdb`.`ticket`
		SET
			`SortValue` = SortValue,
			`IsActive` = IsActive,
			`DateStarted` = DateStarted,
			`UserID` = UserID,
			`TableID` = TableID,
			`TicketStatus` = TicketStatus,
			`TicketType` = TicketType
		WHERE `ID` = ID and `UUID` = UUID;
	SET retVal = successValue();
END$$

drop procedure if exists delete_Ticket$$
create procedure delete_Ticket
(
	in p_ID varchar(64),
    in p_UUID varchar(36),
    in p_IsActive varchar(64),
    OUT retVal int
)
BEGIN
	declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
	UPDATE `pizzaposdb`.`Ticket`
		SET `IsActive` = IsActive
        WHERE `ID` = ID and `UUID` = UUID;
	SET retVal = 1;
END$$

drop procedure if exists create_TicketItem$$
create procedure create_TicketItem
(
	IN p_TicketID varchar(64),
    IN p_MenuItemID varchar(64),
    IN p_ItemPrice varchar(64),
    IN p_UUID varchar(36),
    IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retID int
)
begin
	declare TicketID int;
    declare MenuItemID int;
    declare ItemPrice double;
    declare UUID varchar(36);
    declare IsActive int;
    declare SortValue int;
    set TicketID = cast(p_TicketID as unsigned);
    set MenuItemID = cast(p_MenuItemID as unsigned);
    set ItemPrice = cast(p_ItemPrice as decimal(10, 2));
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	Insert into `pizzaposdb`.`TicketItem`
    (`UUID`,
    `IsActive`,
    `SortValue`,
    `TicketID`,
	`MenuItemID`,
    `ItemPrice`)
    values
    (UUID,
    IsActive,
    SortValue,
    TicketID,
    MenuItemID,
    ItemPrice);
	set retID = last_insert_id();
END$$

Drop procedure if exists read_TicketItem$$
create procedure read_TicketItem
(
	IN p_IsActive varchar(64),
    IN p_TicketID varchar(64)
)
begin
	declare IsActive int;
    declare TicketID int;
    set IsActive = cast(p_IsActive as unsigned);
    set TicketID = cast(p_TicketID as unsigned);
	select * from `pizzaposdb`.`TicketItem`
    where `IsActive` = IsActive and
    `TicketID` = TicketID;
end$$

Drop procedure if exists update_TicketItem$$
create procedure update_TicketItem
(
	IN p_TicketID varchar(64),
    IN p_MenuItemID varchar(64),
    IN p_ItemPrice varchar(64),
    IN p_ID varchar(64),
    IN p_UUID varchar(36),
    IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retVal int
)
begin
	declare TicketID int;
    declare MenuItemID int;
    declare ItemPrice double;
    declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    declare SortValue int;
    set TicketID = cast(p_TicketID as unsigned);
    set MenuItemID = cast(p_MenuItemID as unsigned);
    set ItemPrice = cast(p_ItemPrice as decimal(10,2));
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	update `pizzaposdb`.`TicketItem` set
		`IsActive` = IsActive,
        `SortValue` = SortValue,
        `TicketID` = TicketID,
        `MenuItemID` = MenuItemID,
        `ItemPrice` = ItemPrice
	where `ID` = ID and `UUID` = UUID;
    set retVal = successValue();
end$$

Drop procedure if exists delete_TicketItem$$
create procedure delete_TicketItem
(
	in p_ID varchar(64),
    in p_UUID varchar(36),
    in p_IsActive varchar(64),
    OUT retVal int
)
begin
	declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
	update `pizzaposdb`.`TicketItem` set
		`IsActive` = IsActive
	where `ID` = ID and `UUID` = UUID;
    set retVal = successValue();
end$$

drop procedure if exists create_TransactionHistory$$
create procedure create_TransactionHistory
(
	IN p_FinalTotal varchar(64),
    IN p_CheckID varchar(64),
    IN p_UserID varchar(64),
    IN p_PaymentType varchar(64),
    IN p_PaymentStatus varchar(64),
    IN p_PaymentDate varchar(64),
    IN p_UUID varchar(36),
    IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retID int
)
begin
    declare FinalTotal double;
    declare CheckID int;
    declare UserID int;
    declare PaymentType int;
    declare PaymentStatus int;
    declare PaymentDate date;
    declare UUID varchar(36);
    declare IsActive int;
    declare SortValue int;
    set FinalTotal = cast(p_FinalTotal as decimal(10,2));
    set CheckID = cast(p_CheckID as unsigned);
    set UserID = cast(p_UserID as unsigned);
    set PaymentType = cast(p_PaymentType as unsigned);
    set PaymentStatus = cast(p_PaymentStatus as unsigned);
    set PaymentDate = cast(p_PaymentDate as date);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	insert into `pizzaposdb`.`TransactionHistory`
		(`UUID`,
        `IsActive`,
        `SortValue`,
        `FinalTotal`,
        `CheckID`,
        `UserID`,
        `PaymentType`,
        `PaymentStatus`,
        `PaymentDate`)
        values
        (UUID,
        IsActive,
        SortValue,
        FinalTotal,
        CheckID,
        UserID,
        PaymentType,
        PaymentStatus,
        PaymentDate);
	Set retID = last_insert_id();
end$$

drop procedure if exists read_TransactionHistory$$
create procedure read_TransactionHistory
(
	IN p_IsActive varchar(64),
    IN p_UserID varchar(64),
    IN p_PaymentStatus varchar(64)
)
begin
	declare IsActive int;
    declare UserID int;
    declare PaymentStatus int;
    set IsActive = cast(p_IsActive as unsigned);
    set UserID = cast(p_UserID as unsigned);
    set PaymentStatus = cast(p_PaymentStatus as unsigned);
	select * from `pizzaposdb`.`TransactionHistory`
    where `UserID` = UserID and 
    `PaymentStatus` = PaymentStatus and
    `IsActive` = IsActive;
end$$

drop procedure if exists update_TransactionHistory$$
create procedure update_TransactionHistory
(
	IN p_FinalTotal varchar(64),
    IN p_CheckID varchar(64),
    IN p_UserID varchar(64),
    IN p_PaymentType varchar(64),
    IN p_PaymentStatus varchar(64),
    IN p_PaymentDate varchar(64),
    IN p_ID varchar(64),
    IN p_UUID varchar(36),
    IN p_IsActive varchar(64),
    IN p_SortValue varchar(64),
    OUT retVal int
)
begin
    declare FinalTotal double;
    declare CheckID int;
    declare UserID int;
    declare PaymentType int;
    declare PaymentStatus int;
    declare PaymentDate date;
    declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    declare SortValue int;
    set FinalTotal = cast(p_FinalTotal as decimal(10,2));
    set CheckID = cast(p_CheckID as unsigned);
    set UserID = cast(p_UserID as unsigned);
    set PaymentType = cast(p_PaymentType as unsigned);
    set PaymentStatus = cast(p_PaymentStatus as unsigned);
    set PaymentDate = cast(p_PaymentDate as date);
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
    set SortValue = cast(p_SortValue as unsigned);
	update `pizzaposdb`.`TransactionHistory` set
        `IsActive` = IsActive,
		`SortValue` = SortValue,
        `FinalTotal` = FinalTotal,
        `CheckID` = CheckID,
        `UserID` = UserID,
        `PaymentType` = PaymentType,
        `PaymentStatus` = PaymentStatus,
        `PaymentDate` = PaymentDate
        where `ID` = ID and `UUID` = UUID;
	set retVal = successValue();
end$$

drop procedure if exists delete_TransactionHistory$$
create procedure delete_TransactionHistory
(
	in p_ID varchar(64),
    in p_UUID varchar(36),
    in p_IsActive varchar(64),
    OUT retVal int
)
begin
	declare ID int;
    declare UUID varchar(36);
    declare IsActive int;
    set ID = cast(p_ID as unsigned);
    set UUID = p_UUID;
    set IsActive = cast(p_IsActive as unsigned);
	update `pizzaposdb`.`TransactionHistory` set
        `IsActive` = IsActive
        where `ID` = ID  and `UUID` = UUID;
	set retVal = successValue();
end$$