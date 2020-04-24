package models;

/**
 * This class is used to organize items to be consumed by the menu view.
 * 
 * @author Ashim Chalise, Ian Wilhelmsen
 * @since UPDATED: 4/23/2020
 */
import java.util.ArrayList;
import database.DatabaseConstants;
import models.MenuItem;

@ModelAnnotations(key = DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_MENU_TABLE_VALUE)
public class Menu extends ModelObject{
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_MENU_NAME_VALUE)
	private String menuName;
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_MENU_TYPE_VALUE)
	private int menuType;

	public Menu(String _menuName, int _menuType) {
		super();
		this.setMenuName(_menuName);
		this.setMenuType(_menuType);
	}
	public Menu(int _id, String _uuid, int _sortValue, boolean _isActive, String _menuName, int _menuType) {
		this.setId(_id);
		this.setUuid(_uuid);
		this.setSortValue(_sortValue);
		this.setIsActive(_isActive);
		this.setMenuName(_menuName);
		this.setMenuType(_menuType);
	}

	// ====================GETTERS====================

	public String getMenuName() {
		return this.menuName;
	}

	public ArrayList<MenuItem> getItems() {
		return this.items;
	}

	public int getMenuType() {
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

	public void setMenuType(int _menuType ) {
		this.menuType = _menuType;
	}
}
