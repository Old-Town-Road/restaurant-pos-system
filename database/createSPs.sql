Delimiter $$
create procedure create_Menu 
(
	IN p_UUID varchar(36),
	IN p_SortValue int,
    IN p_IsActive bit,
    IN p_StoreID int,
    IN p_MenuType int,
    IN p_MenuName varchar(50),
    OUT p_ID int
)
BEGIN
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
		p_UUID,
        p_SortValue, 
        p_IsActive,
        p_StoreID, 
        p_MenuType,
        p_MenuName
	);
	set p_ID = (SELECT `Menu`.`ID` FROM `pizzaposdb`.`Menu` where
		`UUID` = p_UUID and
		`SortValue` = p_SortValue and
		`IsActive` = p_IsActive and
		`StoreID` = p_StoreID and
		`MenuType` = p_MenuType and
		`MenuName` = p_MenuName);
END$$

create procedure read_Menu
(
	in p_IsActive int,
	in p_StoreID int
)
BEGIN
	select * from `pizzaposdb`.`Menu`
    WHERE `Menu`.`IsActive` = p_IsActive and
		`Menu`.`StoreID` = p_StoreID;
END$$

create procedure update_Menu
(
	IN p_ID int,
    IN p_UUID varchar(36),
    IN p_SortValue int,
    IN p_IsActive bit,
    IN p_StoreID int,
    IN p_MenuType int,
    IN p_MenuName varchar(50),
    OUT retVal int
)
BEGIN
	UPDATE `pizzaposdb`.`Menu` SET
		`SortValue` = p_SortValue,
		`IsActive` = p_IsActive,
		`StoreID` = p_StoreID,
		`MenuType` = p_MenuType,
		`MenuName` = p_MenuName
	WHERE `UUID` = p_UUID and `ID` = p_ID;
	set retVal = 1;
END$$

create procedure delete_Menu
(
	in p_ID int,
    in p_UUID int,
    in p_IsActive bit,
    OUT retVal int
)
BEGIN
	UPDATE `pizzaposdb``Menu` SET
		`IsActive` = p_IsActive
	where `UUID` = p_UUID and `ID` = p_ID;
    SET retVal = 1;
END$$

create procedure create_MenuItem
(
	IN p_UUID varchar(36),
	IN p_SortValue int,
    IN p_IsActive bit,
    IN p_MenuID int,
    IN p_ItemName varchar(50),
    IN p_Price double,
    IN p_PriorityScore int,
    IN p_ExecutionTime int,
    OUT p_ID int
)
BEGIN
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
		p_UUID,
        p_SortValue, 
        p_IsActive,
        p_MenuID,
		p_ItemName,
		p_Price,
		p_PriorityScore,
		p_ExecutionTime
	);
	set p_ID = (SELECT `MenuItem`.`ID` FROM `pizzaposdb`.`MenuItem` where
		`UUID` = p_UUID and
		`SortValue` = p_SortValue and
		`IsActive` = p_IsActive and
		`MenuID` = p_MenuID and
        `ItemName` = p_ItemName and
        `Price` = p_Price and
        `PriorityScore` = p_PriorityScore and
        `ExecutionTime` = p_ExecutionTime);
END$$

create procedure read_MenuItem
(
	in p_MenuID int,
    in p_IsActive int
)
BEGIN
	select * from `pizzaposdb`.`MenuItem`
    WHERE `MenuItem`.`IsActive` = p_IsActive and
		`Menu`.`MenuID` = p_MenuID;
END$$

create procedure update_MenuItem
(
	IN p_ID int,
    IN p_UUID varchar(36),
	IN p_SortValue int,
    IN p_IsActive bit,
    IN p_MenuID int,
    IN p_ItemName varchar(50),
    IN p_Price double,
    IN p_PriorityScore int,
    IN p_ExecutionTime int,
    OUT retVal int
)
BEGIN
	UPDATE `pizzaposdb`.`MenuItem` SET
		`SortValue` = p_SortValue,
		`IsActive` = p_IsActive,
		`MenuID` = p_MenuID,
		`ItemName` = p_ItemName,
		`Price` = p_MenuName
	WHERE `UUID` = p_UUID and `ID` = p_ID;
	set retVal = 1;
END$$

create procedure delete_MenuItem
(
	in p_ID int,
    in p_UUID int,
    in p_IsActive bit,
    OUT retVal int
)
BEGIN
	UPDATE `pizzaposdb``MenuItem` SET
		`IsActive` = p_IsActive
	where `UUID` = p_UUID and `ID` = p_ID;
    SET retVal = 1;
END$$

-- create procedure create_PosCheck
-- (
-- 	IN p_UUID varchar(36),
--     IN p_TableID int,
--     IN p_UserID int,
--     IN p_CheckStatus int,
--     IN p_DateStarted datetime,
--     IN p_DateClosed datetime,
--     OUT p_ID int
-- )
-- BEGIN
-- 	INSERT INTO `pizzaposdb`.`poscheck`
-- 	(
-- 		`ID`,
-- 		`UUID`,
-- 		`TableID`,
-- 		`UserID`,
-- 		`CheckStatus`,
-- 		`DateStarted`,
-- 		`DateClosed`
-- 	) VALUES
-- 	(
-- 		p_UUID,
--         p_TableID,
--         p_UserID,
--         p_CheckStatus,
--         p_DateStarted,
--         p_DateClosed
--     );

-- 	set ID = (SELECT `poscheck`.`ID` FROM `pizzaposdb`.`poscheck`
-- 		where `poscheck`.`UUID` = p_UUID and
-- 			`TableID` = p_TableID and
-- 			`UserID` = p_UserID and
-- 			`CheckStatus` = p_CheckStatus and
-- 			`DateStarted` = p_DateStarted and
-- 			`DateClosed` = p_DateClosed);
-- END$$

-- create procedure read_PosCheck
-- (
-- 	IN p_UserID
--     IN 
-- )
-- BEGIN
-- 	select * from pizzaposdb.PosCheck;
-- END$$

-- create procedure delete_PosCheck
-- (

-- )
-- BEGIN
-- 	UPDATE `pizzaposdb`.`poscheck`
-- 	SET
-- 		`ID` = <{ID: }>,
-- 		`UUID` = <{UUID: }>,
-- 		`TableID` = <{TableID: }>,
-- 		`UserID` = <{UserID: }>,
-- 		`CheckStatus` = <{CheckStatus: }>,
-- 		`DateStarted` = <{DateStarted: }>,
-- 		`DateClosed` = <{DateClosed: }>
-- 	WHERE `ID` = <{expr}>;
-- END$$

create procedure create_PosTables
(
	IN p_UUID varchar(36),
    IN p_SortValue int,
    IN p_IsActive bit,
    IN p_TableName varchar(50),
    IN p_StoreID int,
    IN p_TableStatus int,
    OUT p_ID int
)
Begin
	INSERT INTO `pizzaposdb`.`postables`
		(`UUID`,
		`SortValue`,
		`IsActive`,
		`TableName`,
		`StoreID`,
		`TableStatus`)
	VALUES
		(p_UUID,
        p_SortValue,
		p_IsActive,
		p_TableName,
		p_StoreID,
		p_TableStatus);
	SET p_ID = (SELECT `postables`.`ID`
		FROM `pizzaposdb`.`postables`
        Where `postables`.`UUID` = p_UUID and
			`postables`.`SortValue` = p_SortValue and
			`postables`.`IsActive` = p_IsActive and
			`postables`.`TableName` = p_TableName and
			`postables`.`StoreID` = p_StoreID and
			`postables`.`TableStatus` = p_TableStatus);
END$$

create procedure read_PosTable
(
	IN p_StoreID int,
    IN IsActive bit
)
BEGIN
	SELECT * FROM `pizzaposdb`.`postables`
    WHERE `StoreID` = p_StoreID and
		`IsActive` = p_IsActive;
END$$

create procedure update_PosTable
(
	IN p_ID int,
    IN p_UUID varchar(36),
    IN p_SortValue int,
    IN p_IsActive bit,
    IN p_TableName varchar(50),
    IN p_StoreID int,
    IN p_TableStatus int,
    OUT retVal int
)
BEGIN
	UPDATE `pizzaposdb`.`postables`
		SET
			`sortValue` = p_SortValue,
			`isActive` = p_IsActive,
			`TableName` = p_TableName,
			`StoreID` = p_StoreID,
			`TableStatus` = p_TableStatus
		WHERE `ID` = p_ID AND `UUID` = p_UUID;
	SET retVal = 1;
END$$

create procedure delete_PosTable
(
	IN p_ID int,
    IN p_UUID varchar(36),
    IN p_IsActive bit,
    OUT retVal int
)
BEGIN
	UPDATE `pizzaposdb`.`postables`
		SET
			`IsActive` = p_IsActive
		WHERE `ID` = p_ID AND `UUID` = p_UUID;
	SET retVal = 1;
END$$

create procedure create_Ticket
(
	IN p_UUID varchar(36),
    IN p_SortValue int,
    IN p_IsActive bit,
    IN p_DateStarted date,
    IN p_UserID int,
    IN p_TableID int,
    IN p_TicketStatus int,
    IN p_TicketType int,
    OUT p_ID int
)
BEGIN
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
		(p_UUID,
        p_SortValue,
        p_IsActive,
        p_DateStarted,
        p_UserID,
        p_TableID,
        p_TicketStatus,
        p_TicketType);
	SET p_ID = (SELECT `ticket`.`ID`
		FROM `pizzaposdb`.`ticket`
        WHERE `ticket`.`UUID` = p_UUID AND
		`ticket`.`SortValue` = p_SortValue AND
		`ticket`.`IsActive` = p_IsActive AND
		`ticket`.`DateStarted` = p_DateStarted AND
		`ticket`.`UserID` = p_UserID AND
		`ticket`.`TableID` = p_TableID AND
		`ticket`.`TicketStatus`= p_TicketStatus AND
		`ticket`.`TicketType`= p_TicketType);
END$$

create procedure read_Ticket
(
	IN p_UserID int,
    IN p_TableID int,
    IN p_IsActive int
)
BEGIN
	Select * from `pizzaposdb`.`Ticket`
    WHERE `UserID` = p_UserID and
    `TableID` = p_TableID and
    `IsActive` = p_IsActive;
END$$

create procedure update_Ticket
(
	IN p_ID int,
    IN p_UUID varchar(36),
    IN p_SortValue int,
    IN p_IsActive bit,
    IN p_DateStarted date,
    IN p_UserID int,
    IN p_TableID int,
    IN p_TicketStatus int,
    IN p_TicketType int,
    OUT retVal int
)
BEGIN
	UPDATE `pizzaposdb`.`ticket`
		SET
			`SortValue` = p_SortValue,
			`IsActive` = p_IsActive,
			`DateStarted` = p_DateStarted,
			`UserID` = p_UserID,
			`TableID` = p_TableID,
			`TicketStatus` =p_TicketStatus,
			`TicketType` = p_TicketType
		WHERE `ID` = p_ID and `UUID` = p_UUID;
	SET retVal = 1;
END$$

create procedure delete_Ticket
(
	IN p_ID int,
    IN p_UUID varchar(36),
    IN p_IsActive bit,
    OUT retVal int
)
BEGIN
	UPDATE `pizzaposdb`.`Ticket`
		SET `IsActive` = p_IsActive
        WHERE `ID` = p_ID and `UUID` = p_UUID;
	SET retVal = 1;
END$$
