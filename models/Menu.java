package models;

import java.util.ArrayList;
import database.DatabaseConstants;
import models.MenuItem;

/**
 * This class is used to organize items to be consumed by the menu view
 * 
 * @author Ashimchalise
 * @since UPDATED: 2/12/20
 */
@ModelAnnotations(key = DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_MENU_TABLE_VALUE)
public class Menu extends ModelObject{
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_MENU_NAME_VALUE)
	private String menuName;
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_MENU_TYPE_VALUE)
	private MenuType menuType;

	public Menu(int _ID, String _UUID, String _menuName, MenuItem[] _items, MenuType _menuType) {
		this.setId(_ID);
		this.setUuid(_UUID);
		this.setMenuName(_menuName);
		this.setItems(_items);
		this.setMenuType(_menuType);
	}

	// ====================GETTERS====================

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
