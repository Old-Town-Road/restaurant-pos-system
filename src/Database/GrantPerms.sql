use PizzaPOS;

declare @sp nvarchar(50);

declare spCursor cursor
	FOR SELECT SPECIFIC_NAME FROM PizzaPOS.INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_TYPE = 'PROCEDURE';

Open spCursor
fetch next from spCursor into @sp

while @@FETCH_STATUS = 0
BEGIN
	GRANT EXECUTE ON @sp
    TO PizzaPOSUser;
END
Close spCursor;
deallocate spCursor;