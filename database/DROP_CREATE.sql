Drop database if exists `pizzaposdb`;
CREATE DATABASE `pizzaposdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
Create table if not exists pizzaposdb.PosCheck (
	  ID INT NOT NULL AUTO_INCREMENT,
	  UUID varchar(36) NOT NULL,
      IsActive INT NOT NULL default 1,
      SortValue int not null default 0,
	  TableID INT NOT NULL,
	  UserID INT NOT NULL,
	  CheckStatus INT NOT NULL,
	  DateStarted DATETIME NOT NULL,
	  DateClosed datetime NULL,
	  PRIMARY KEY (ID)
);
Create table if not exists pizzaposdb.CheckStatusLU (
	ID int NOT NULL,
	CheckStatus varchar(50) NULL,
    primary key(ID)
 );
Create table if not exists pizzaposdb.Menu(
	ID int NOT NULL auto_increment,
    UUID varchar(36) NOT NULL,
    IsActive INT NOT NULL default 1,
    SortValue int NOT NULL default 0,
	StoreID int NOT NULL,
    MenuName varchar(64) NULL,
	MenuType int NOT NULL,
	PRIMARY KEY (ID)
);
Create table if not exists pizzaposdb.MenuItem  (
	ID INT NOT NULL AUTO_INCREMENT,
    UUID varchar(36) NOT NULL,
    SortValue int NOT NULL default 0,
    IsActive INT NOT NULL default 1,
	MenuID int NOT NULL,
	ItemName varchar(64) NOT NULL,
	Price double NOT NULL,
	PriorityScore int NULL,
	ExecutionTime int NULL,
	primary key(ID)
);
Create table if not exists pizzaposdb.MenuTypeLU  (
	ID int NOT NULL,
	MenuType varchar(50) NULL,
    primary key(ID)
); 
Create table if not exists pizzaposdb.PaymentStatusLU  (
	ID INT NOT NULL AUTO_INCREMENT,
	PaymentStatusType varchar(64) NOT NULL,
	primary key(ID)
); 

Create table if not exists pizzaposdb.PaymentTypeLU (
	ID INT NOT NULL AUTO_INCREMENT,
	PaymentType varchar(64) NULL,
    primary key(ID)
); 

Create table if not exists pizzaposdb.RoleLU  (
	ID int NOT NULL,
	RoleType varchar(64) NULL,
	primary key(ID)
); 

Create table if not exists pizzaposdb.Store  (
	ID int NOT NULL,
	StoreName varchar(64) NOT NULL,
	primary key(ID)
); 

Create table if not exists pizzaposdb.POSTables  (
	ID INT NOT NULL AUTO_INCREMENT,
	UUID varchar(36) NOT NULL,
    sortValue int NOT NULL default 0,
    isActive INT NOT NULL default 1,
	TableName varchar(64) NOT NULL,
	StoreID int NOT NULL,
	primary key(ID)
); 

Create table if not exists pizzaposdb.TableStatusLU  (
	ID int NOT NULL,
	TableStatus varchar(64) NULL,
	primary key(ID)
); 

CREATE TABLE IF NOT EXISTS pizzaposdb.Ticket (
    ID INT NOT NULL AUTO_INCREMENT,
	UUID varchar(36) NOT NULL,
    sortValue int NOT NULL default 0,
    isActive INT NOT NULL default 1,
    DateStarted DATE NOT NULL,
    UserID INT NOT NULL,
    TableID INT NOT NULL,
    TicketStatus INT NOT NULL,
    TicketType INT NOT NULL,
    PRIMARY KEY (ID)
); 

Create table if not exists pizzaposdb.TicketStatusLU  (
	ID INT NOT NULL AUTO_INCREMENT,
	TicketStatus varchar(64) NULL,
	primary key(ID)
); 

CREATE TABLE IF NOT EXISTS pizzaposdb.TicketTypeLU (
    ID INT NOT NULL,
    TicketType varchar(64) NULL,
    PRIMARY KEY (ID)
); 

CREATE TABLE IF NOT EXISTS pizzaposdb.TransactionHistory (
    ID INT NOT NULL AUTO_INCREMENT,
    UUID varchar(36) not null,
    IsActive INT default 1,
    SortValue int default 0,
    CheckID INT NOT NULL,
    UserID INT NOT NULL,
    FinalTotal double not null,
    PaymentType INT NOT NULL,
    PaymentDate DATE NOT NULL,
    PaymentStatus INT NOT NULL,
    PRIMARY KEY (`ID`)
); 

Create table if not exists pizzaposdb.UserLU  (
	ID INT NOT NULL AUTO_INCREMENT,
	UserName varchar(64) NOT NULL,
	FirstName varchar(64) NOT NULL,
	LastName varchar(64) NOT NULL,
	RoleID int NOT NULL,
    UUID varchar(36),
    IsActive int default 1,
    SortValue int default 0,
    primary key(`ID`)
);

create table if not exists pizzaposdb.TicketItem (
	ID int not null auto_increment,
    UUID varchar(36) not null,
    SortValue int default 0,
    IsActive INT default 1,
    TicketID int not null,
    MenuItemID int not null,
    ItemPrice double not null,
    primary key(`ID`)
);

Alter table pizzaposdb.TicketItem
	add foreign key
    ticket_ticketitem (TicketID)
    references pizzaposdb.ticket (ID)
    on update cascade
    on delete cascade;

Alter table pizzaposdb.TicketItem
	add foreign key
    menuitem_ticketitem (MenuItemID)
    references pizzaposdb.MenuItem (ID)
    on update cascade
    on delete cascade;

Alter table pizzaposdb.POSCheck
	ADD FOREIGN KEY
    check_table (TableId)
    references pizzaposdb.postables (ID)
    on update cascade
    on delete cascade;

Alter table pizzaposdb.POSCheck
	ADD FOREIGN KEY
    check_userLU (UserID)
    references pizzaposdb.UserLU (ID)
    on update cascade
    on delete cascade;
    
Alter table pizzaposdb.POSCheck
	ADD FOREIGN KEY
    check_checkstatus (CheckStatus)
    references pizzaposdb.checkstatuslu (ID)
    on update cascade
    on delete cascade;

Alter table pizzaposdb.menu
	ADD FOREIGN KEY
    menu_store (StoreID)
    references pizzaposdb.store (ID)
    on update cascade
    on delete cascade;


Alter table pizzaposdb.menu
	ADD FOREIGN KEY
    menu_menutype (menutype)
    references pizzaposdb.menutypelu (ID)
    on update cascade
    on delete cascade;

Alter table pizzaposdb.menuitem
	ADD FOREIGN KEY
    menuitem_menu (MenuID)
    references pizzaposdb.menu (ID)
    on update cascade
    on delete cascade;

alter table pizzaposdb.ticket
	add foreign key
    ticket_userid (UserID)
    references pizzaposdb.userlu (ID)
    on update cascade
    on delete cascade;

alter table pizzaposdb.ticket
	add foreign key
    ticket_resttable (TableID)
    references pizzaposdb.postables (ID)
    on update cascade
    on delete cascade;

alter table pizzaposdb.ticket
	add foreign key
    ticket_ticketstatuslu (TicketStatus)
    references pizzaposdb.ticketstatuslu (ID)
    on update cascade
    on delete cascade;
    
alter table pizzaposdb.ticket
	add foreign key
    ticket_tickettypelu (TicketType)
    references pizzaposdb.tickettypelu (ID)
    on update cascade
    on delete cascade;

alter table pizzaposdb.userlu
	add foreign key
    userlu_roleid (RoleID)
    references pizzaposdb.rolelu (ID)
    on update cascade
    on delete cascade;

alter table pizzaposdb.TransactionHistory
	add foreign key
    transact_restcheck (CheckID)
    references pizzaposdb.poscheck (ID)
    on update cascade
    on delete cascade;

alter table pizzaposdb.transactionhistory
	add foreign key
    transact_userlu (UserID)
    references pizzaposdb.userlu (ID)
    on update cascade
    on delete cascade;
    
alter table pizzaposdb.transactionhistory
	add foreign key
    transact_paymenttype (PaymentType)
    references pizzaposdb.paymenttypelu (ID)
    on update cascade
    on delete cascade;

alter table pizzaposdb.transactionhistory
	add foreign key
    transact_paymentstatus (PaymentStatus)
    references pizzaposdb.paymentstatuslu (ID)
    on update cascade
    on delete cascade;