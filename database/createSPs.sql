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
    in p_StoreID varchar(64),
	out op_StoreID int,
    out op_MenuName varchar(64),
    out op_MenuType int,
	out op_ID int,
    out op_UUID varchar(36),
	out op_IsActive int,
    out op_SortValue int
)
BEGIN
	declare currIsActive int;
    declare currStoreID int;
    set currIsActive = cast(p_IsActive as unsigned);
    set currStoreID = cast(p_StoreID as unsigned);
    SELECT `menu`.`StoreID`,
		`menu`.`MenuName`,
		`menu`.`MenuType`,
		`menu`.`ID`,
		`menu`.`UUID`,
		`menu`.`IsActive`,
		`menu`.`SortValue`
	FROM `pizzaposdb`.`menu`
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
    IN p_UUID varchar(36),
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
    in p_IsActive varchar(64),
    out op_ItemName varchar(64),
    out op_MenuID int,
    out op_Price double,
    out op_PriorityScore int,
    out op_ExecutionTime int,
    out op_ID int,
    out op_UUID varchar(36),
	out op_IsActive int,
    out op_SortValue int
)
BEGIN
	declare currMenuID int;
    declare currIsActive int;
    set currMenuID = cast(p_MenuID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
    SELECT `MenuItem`.`ItemName`, 
		`MenuItem`.`MenuID`,
        `MenuItem`.`Price`,
        `MenuItem`.`PriorityScore`,
		`MenuItem`.`ExecutionTime`,
        `MenuItem`.`ID`,
		`MenuItem`.`UUID`,
		`MenuItem`.`IsActive`,
        `MenuItem`.`SortValue`
	FROM `pizzaposdb`.`MenuItem` WHERE `MenuItem`.`IsActive` = currIsActive and
		`MenuItem`.`MenuID` = currMenuID;
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
    IN p_CheckStatus varchar(64),
	out op_TableID int,
    out op_UserID int,
    out op_CheckStatus int,
    out op_DateStarted datetime,
    out op_DateClosed datetime,
    out op_ID int,
	out op_UUID varchar(36),
    out op_IsActive int,
    out op_SortValue int
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
	SELECT `poscheck`.`TableID`,
    `poscheck`.`UserID`,
    `poscheck`.`CheckStatus`,
    `poscheck`.`DateStarted`,
    `poscheck`.`DateClosed`,
    `poscheck`.`ID`,
    `poscheck`.`UUID`,
    `poscheck`.`IsActive`,
    `poscheck`.`SortValue`    
	FROM `pizzaposdb`.`poscheck`
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
    IN p_StoreID varchar(64),
    out op_TableName varchar(64),
    out op_StoreID int,
    out op_ID int,
	out op_UUID varchar(36),
    out op_IsActive int,
    out op_SortValue int
)
BEGIN
	declare currIsActive int;
    declare currStoreID int;
    set currIsActive = cast(p_IsActive as unsigned);
    set currStoreID = cast(p_StoreID as unsigned);
	SELECT `postables`.`TableName`,
    `postables`.`StoreID`,
    `postables`.`ID`,
    `postables`.`UUID`,
    `postables`.`sortValue`,
    `postables`.`isActive`
FROM `pizzaposdb`.`postables`
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
    IN p_IsActive varchar(64),
	out op_DateStarted date,
    out op_UserID int,
    out op_TableID int,
    out op_TicketStatus int,
    out op_TicketType int,
    out op_ID int,
	out op_UUID varchar(36),
    out op_IsActive int,
    out op_SortValue int
)
BEGIN
	declare currUserID int;
    declare currTableID int;
    declare currIsActive int;
    set currUserID = cast(p_UserID as unsigned);
    set currTableID = cast(p_TableID as unsigned);
    set currIsActive = cast(p_IsActive as unsigned);
	SELECT `ticket`.`DateStarted`,
    `ticket`.`UserID`,
    `ticket`.`TableID`,
    `ticket`.`TicketStatus`,
    `ticket`.`TicketType`,
    `ticket`.`ID`,
    `ticket`.`UUID`,
    `ticket`.`isActive`,
    `ticket`.`sortValue`    
FROM `pizzaposdb`.`ticket`
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
    IN p_TicketID varchar(64),
    out op_TicketID int,
    out op_MenuItemID varchar(64),
    out op_ItemPrice double,
    out op_ID int,
    out op_UUID varchar(36),
    out op_IsActive int,
    out op_SortValue int
)
begin
	declare currIsActive int;
    declare currTicketID int;
    set currIsActive = cast(p_IsActive as unsigned);
    set currTicketID = cast(p_TicketID as unsigned);
	SELECT `ticketitem`.`TicketID`,
    `ticketitem`.`MenuItemID`,
    `ticketitem`.`ItemPrice`,
    `ticketitem`.`ID`,
    `ticketitem`.`UUID`,
    `ticketitem`.`IsActive`,
    `ticketitem`.`SortValue`
FROM `pizzaposdb`.`ticketitem`
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
    IN p_PaymentStatus varchar(64),
    out op_FinalTotal double,
    out op_CheckID int,
    out op_UserID int,
    out op_PaymentType int,
    out op_PaymentStatus int,
    out op_PaymentDate date,
    out op_ID int,
    out op_UUID varchar(36),
    out op_IsActive int,
    out op_SortValue int
)
begin
	declare currIsActive int;
    declare currUserID int;
    declare currPaymentStatus int;
    set currIsActive = cast(p_IsActive as unsigned);
    set currUserID = cast(p_UserID as unsigned);
    set currPaymentStatus = cast(p_PaymentStatus as unsigned);
	SELECT 
    `transactionhistory`.`FinalTotal`,
    `transactionhistory`.`CheckID`,
    `transactionhistory`.`UserID`,
    `transactionhistory`.`PaymentType`,
    `transactionhistory`.`PaymentStatus`,
    `transactionhistory`.`PaymentDate`,
    `transactionhistory`.`ID`,
    `transactionhistory`.`UUID`,
    `transactionhistory`.`IsActive`,
    `transactionhistory`.`SortValue`
FROM
    `pizzaposdb`.`transactionhistory`
WHERE
    `UserID` = currUserID
        AND `PaymentStatus` = currPaymentStatus
        AND `IsActive` = currIsActive;
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

drop procedure if exists create_UserLU$$
create procedure create_UserLU
(
	in p_UserName varchar(64),
    in p_FirstName varchar(64),
    in p_LastName varchar(64),
    in p_RoleID varchar(64),
    in p_UUID varchar(36),
    in p_IsActive varchar(64),
    in p_SortValue varchar(64),
    out retID int
)
begin
declare currUserName varchar(64);
declare currFirstName varchar(64);
declare currLastName varchar(64);
declare currRoleID int;
declare currUUID varchar(36);
declare currIsActive int;
declare currSortValue int;
set currUserName = p_UserName;
set currFirstName = p_FirstName;
set currLastName = p_LastName;
set currRoleID = cast(p_RoleID as unsigned);
set currUUID = p_UUID;
set currIsActive = cast(p_IsActive as unsigned);
set currSortValue = cast(p_SortValue as unsigned);
INSERT INTO `pizzaposdb`.`userlu`
(`UserName`,
`FirstName`,
`LastName`,
`RoleID`,
`UUID`,
`IsActive`,
`SortValue`)
VALUES
(currUserName,
currFirstName,
currLastName,
currRoleID,
currUUID,
currIsActive,
currSortValue);
set retID = last_insert_id();
end$$

drop procedure if exists read_UserLU$$
create procedure read_UserLU
(
	in p_UserName varchar(64),
	out op_UserName varchar(64),
    out op_FirstName varchar(64),
    out op_LastName varchar(64),
    out op_RoleID int,
    out op_ID int,
    out op_UUID varchar(36),
    out op_IsActive int,
    out op_SortValue int
)
begin
SELECT `userlu`.`UserName`,
    `userlu`.`FirstName`,
    `userlu`.`LastName`,
    `userlu`.`RoleID`,
    `userlu`.`ID`,
    `userlu`.`UUID`,
    `userlu`.`IsActive`,
    `userlu`.`SortValue`
FROM `pizzaposdb`.`userlu`
where `userlu`.`UserName` = p_UserName;
end$$

-- drop procedure if exists update_UserLU$$
-- create procedure update_UserLU
-- (
-- 	in p_UserName varchar(64),
--     in p_FirstName varchar(64),
--     in p_LastName varchar(64),
--     in p_RoleID varchar(64),
--     in p_ID varchar(64),
--     in p_UUID varchar(64),
--     in p_IsActive varchar(64),
--     in p_SortValue varchar(64),
--     out retVal int
-- )
-- begin
-- 	declare currUserName varchar(64);
--     declare currFirstName varchar(64);
--     declare currLastName varchar(64);
--     declare currRoleID int;
--     declare currID int;
--     declare currUUID varchar(64);
--     declare currIsActive int;
--     declare currSortValue int;
--     set currUserName = p_UserName;
--     set currFirstName = p_FirstName;
--     set currLastName = p_LastName;
--     set currRoleID = cast(p_RoleID as unsigned);d
-- 	UPDATE `pizzaposdb`.`userlu`
-- 	SET
-- 	`ID` = <{ID: }>,
-- `UserName` = <{UserName: }>,
-- `FirstName` = <{FirstName: }>,
-- `LastName` = <{LastName: }>,
-- `RoleID` = <{RoleID: }>
-- WHERE `ID` = <{expr}>;

-- end$$

-- drop procedure if exists delete_UserLU$$
-- create procedure delete_UserLU
-- ()
-- begin

-- end$$