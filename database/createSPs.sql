use pizzaposdb;
Delimiter $$
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
	declare currStoreID int;
    declare currMenuName varchar(64);
    declare currMenuType int;
	declare currUUID varchar(36);
	declare currIsActive int;
    declare currSortValue int;
    set currStoreID = cast(p_StoreID as unsigned);
    set currMenuName = p_MenuName;
    set currMenuType = cast(p_MenuType as unsigned);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
    
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
		currUUID,
        currSortValue, 
        currIsActive,
        currStoreID, 
        currMenuType,
        currMenuName
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
	declare currIsActive int;
    declare currStoreID int;
    set currIsActive = cast(p_IsActive as unsigned);
    set currStoreID = cast(p_StoreID as unsigned);
    
	select * from `pizzaposdb`.`Menu`
    WHERE `Menu`.`IsActive` = currIsActive and
		`Menu`.`StoreID` = currStoreID;
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
	declare currStoreID int;
    declare currMenuName varchar(64);
    declare currMenuType int;
	declare currID int;
    declare currUUID varchar(36);
	declare currIsActive int;
    declare currSortValue int;
    set currStoreID = cast(p_StoreID as unsigned);
    set currMenuName = p_MenuName;
    set currMenuType = cast(p_MenuType as unsigned);
    set currID = cast(p_ID as unsigned);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
	UPDATE `pizzaposdb`.`Menu` SET
		`SortValue` = currSortValue,
		`IsActive` = currIsActive,
		`StoreID` = currStoreID,
		`MenuType` = currMenuType,
		`MenuName` = currMenuName
	WHERE pizzaposdb.menu.UUID = currUUID and `ID` = currID;
	set retVal = 1;
END$$

drop procedure if exists pizzaposdb.delete_Menu$$
create procedure pizzaposdb.delete_Menu
(
	in p_ID varchar(64),
    in p_UUID varchar(36),
    in p_IsActive varchar(64),
    OUT retVal int
)
BEGIN
	declare currID int;
    declare currUUID varchar(36);
    declare currIsActive bit;
    set currID = cast(p_ID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
    set currUUID = p_UUID;
	UPDATE pizzaposdb.menu
    SET
		`IsActive` = currIsActive
	WHERE `ID` = currID and `UUID` = currUUID;
    SET retVal = 1;
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
	declare currItemName varchar(64);
    declare currMenuID int;
    declare currPrice double;
    declare currPriorityScore int;
    declare currExecutionTime int;
    declare currUUID varchar(36);
	declare currIsActive int;
    declare currSortValue int;
    set currItemName = p_ItemName;
    set currMenuID = cast(p_MenuID as unsigned);
    set currPrice = cast(p_Price as decimal(10,2));
    set currPriorityScore = cast(p_PriorityScore as unsigned);
    set currExecutionTime = cast(p_ExecutionTime as unsigned);
    set currUUID = p_UUID;
	set currIsActive = cast(p_IsActive as binary);
    set currSortValue = cast(p_SortValue as unsigned);
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
		currUUID,
        currSortValue, 
        currIsActive,
        currMenuID,
		currItemName,
		currPrice,
		currPriorityScore,
		currExecutionTime
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
	declare currMenuID int;
    declare currIsActive int;
    set currMenuID = cast(p_MenuID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
	select * from `pizzaposdb`.`MenuItem`
    WHERE `MenuItem`.`IsActive` = currIsActive and
		`Menu`.`MenuID` = currMenuID;
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
	declare currItemName varchar(64);
    declare currMenuID int;
    declare currPrice double;
    declare currPriorityScore int;
    declare currExecutionTime int;
	declare currID int;
    declare currUUID varchar(36);
	declare currIsActive int;
    declare currSortValue int;
    set currItemName = p_ItemName;
    set currMenuID = cast(p_MenuID as unsigned);
    set currPrice = cast(p_Price as decimal(10,2));
    set currPriorityScore = cast(p_PriorityScore as unsigned);
    set currExecutionTime = cast(p_ExecutionTIme as unsigned);
    set currID = cast(p_ID as unsigned);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
	UPDATE `pizzaposdb`.`MenuItem` SET
		`SortValue` = currSortValue,
		`IsActive` = currIsActive,
		`MenuID` = currMenuID,
		`ItemName` = currItemName,
		`Price` = currMenuName
	WHERE `UUID` = currUUID and `ID` = currID;
	set retVal = 1;
END$$

drop procedure if exists delete_MenuItem$$
create procedure delete_MenuItem
(
	in p_ID varchar(64),
    in p_UUID varchar(36),
    in p_IsActive varchar(64),
    OUT retVal int
)
BEGIN
	declare currID int;
    declare currUUID varchar(36);
    declare currIsActive bit;
    set currID = cast(p_ID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
    set currUUID = p_UUID;
	UPDATE `pizzaposdb`.`MenuItem` SET
		`IsActive` = currIsActive
	where `UUID` = currUUID and `ID` = currID;
    SET retVal = 1;
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
	declare currTableID int;
    declare currUserID int;
    declare currCheckStatus int;
    declare currDateStarted datetime;
    declare currDateClosed datetime;
	declare currUUID varchar(36);
    declare currIsActive int;
    declare currSortValue int;
    set currTableID = cast(p_TableID as unsigned);
    set currUserID = cast(p_UserID as unsigned);
    set currCheckStatus = cast(p_CheckStatus as unsigned);
    set currDateStarted = cast(p_DateStarted as datetime);
    set currDateClosed = cast(p_DateClosed as datetime);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
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
		currUUID,
        currSortValue,
        currIsActive,
        currTableID,
        currUserID,
        currCheckStatus,
        currDateStarted,
        currDateClosed
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
	declare currIsActive int;
    declare currTableID int;
    declare currUserID int;
    declare currCheckStatus int;
    set currIsActive = cast(p_IsActive as unsigned);
    set currTableID = cast(p_TableID as unsigned);
    set currUserID = cast(p_UserID as unsigned);
    set currCheckStatus = cast(p_CheckStatus as unsigned);
	select * from pizzaposdb.PosCheck
    where `IsActive` = currIsActive and
    `TableID` = currTableID and
    `UserID` = currUserID and
    `CheckStatus` = currCheckStatus;
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
	declare currID int;
    declare currUUID varchar(36);
    declare currIsActive bit;
    set currID = cast(p_ID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
    set currUUID = p_UUID;
	UPDATE `pizzaposdb`.`poscheck`
	SET
		`IsActive` = currIsActive
	WHERE `ID` = currID and `UUID` = currUUID;
    SET retVal = 1;
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
	declare currTableName varchar(64);
    declare currStoreID int;
	declare currUUID varchar(36);
    declare currIsActive int;
    declare currSortValue int;
    set currTableName = p_TableName;
    set currStoreID = cast(p_StoreID as unsigned);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
	INSERT INTO `pizzaposdb`.`postables`
		(`UUID`,
		`SortValue`,
		`IsActive`,
		`TableName`,
		`StoreID`)
	VALUES
		(currUUID,
        currSortValue,
		currIsActive,
		currTableName,
		currStoreID);
	SET retID = last_insert_id();
END$$

Drop procedure if exists read_PosTable$$
create procedure read_PosTable
(
	IN p_IsActive varchar(64),
    IN p_StoreID varchar(64)
)
BEGIN
	declare currIsActive int;
    declare currStoreID int;
    set currIsActive = cast(p_IsActive as unsigned);
    set currStoreID = cast(p_StoreID as unsigned);
	SELECT * FROM `pizzaposdb`.`postables`
    WHERE `StoreID` = currStoreID and
		`IsActive` = currIsActive;
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
	declare currTableName varchar(64);
    declare currStoreID int;
    declare currID int;
    declare currUUID varchar(36);
    declare currIsActive int;
    declare currSortValue int;
    set currTableName = p_TableName;
    set currStoreID = cast(p_StoreID as unsigned);
    set currID = cast(p_ID as unsigned);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
	UPDATE `pizzaposdb`.`postables`
		SET
			`sortValue` = currSortValue,
			`isActive` = currIsActive,
			`TableName` = currTableName,
			`StoreID` = currStoreID
		WHERE `ID` = currID AND `UUID` = currUUID;
	SET retVal = 1;
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
	declare currID int;
    declare currUUID varchar(36);
    declare currIsActive bit;
    set currID = cast(p_ID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
    set currUUID = p_UUID;
	UPDATE `pizzaposdb`.`postables`
		SET
			`IsActive` = currIsActive
		WHERE `ID` = currID AND `UUID` = currUUID;
	SET retVal = 1;
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
	declare currDateStarted date;
    declare currUserID int;
    declare currTableID int;
    declare currTicketStatus int;
    declare currTicketType int;
    declare currID int;
    declare currUUID varchar(36);
    declare currIsActive int;
    declare currSortValue int;
    set currDateStarted = cast(p_DateStarted as date);
    set currUserID = cast(p_UserID as unsigned);
    set currTableID = cast(p_TableID as unsigned);
    set currTicketStatus = cast(p_TicketStatus as unsigned);
    set currTicketType = cast(p_TicketType as unsigned);
    set currID = cast(p_ID as unsigned);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
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
		(currUUID,
        currSortValue,
        currIsActive,
        currDateStarted,
        currUserID,
        currTableID,
        currTicketStatus,
        currTicketType);
	SET retID = last_insert_id();
END$$

drop procedure if exists read_Ticket$$
create procedure read_Ticket
(
	IN p_UserID varchar(64),
    IN p_TableID varchar(64),
    IN p_IsActive varchar(64)
)
BEGIN
	declare currUserID int;
    declare currTableID int;
    declare currIsActive int;
    set currUserID = cast(p_UserID as unsigned);
    set currTableID = cast(p_TableID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
	Select * from `pizzaposdb`.`Ticket`
    WHERE `UserID` = currUserID and
    `TableID` = currTableID and
    `IsActive` = currIsActive;
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
	declare currDateStarted date;
    declare currUserID int;
    declare currTableID int;
    declare currTicketStatus int;
    declare currTicketType int;
    declare currID int;
    declare currUUID varchar(36);
    declare currIsActive int;
    declare currSortValue int;
    set currDateStarted = cast(p_DateStarted as date);
    set currUserID = cast(p_UserID as unsigned);
    set currTableID = cast(p_TableID as unsigned);
    set currTicketStatus = cast(p_TicketStatus as unsigned);
    set currTicketType = cast(p_TicketType as unsigned);
    set currID = cast(p_ID as unsigned);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
	UPDATE `pizzaposdb`.`ticket`
		SET
			`SortValue` = currSortValue,
			`IsActive` = currIsActive,
			`DateStarted` = currDateStarted,
			`UserID` = currUserID,
			`TableID` = currTableID,
			`TicketStatus` = currTicketStatus,
			`TicketType` = currTicketType
		WHERE `ID` = currID and `UUID` = currUUID;
	SET retVal = 1;
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
	declare currID int;
    declare currUUID varchar(36);
    declare currIsActive bit;
    set currID = cast(p_ID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
    set currUUID = p_UUID;
	UPDATE `pizzaposdb`.`Ticket`
		SET `IsActive` = currIsActive
        WHERE `ID` = currID and `UUID` = currUUID;
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
	declare currTicketID int;
    declare currMenuItemID int;
    declare currItemPrice double;
    declare currUUID varchar(36);
    declare currIsActive int;
    declare currSortValue int;
    set currTicketID = cast(p_TicketID as unsigned);
    set currMenuItemID = cast(p_MenuItemID as unsigned);
    set currItemPrice = cast(p_ItemPrice as decimal(10, 2));
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
	Insert into `pizzaposdb`.`TicketItem`
    (`UUID`,
    `IsActive`,
    `SortValue`,
    `TicketID`,
	`MenuItemID`,
    `ItemPrice`)
    values
    (currUUID,
    currIsActive,
    currSortValue,
    currTicketID,
    currMenuItemID,
    currItemPrice);
	set retID = last_insert_id();
END$$

Drop procedure if exists read_TicketItem$$
create procedure read_TicketItem
(
	IN p_IsActive varchar(64),
    IN p_TicketID varchar(64)
)
begin
	declare currIsActive int;
    declare currTicketID int;
    set currIsActive = cast(p_IsActive as unsigned);
    set currTicketID = cast(p_TicketID as unsigned);
	select * from `pizzaposdb`.`TicketItem`
    where `IsActive` = currIsActive and
    `TicketID` = currTicketID;
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
	declare currTicketID int;
    declare currMenuItemID int;
    declare currItemPrice double;
    declare currID int;
    declare currUUID varchar(36);
    declare currIsActive int;
    declare currSortValue int;
    set currTicketID = cast(p_TicketID as unsigned);
    set currMenuItemID = cast(p_MenuItemID as unsigned);
    set currItemPrice = cast(p_ItemPrice as decimal(10,2));
    set currID = cast(p_ID as unsigned);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
	update `pizzaposdb`.`TicketItem` set
		`IsActive` = currIsActive,
        `SortValue` = currSortValue,
        `TicketID` = currTicketID,
        `MenuItemID` = currMenuItemID,
        `ItemPrice` = currItemPrice
	where `ID` = currID and `UUID` = currUUID;
    set retVal = 1;
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
	declare currID int;
    declare currUUID varchar(36);
    declare currIsActive bit;
    set currID = cast(p_ID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
    set currUUID = p_UUID;
	update `pizzaposdb`.`TicketItem` set
		`IsActive` = currIsActive
	where `ID` = currID and `UUID` = currUUID;
    set retVal = 1;
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
    declare currFinalTotal double;
    declare currCheckID int;
    declare currUserID int;
    declare currPaymentType int;
    declare currPaymentStatus int;
    declare currPaymentDate date;
    declare currUUID varchar(36);
    declare currIsActive int;
    declare currSortValue int;
    set currFinalTotal = cast(p_FinalTotal as decimal(10,2));
    set currCheckID = cast(p_CheckID as unsigned);
    set currUserID = cast(p_UserID as unsigned);
    set currPaymentType = cast(p_PaymentType as unsigned);
    set currPaymentStatus = cast(p_PaymentStatus as unsigned);
    set currPaymentDate = cast(p_PaymentDate as date);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
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
        (currUUID,
        currIsActive,
        currSortValue,
        currFinalTotal,
        currCheckID,
        currUserID,
        currPaymentType,
        currPaymentStatus,
        currPaymentDate);
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
	declare currIsActive int;
    declare currUserID int;
    declare currPaymentStatus int;
    set currIsActive = cast(p_IsActive as unsigned);
    set currUserID = cast(p_UserID as unsigned);
    set currPaymentStatus = cast(p_PaymentStatus as unsigned);
	select * from `pizzaposdb`.`TransactionHistory`
    where `UserID` = currUserID and 
    `PaymentStatus` = currPaymentStatus and
    `IsActive` = currIsActive;
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
    declare currFinalTotal double;
    declare currCheckID int;
    declare currUserID int;
    declare currPaymentType int;
    declare currPaymentStatus int;
    declare currPaymentDate date;
    declare currID int;
    declare currUUID varchar(36);
    declare currIsActive int;
    declare currSortValue int;
    set currFinalTotal = cast(p_FinalTotal as decimal(10,2));
    set currCheckID = cast(p_CheckID as unsigned);
    set currUserID = cast(p_UserID as unsigned);
    set currPaymentType = cast(p_PaymentType as unsigned);
    set currPaymentStatus = cast(p_PaymentStatus as unsigned);
    set currPaymentDate = cast(p_PaymentDate as date);
    set currID = cast(p_ID as unsigned);
    set currUUID = p_UUID;
    set currIsActive = cast(p_IsActive as unsigned);
    set currSortValue = cast(p_SortValue as unsigned);
	update `pizzaposdb`.`TransactionHistory` set
        `IsActive` = currIsActive,
		`SortValue` = currSortValue,
        `FinalTotal` = currFinalTotal,
        `CheckID` = currCheckID,
        `UserID` = currUserID,
        `PaymentType` = currPaymentType,
        `PaymentStatus` = currPaymentStatus,
        `PaymentDate` = currPaymentDate
        where `ID` = currID and `UUID` = currUUID;
	set retVal = 1;
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
	declare currID int;
    declare currUUID varchar(36);
    declare currIsActive bit;
    set currID = cast(p_ID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
    set currUUID = p_UUID;
	update `pizzaposdb`.`TransactionHistory` set
        `IsActive` = currIsActive
        where `ID` = currID  and `UUID` = currUUID;
	set retVal = 1;
end$$