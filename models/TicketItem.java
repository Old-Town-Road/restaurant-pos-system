package models;

import database.DatabaseConstants;

/**
 * Creates a clone of MenuItem to keep track of a change in price per ticket.
 * @author Ian Wilhelmsen, Last Updated: 4/23/2020
 */

@ModelAnnotations(key =  DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_TICKET_ITEM_VALUE)
public class TicketItem extends ModelObject {
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TICKET_ITEM_TICKET_ID_VALUE)
	private int ticketId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TICKET_ITEM_MENU_ITEM_ID_VALUE)
	private int menuItemId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TICKET_ITEM_ITEM_PRICE_VALUE)
	private double itemPrice;

	public TicketItem(int _ticketId, int _menuItemId, double _itemPrice) {
		super();
		this.setTicketId(_ticketId);
		this.setMenuItemId(_menuItemId);
		this.setItemPrice(_itemPrice);
	}

	public TicketItem(MenuItem _menuItem, int _ticketId) {
		super();
		this.setTicketId(_ticketId);
		this.setMenuItemId(_menuItem.getId());
		this.setItemPrice(_menuItem.getPrice());
	}

	public TicketItem(int _id, String _uuid, int _sortValue, boolean _isActive, int _ticketId, int _menuItemId, double _itemPrice) {
		this.setId(_id);
		this.setUuid(_uuid);
		this.setSortValue(_sortValue);
		this.setIsActive(_isActive);
		this.setTicketId(_ticketId);
		this.setMenuItemId(_menuItemId);
		this.setItemPrice(_itemPrice);
	}

	public TicketItem() {
	}

	// ================= GETTERS ==========================
	public int getTicketId() {
		return ticketId;
	}

	public int getMenuItemId() {
		return menuItemId;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	// ================= SETTERS ==========================
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
}
