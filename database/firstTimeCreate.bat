set /P inputPath=Enter Path: 
cd \Program Files\MySQL\MySQL Server 8.0\bin\
mysql -u root --password=CSC340Q -h localhost < %inputPath%\src\database\mysql\createOTRUser.sql
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost
source %inputPath%\src\database\mysql\createDB.sql
source %inputPath%\src\database\mysql\luInserts.sql
source %inputPath%\src\database\mysql\createUser.sql
exit