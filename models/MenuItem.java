package models;

/**
 * This class represents the menu items of the restaurant.
 * @author Ian Wilhelmsen
 * Last Updated 4/23/2020
 */

import database.DatabaseConstants;

@ModelAnnotations(key = DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_MENU_ITEM_VALUE)
public class MenuItem extends ModelObject {
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_ITEM_NAME_VALUE)
	private String itemName;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_MENU_ID_VALUE)
	private int menuID;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_PRICE_VALUE)
	private double price;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_PRIORITY_SCORE_VALUE)
	private int priorityScore;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_EXECUTION_TIME_VALUE)
	private int executionTime;

	public MenuItem(int _ID, String _UUID, int _sortValue, boolean _isActive, int _menuID, String _itemName,
			double _price, int _priorityScore, int _executionTime) {
		this.setId(_ID);
		this.setUuid(_UUID);
		this.setSortValue(_sortValue);
		this.setIsActive(_isActive);
		this.itemName = _itemName;
		this.menuID = _menuID;
		this.price = _price;
		this.priorityScore = _priorityScore;
		this.executionTime = _executionTime;
	}

	public MenuItem(int _menuID, String _itemName, double _price, int _priorityScore, int _executionTime) {
		super();
		this.itemName = _itemName;
		this.menuID = _menuID;
		this.price = _price;
		this.priorityScore = _priorityScore;
		this.executionTime = _executionTime;
	}

	public MenuItem() {
	}

//====================GETTERS====================

	public String getItemName() {
		return this.itemName;
	}

	public double getPrice() {
		return this.price;
	}

	public int getPriorityScore() {
		return this.priorityScore;
	}

	public int getExecutionTime() {
		return this.executionTime;
	}

	public int getMenuID() {
		return menuID;
	}

//====================SETTERS====================

	public void setItemName(String _itemName) {
		this.itemName = _itemName;
	}

	public void setPrice(double _price) {
		this.price = _price;
	}

	public void setPrioritySCore(int _priorityScore) {
		this.priorityScore = _priorityScore;
	}

	public void setExectuionTime(int _executionTime) {
		this.executionTime = _executionTime;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
}