set /P inputPath=Enter Path to Project: 
cd \Program Files\MySQL\MySQL Server 8.0\bin\
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost
source %inputPath%\src\database\mysql\DROP_CREATE.sql
source %inputPath%\src\database\mysql\luInserts.sql
source %inputPath%\src\database\mysql\dropCreateUser.sql
exit