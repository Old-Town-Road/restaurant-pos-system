package database;

import java.util.ArrayList;
import models.Menu;
import models.MenuItem;
import models.Table;
import models.Ticket;
import models.User;
import models.ModelObject;

public class DatabaseStandardReads {
	/**
	 * This method returns Menus with their MenuItems attached
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<Menu> getFilledListOfMenus() throws ClassNotFoundException {
		ArrayList<Menu> retVal = new ArrayList<Menu>();
		ArrayList<ModelObject> tempRetVal = DataStoreAdapter.readObject(DatabaseConstants.getReadActiveMenuKVPairs(),
				Class.forName(DatabaseConstants.MENU_CLASS_NAME));
		// For each member cast each to the Menu class and assign each it's menu items
		for (ModelObject currObj : tempRetVal) {
			Menu currMenu = (Menu) currObj;
			currMenu.setItems((MenuItem[]) getListOfMenuItemsByMenuId(currMenu.getId()).toArray());
			retVal.add(currMenu);
		}
		return retVal;
	}

	public static ArrayList<MenuItem> getListOfMenuItemsByMenuId(int _menuId) throws ClassNotFoundException {
		ArrayList<MenuItem> retVal = new ArrayList<MenuItem>();
		ArrayList<ModelObject> tempRetVal = DataStoreAdapter.readObject(
				DatabaseConstants.getReadActiveMenuItemKVPairs(_menuId),
				Class.forName(DatabaseConstants.MENU_ITEM_CLASS_NAME));
		// Cast each member to the MenuItem class.
		for (ModelObject currObj : tempRetVal) {
			retVal.add((MenuItem) currObj);
		}
		return retVal;
	}

	public static ArrayList<Table> getListOfTables() throws ClassNotFoundException {
		ArrayList<Table> retVal = new ArrayList<Table>();
		ArrayList<ModelObject> tempRetVal = DataStoreAdapter.readObject(DatabaseConstants.getReadActiveTableKVPairs(),
				Class.forName(DatabaseConstants.TABLE_CLASS_NAME));
		// Cast each member as a Table.
		for (ModelObject currObj : tempRetVal) {
			retVal.add((Table) currObj);
		}
		return retVal;
	}
	
	public static ArrayList<Ticket> getListOfTicketsByUserIdTableId(int _userId, int _tableId) throws ClassNotFoundException {
		ArrayList<Ticket> retVal = new ArrayList<Ticket>();
		ArrayList<ModelObject> tempRetVal = DataStoreAdapter.readObject(DatabaseConstants.getReadActiveTicketKVPairs(_userId, _tableId), Class.forName(DatabaseConstants.TICKET_CLASS_NAME));
		for (ModelObject currObj : tempRetVal) {
			retVal.add((Ticket) currObj);
		}
		return retVal;
	}

}