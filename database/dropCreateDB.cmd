set /P inputPath=Enter Path to Project Directory: 
set /P mysqlPath=Enter Path to MySQL 8.0 bin Directory: 
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\DROP_CREATE.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\createSPs.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\luInserts.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\dropCreateUser.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\seedData.sql"
exit