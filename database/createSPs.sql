Delimiter $$
create procedure create_Menu 
(
	IN p_uuid varchar(36),
	IN p_sortValue int,
    IN p_isActive bit,
    IN p_StoreID int,
    IN p_MenuType int,
    IN p_MenuName varchar(50),
    OUT p_ID int
)
BEGIN
	INSERT INTO `pizzaposdb`.`menu`
    (
		`UUID`,
        `sortValue`,
        `isActive`,
        `StoreID`,
        `MenuType`,
        `MenuName`
	) VALUES
    (
		p_uuid,
        p_sortValue, 
        p_isActive,
        p_StoreID, 
        p_MenuType,
        p_MenuName
	);
	set p_ID = (SELECT `menu`.`ID` FROM `pizzaposdb`.`menu` where
		`UUID` = p_uuid and
		`sortValue` = p_sortValue and
		`isActive` = p_isActive and
		`StoreID` = p_StoreID and
		`MenuType` = p_MenuType and
		`MenuName` = p_MenuName);
END$$

create procedure read_Menu
(
	in p_storeID int,
    in p_isActive int
)
BEGIN
	select * from `pizzaposdb`.`menu`
    WHERE `menu`.`isActive` = p_isActive and
		`menu`.`StoreID` = p_storeID;
END$$

create procedure update_Menu
(
	IN p_ID int,
    IN p_UUID varchar(36),
    IN p_sortValue int,
    IN p_isActive bit,
    IN p_StoreID int,
    IN p_MenuType int,
    IN p_MenuName varchar(50),
    OUT retVal int
)
BEGIN
	UPDATE `pizzaposdb`.`menu` SET
		`sortValue` = p_sortValue,
		`isActive` = p_isActive,
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
    in p_isActive bit,
    OUT p_retVal int
)
BEGIN
	UPDATE `pizzaposdb``menu` SET
		`isActive` = p_isActive
	where `UUID` = p_UUID and `ID` = p_ID;
    SET p_retVal = 1;
END$$

create procedure create_MenuItem
(
	IN p_uuid varchar(36),
	IN p_sortValue int,
    IN p_isActive bit,
    IN p_MenuID int,
    IN p_ItemName varchar(50),
    IN p_Price double,
    IN p_PriorityScore int,
    IN p_ExecutionTime int,
    OUT p_ID int
)
BEGIN
	INSERT INTO `pizzaposdb`.`menu`
    (
		`UUID`,
        `sortValue`,
        `isActive`,
        `MenuID`,
        `ItemName`,
        `Price`,
        `PriorityScore`,
        `ExecutionTime`
	) VALUES
    (
		p_uuid,
        p_sortValue, 
        p_isActive,
        p_MenuID,
		p_ItemName,
		p_Price,
		p_PriorityScore,
		p_ExecutionTime
	);
	set p_ID = (SELECT `menuitem`.`ID` FROM `pizzaposdb`.`menuitem` where
		`UUID` = p_uuid and
		`sortValue` = p_sortValue and
		`isActive` = p_isActive and
		`MenuID` = p_MenuID and
        `ItemName` = p_ItemName and
        `Price` = p_Price and
        `PriorityScore` = p_PriorityScore and
        `ExecutionTime` = p_ExecutionTime);
END$$

create procedure read_MenuItem
(
	in p_MenuID int,
    in p_isActive int
)
BEGIN
	select * from `pizzaposdb`.`menuitem`
    WHERE `menu`.`isActive` = p_isActive and
		`menu`.`MenuID` = p_MenuID;
END$$

create procedure update_Menu
(
	IN p_ID int,
    IN p_uuid varchar(36),
	IN p_sortValue int,
    IN p_isActive bit,
    IN p_MenuID int,
    IN p_ItemName varchar(50),
    IN p_Price double,
    IN p_PriorityScore int,
    IN p_ExecutionTime int,
    OUT retVal int
)
BEGIN
	UPDATE `pizzaposdb`.`menu` SET
		`sortValue` = p_sortValue,
		`isActive` = p_isActive,
		`MenuID` = p_MenuID,
		`` = p_MenuType,
		`MenuName` = p_MenuName
	WHERE `UUID` = p_UUID and `ID` = p_ID;
	set retVal = 1;
END$$

create procedure delete_Menu
(
	in p_ID int,
    in p_UUID int,
    in p_isActive bit,
    OUT p_retVal int
)
BEGIN
	UPDATE `pizzaposdb``menu` SET
		`isActive` = p_isActive
	where `UUID` = p_UUID and `ID` = p_ID;
    SET p_retVal = 1;
END$$