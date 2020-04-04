package models;

public class MenuItem {
	private String itemName;
	private int menuID;
	private double price;
	private int priorityScore;
	private int executionTime;

	public MenuItem(int _ID, int _menuID, String _itemName, double _price, int _priorityScore, int _executionTime) {
		this.itemName = _itemName;
		this.menuID=_menuID;
		this.price = _price;
		this.priorityScore = _priorityScore;
		this.executionTime = _executionTime;
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
