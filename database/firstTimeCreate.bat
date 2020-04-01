set /P inputPath=Enter Path to Project: 
cd \Program Files\MySQL\MySQL Server 8.0\bin\
mysql -u root --password=CSC390Q --host=localhost -e "source %inputPath%\src\database\createOTRUser.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\createDB.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\luInserts.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\createUser.sql"
exit