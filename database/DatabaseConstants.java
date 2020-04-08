package database;

public class DatabaseConstants {
	//Database table name values.
	public static final String TABLE_NAME_ANNOTATION = "TableName";
	public static final String DB_TABLE_MENU_TABLE_VALUE = "Menu";
	public static final String DB_TABLE_MENU_ITEM_VALUE = "MenuItem";
	public static final String DB_TABLE_CHECK_VALUE = "PosCheck";
	public static final String DB_TABLE_TABLES_VALUE = "PosTables";
	public static final String DB_TABLE_STORE_VALUE = "Store";
	public static final String DB_TABLE_TICKET_VALUE = "Ticket";
	public static final String DB_TABLE_USER_VALUE = "UserLU";

	public static final String TARGET_SUPER_CLASS = "java.lang.Object";

	//Database column key.
	public static final String DB_COLUMN_NAME_KEY = "colName";

	//Column names from the database for the main object
	public static final String DB_ID_VALUE = "ID";
	public static final String DB_UUID_VALUE = "UUID";
	public static final String DB_IS_ACTIVE_VALUE = "IsActive";
	public static final String DB_SORT_VALUE_VALUE = "SortValue";

	//Menu object database column constants.
	public static final String DB_STORE_ID_VALUE = "StoreID"; 
	public static final String DB_MENU_TYPE_VALUE = "MenuType";
	public static final String DB_MENU_NAME_VALUE = "MenuName";

	//MenuItem object database column constants.
	public static final String DB_MENU_ID_VALUE = "MenuID";
	public static final String DB_ITEM_NAME_VALUE = "ItemName";
	public static final String DB_PRICE_VALUE = "Price";
	public static final String DB_PRIORITY_SCORE_VALUE = "PriorityScore";
	public static final String DB_EXECUTION_TIME_VALUE = "ExecutionTime";

	//Check object database column constants.
	public static final String DB_TABLE_ID_VALUE = "TableID";
	public static final String DB_USER_ID_VALUE = "UserID";
	public static final String DB_CHECK_STATUS_VALUE = "CheckStatus";
	public static final String DB_DATE_STARTED_VALUE = "DateStarted";
	public static final String DB_DATE_CLOSED_VALUE = "DateClosed";

	//Tables object database column constants.
	public static final String DB_TABLE_NAME_VALUE = "TableName";
	public static final String DB_TABLE_STATUS_VALUE = "TableStatus";

	//Ticket object database column constants.
	public static final String DB_TICKET_STATUS_VALUE = "TicketStatus";
	public static final String DB_TICKET_TYPE_VALUE = "TicketType";
}
