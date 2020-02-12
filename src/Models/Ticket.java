package Models;

/**
 * This class is to build the ticket object, which is inherited by two other
 * classes.
 *
 * @author Sultan Albogami Last Updated: 2/11/2020
 */

import java.util.Date;

public class Ticket {

    protected int ticketId;
    protected Date dateTime;
    protected int userId;
    protected int tableId;
    // I am not clear on what menuTime is.

    enum TICKETSTATUS {
        STATUS1, STATUS2, STATUS3, STATUS4
    }

    private TICKETSTATUS ticketStatus;

    Ticket(int _ticketId, Date _dateTime, int _userId, int _tableId, TICKETSTATUS _ticketStatus) {
        this.ticketId = _ticketId;
        this.dateTime = _dateTime;
        this.userId = _userId;
        this.tableId = _tableId;
        this.ticketStatus = _ticketStatus;
    }

    /**
     * Called when a ticket is loaded.
     *
     * @param _ticketId
     * @return
     */
    boolean loadTicket(int _ticketId) {
        return true;
    }

    /**
     * Called when an item is added to the ticket.
     *
     * @param _itemId
     * @return
     */
    boolean addMenuItem(int _itemId) {
        return true;
    }

    /**
     * Called when an item is removed from the ticket.
     *
     * @param _listIndex
     * @return
     */
    boolean removeMenuItem(int _listIndex) {
        //Should check if item is present first.
        return true;
    }

    /**
     * @return
     * @override
     */
    public String toString() {
        return "\n\n================= Ticket " + this.ticketId + " =================\n" + "Date & Time:\t"
                + this.dateTime + "\nUser ID:\t" + this.userId + "\nTable ID:\t" + this.tableId +
                "\nTicket Status:\t" + this.ticketStatus + "\n\n";
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

    public TICKETSTATUS getTicketStatus() {
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

    public void setTicketStatus(TICKETSTATUS _ticketStatus) {
        this.ticketStatus = _ticketStatus;
    }
}