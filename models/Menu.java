package models;

import java.util.ArrayList;
import models.MenuItem;

/**
 * This class is used to organize items to be consumed by the menu view
 * 
 * @author Ashimchalise
 * @since UPDATED: 2/12/20
 */
public class Menu {
	private int ID;
	private String menuName;
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	private MenuType menuType;

	public Menu(int _ID, String _menuName, MenuItem[] _items, MenuType _menuType) {
		this.ID = _ID;
		this.menuName = _menuName;
		this.setItems(_items);
		this.setMenuType(_menuType);
	}

	// ====================GETTERS====================
	public int getID() {
		return this.ID;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public ArrayList<MenuItem> getItems() {
		return this.items;
	}

	public MenuType getMenuType() {
		return this.menuType;
	}

	// ====================SETTERS====================

	public void setID(int _ID) {
		this.ID = _ID;
	}

	public void setMenuName(String _menuName) {
		this.menuName = _menuName;
	}

	public void setItems(MenuItem[] _items) {
		for (MenuItem items : _items) {
			this.items.add(items);
		}
	}

	public void setMenuType(MenuType _menuType ) {
		this.menuType = _menuType;
	}
}
