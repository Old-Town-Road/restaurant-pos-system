create procedure createMenu
(
	IN uuid varchar(36),
    IN sortValue int,
    IN isActive bit,
    IN StoreID int,
    IN MenuType int,
    IN MenuName varchar(50),
    OUT ID int
)
begin
	INSERT INTO `pizzaposdb`.`menu`
		(`UUID`,
		`sortValue`,
		`isActive`,
		`StoreID`,
		`MenuType`,
		`MenuName`)
	VALUES
		(uuid,
		sortValue,
		isActive,
		StoreID,
		MenuType,
        MenuName);
	set ID = (SELECT `menu`.`ID` FROM `pizzaposdb`.`menu` where
		`UUID` = uuid and
		`sortValue` = sortValue and
		`isActive` = isActive and
		`StoreID` = StoreID and
		`MenuType` = MenuType and
		`MenuName` = MenuName);
end;