package models;

/**
 * This class is to build the ticket object, which is inherited by two other
 * classes.
 *
 * @author Sultan Albogami, Ian Wilhelmsen Last Updated: 4/18/2020
 */

import java.util.Date;
import database.DatabaseConstants;
import java.util.ArrayList;

@ModelAnnotations(key = DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_TICKET_VALUE)
public class Ticket extends ModelObject {

	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_DATE_STARTED_VALUE)
	private Date dateTime;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_USER_ID_VALUE)
	private int userId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TABLE_ID_VALUE)
	private int tableId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TICKET_STATUS_VALUE)
	private int ticketStatus;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TICKET_TYPE_VALUE)
	private int ticketType;

	private ArrayList<MenuItem> ticketItems = new ArrayList<MenuItem>();	

	Ticket(Date _dateTime, int _userId, int _tableId, int _ticketStatus, int _ticketType) {
		this.setDateTime(_dateTime);
		this.setUserId(_userId);
		this.setTableId(_tableId);
		this.setTicketStatus(_ticketStatus);
		this.setTicketType(_ticketType);
	}

	Ticket(int _ID, String _UUID, Date _dateTime, int _userId, int _tableId, int _ticketStatus, int _ticketType) {
		this.setId(_ID);
		this.setUuid(_UUID);
		this.setDateTime(_dateTime);
		this.setUserId(_userId);
		this.setTableId(_tableId);
		this.setTicketStatus(_ticketStatus);
		this.setTicketType(_ticketType);
	}

	/**
	 * Called when an item is added to the ticket.
	 *
	 * @param int _itemId
	 * @return boolean This verifies that method was successful
	 */
	boolean addMenuItem(int _itemId) {
		return true;
		// TODO stubbed
	}

	/**
	 * Called when an item is removed from the ticket.
	 *
	 * @param _listIndex
	 * @return boolean This verifies that method was successful
	 */
	boolean removeMenuItem(int _listIndex) {
		// Should check if item is present first.
		// TODO stubbed
		return true;
	}

	/**
	 * @override This method returns a formated string of the instance specific
	 *           information.
	 * 
	 * @return string formated string that contains parameters from this instance
	 */
	public String toString() {
		return "\n\n================= Ticket " + this.getId() + " =================\n" + "Date & Time:\t"
				+ this.dateTime + "\nUser ID:\t" + this.userId + "\nTable ID:\t" + this.tableId + "\nTicket Status:\t"
				+ this.ticketStatus + "\n\n";
	}

	// ================= GETTERS ==========================
	public Date getDateTime() {
		return this.dateTime;
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
	public void setDateTime(Date _dateTime) {
		this.dateTime = _dateTime;
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