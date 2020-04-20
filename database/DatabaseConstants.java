package database;

import java.util.HashMap;
import java.util.Map;

public class DatabaseConstants {
	//Database keys for the annotations.
	public static final String TABLE_NAME_ANNOTATION = "TableName";
	public static final String DB_COLUMN_NAME_KEY = "colName";

	//Database table name values.
	public static final String DB_TABLE_MENU_TABLE_VALUE = "Menu";
	public static final String DB_TABLE_MENU_ITEM_VALUE = "MenuItem";
	public static final String DB_TABLE_CHECK_VALUE = "PosCheck";
	public static final String DB_TABLE_TABLES_VALUE = "PosTables";
	public static final String DB_TABLE_STORE_VALUE = "Store";
	public static final String DB_TABLE_TICKET_VALUE = "Ticket";
	public static final String DB_TABLE_USER_VALUE = "UserLU";
	
	//All class names.
	public static final String MENU_CLASS_NAME = "Menu";
	public static final String MENU_ITEM_CLASS_NAME = "MenuItem";
	public static final String TABLE_CLASS_NAME = "Table";
	public static final String TICKET_CLASS_NAME = "Ticket";
	public static final String USER_CLASS_NAME = "User";
	public static final String CHECK_CLASS_NAME = "Check";
	//public static final String MENU_CLASS_NAME

	public static final String TARGET_SUPER_CLASS = "java.lang.Object";
	public static final int STORE_ID_CONSTANT = 1;

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

	//User object database column constants.
	public static final String DB_USER_USERNAME_VALUE = "UserName";
	public static final String DB_USER_FIRST_NAME_VALUE = "FirstName";
	public static final String DB_USER_LAST_NAME_VALUE = "LastName";
	public static final String DB_USER_ROLEID_VALUE = "RoleID";

	//Base key value arguments.
	//public static Map<String, String>
	/**
	 * Next three methods are all about providing the key value pairs that are
	 * associated with getting menus from the db.
	 * @param _isActive
	 * @param _storeID
	 * @return
	 */
	public static Map<String, String> getReadMenuKVPairs(String _isActive, int _storeID) {
		HashMap<String, String> retVal = new HashMap<String,String>();
		retVal.put(DB_IS_ACTIVE_VALUE, _isActive);
		retVal.put(DB_STORE_ID_VALUE, String.valueOf(_storeID));
		return retVal;
	}
	public static Map<String, String> getReadMenuKVPairs(boolean _isActive, int _storeID) {
		return getReadMenuKVPairs(booleanToBit(_isActive), _storeID);
	}

	public static Map<String, String> getReadActiveMenuKVPairs() {
		return getReadMenuKVPairs(booleanToBit(true), STORE_ID_CONSTANT);
	}

	/**
	 * Next methods are all about providing the key value pairs that are
	 * associated with getting menu items from the db
	 * @param _isActive
	 * @param _menuId
	 * @return
	 */
	public static Map<String, String> getReadMenuItemKVPairs(boolean _isActive, int _menuId) {
		HashMap<String, String> retVal = new HashMap<String, String>();
		retVal.put(DB_MENU_ID_VALUE, String.valueOf(_menuId));
		retVal.put(DB_IS_ACTIVE_VALUE, booleanToBit(_isActive));
		return retVal;
	}

	public static Map<String, String> getReadActiveMenuItemKVPairs(int _menuId) {
		return getReadMenuItemKVPairs(true, _menuId);
	}

	/**
	 * Next Methods are all about providing the key value pairs that are associated
	 * with getting the table items from the db.
	 * @param _isActive
	 * @param _storeId
	 * @return
	 */
	public static Map<String, String> getReadTableKVPairs(boolean _isActive, int _storeId) {
		HashMap<String, String> retVal = new HashMap<String, String>();
		retVal.put(DB_STORE_ID_VALUE, String.valueOf(_storeId));
		retVal.put(DB_IS_ACTIVE_VALUE, booleanToBit(_isActive));
		return retVal;
	}

	public static Map<String, String> getReadActiveTableKVPairs() {
		return getReadTableKVPairs(true, STORE_ID_CONSTANT);
	}

	/**
	 * These next functions supply the key value pairs for getting the tickets from
	 * db.
	 * @param _userId
	 * @param _tableId
	 * @param _isActive
	 * @return
	 */
	public static Map<String, String> getReadTicketKVPairs(int _userId, int _tableId, boolean _isActive) {
		HashMap<String, String> retVal = new HashMap<String, String>();
		retVal.put(DB_USER_ID_VALUE, String.valueOf(_userId));
		retVal.put(DB_TABLE_ID_VALUE, String.valueOf(_tableId));
		retVal.put(DB_IS_ACTIVE_VALUE, booleanToBit(_isActive));
		return retVal;
	}

	public static Map<String, String> getReadActiveTicketKVPairs(int _userId, int _tableId) {
		return getReadTicketKVPairs(_userId, _tableId, true);
	}

	public static Map<String, String> getReadUserKVPairs(boolean _isActive, String _userName) {
		HashMap<String, String> retVal = new HashMap<String, String>();
		retVal.put(DB_IS_ACTIVE_VALUE, booleanToBit(_isActive));
		retVal.put(DB_USER_USERNAME_VALUE, _userName);
		return retVal;
	}

	public static Map<String, String> getReadActiveUserKVPairs(String _userName) {
		return getReadUserKVPairs(true, _userName);
	}

	public static Map<String, String> getReadCheckKVPairs(){
		HashMap<String, String> retVal = new HashMap<String, String>();
		return retVal;
	}

	public static String booleanToBit(boolean _value) {
		return (_value ? "1" : "0");
	}
}
