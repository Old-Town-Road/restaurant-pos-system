set /P inputPath=Enter Path to Project Directory: 
c:
cd \Program Files\MySQL\MySQL Server 8.0\bin\
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\DROP_CREATE.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\createSPs.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\luInserts.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\dropCreateUser.sql"
mysql -u OTRUser --password=Burnt4Pizzas! -h localhost -e "source %inputPath%\src\database\seedData.sql"
pause
exit