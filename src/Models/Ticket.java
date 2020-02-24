package Models;

/**
 * This class is to build the ticket object, which is inherited by two other
 * classes.
 *
 * @author Sultan Albogami, Ian Wilhelmsen Last Updated: 2/19/2020
 * 
 * NOTES:
 * 2/19/2020 IMW
 * -> Changed the type of _ticketStatus to new enum created.
 * -> Verified the styling.
 * -> Added descriptions for some methods
 */

import java.util.Date;

public class Ticket {

    protected int ticketId;
    protected Date dateTime;
    protected int userId;
    protected int tableId;
    protected TicketStatus ticketStatus;

    Ticket(int _ticketId, Date _dateTime, int _userId, int _tableId, TicketStatus _ticketStatus) {
	this.ticketId = _ticketId;
	this.dateTime = _dateTime;
	this.userId = _userId;
	this.tableId = _tableId;
	this.ticketStatus = _ticketStatus;
    }

    /**
     * Called when a ticket is loaded.
     *
     * @param int _ticketId
     * @return boolean This verifies that method was successful
     */
    boolean loadTicket(int _ticketId) {
	return true;
	// TODO Stubbed
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
     * @override 
     * This method returns a formated string of the instance specific information.
     * 
     * @return string formated string that contains parameters from this instance
     */
    public String toString() {
	return "\n\n================= Ticket " + this.ticketId + " =================\n" + "Date & Time:\t"
		+ this.dateTime + "\nUser ID:\t" + this.userId + "\nTable ID:\t" + this.tableId + "\nTicket Status:\t"
		+ this.ticketStatus + "\n\n";
    }

    // ================= GETTERS ==========================
    public int getTicketId() {
	return this.ticketId;
    }

    public Date getDateTime() {
	return this.dateTime;
    }

    public int getUserId() {
	return this.userId;
    }

    public int getTableId() {
	return this.tableId;
    }

    public TicketStatus getTicketStatus() {
	return this.ticketStatus;
    }

    // ================= SETTERS ==========================
    public void setTicketId(int _ticketId) {
	this.ticketId = _ticketId;
    }

    public void setDateTime(Date _dateTime) {
	this.dateTime = _dateTime;
    }

    public void setUserId(int _userId) {
	this.userId = _userId;
    }

    public void setTableId(int _tableId) {
	this.tableId = _tableId;
    }

    public void setTicketStatus(TicketStatus _ticketStatus) {
	this.ticketStatus = _ticketStatus;
    }

}