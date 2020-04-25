package models;

/**
 * This class is to build the ticket object, which is inherited by two other
 * classes.
 *
 * @author Sultan Albogami, Ian Wilhelmsen Last Updated: 4/23/2020
 */

import java.util.Date;
import database.DatabaseConstants;
import java.util.ArrayList;

@ModelAnnotations(key = DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_TICKET_VALUE)
public class Ticket extends ModelObject {

	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_DATE_STARTED_VALUE)
	private Date dateStarted;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_USER_ID_VALUE)
	private int userId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TABLE_ID_VALUE)
	private int tableId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TICKET_STATUS_VALUE)
	private int ticketStatus;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TICKET_TYPE_VALUE)
	private int ticketType;

	private ArrayList<TicketItem> ticketItems = new ArrayList<TicketItem>();	

	Ticket(Date _dateStarted, int _userId, int _tableId, int _ticketStatus, int _ticketType) {
		super();
		this.setDateStarted(_dateStarted);
		this.setUserId(_userId);
		this.setTableId(_tableId);
		this.setTicketStatus(_ticketStatus);
		this.setTicketType(_ticketType);
	}

	Ticket(int _id, String uuid, int _sortValue, boolean _isActive, Date _dateStarted, int _userId, int _tableId, int _ticketStatus, int _ticketType) {
		this.setId(_id);
		this.setUuid(uuid);
		this.setSortValue(_sortValue);
		this.setIsActive(_isActive);
		this.setDateStarted(_dateStarted);
		this.setUserId(_userId);
		this.setTableId(_tableId);
		this.setTicketStatus(_ticketStatus);
		this.setTicketType(_ticketType);
	}

	/**
	 * Called when an item is added to the ticket.
	 *
	 * @param MenuItem _item
	 * @return boolean This verifies that method was successful
	 */
	public boolean addMenuItem(MenuItem _item) {
		TicketItem ticketItem = new TicketItem(_item, this.getId());
		return this.ticketItems.add(ticketItem);
	}

	/**
	 * Called when an item is removed from the ticket.
	 *
	 * @param _listIndex
	 * @return boolean This verifies that method was successful
	 */
	boolean removeMenuItem(int _listIndex) {
		return this.ticketItems.remove(this.ticketItems.get(_listIndex));
	}

	/**
	 * This method gets the collective price of all the ticketitems associated with this ticket.
	 * @return
	 */
	public double getPrice() {
		double retVal = 0.0;
		for(TicketItem currItem : this.ticketItems) {
			retVal += currItem.getItemPrice();
		}
		return retVal;
	}

	/**
	 * This method adjusts the price a ticket item in the list of this class.
	 * @param _index
	 * @param _price
	 * @return
	 */
	public boolean adjustPriceOfIndexItem(int _index, double _price) {
		boolean retVal = false;
		retVal = (this.ticketItems.get(_index).getClass().toString() == DatabaseConstants.TICKET_CLASS_NAME);
		if(retVal) {
			TicketItem targetItem = this.ticketItems.get(_index);
			this.ticketItems.set(_index, targetItem);
		}
		return retVal;
	}

	/**
	 * @override This method returns a formated string of the instance specific
	 *           information.
	 * 
	 * @return string formated string that contains parameters from this instance
	 */
	public String toString() {
		return "\n\n================= Ticket " + this.getId() + " =================\n" + "Date & Time:\t"
				+ this.dateStarted + "\nUser ID:\t" + this.userId + "\nTable ID:\t" + this.tableId + "\nTicket Status:\t"
				+ this.ticketStatus + "\n\n";
	}

	// ================= GETTERS ==========================
	public Date getDateStarted() {
		return this.dateStarted;
	}

	public int getUserId() {
		return this.userId;
	}

	public int getTableId() {
		return this.tableId;
	}

	public int getTicketStatus() {
		return this.ticketStatus;
	}

	public int getTicketType() {
		return this.ticketType;
	}

	// ================= SETTERS ==========================
	public void setDateStarted(Date _dateStarted) {
		this.dateStarted = _dateStarted;
	}

	public void setUserId(int _userId) {
		this.userId = _userId;
	}

	public void setTableId(int _tableId) {
		this.tableId = _tableId;
	}

	public void setTicketStatus(int _ticketStatus) {
		this.ticketStatus = _ticketStatus;
	}

	public void setTicketType(int _ticketType) {
		this.ticketType = _ticketType;
	}
}