package Models;

/**
 * This class is construct table model and allow some table functionalities.
 *
 * @author Sultan Albogami Last Updated: 2/6/2020
 */

public class Table {

    private int id;
    private int userId;
    private int ticketId;
    private int checkId;
    private String name;

    public Table(int _id, int _userId, int _ticketId, int _checkId, String _name) {
        this.id = _id;
        this.userId = _userId;
        this.ticketId = _ticketId;
        this.checkId = _checkId;
        this.name = _name;
    }

    /**
     * Called when a new check is opened.
     *
     * @return
     */
    boolean startCheck(int _userId) {
        return true;
    }

    /**
     * Called when a ticket is added to the check.
     *
     * @return
     */
    boolean addTicket(int _ticketId) {
        return true;
    }

    /**
     * Called when a ticket is cashed out.
     *
     * @return
     */
    boolean cashOutTicket(int _checkId) {
        return true;
    }

    // ================= GETTERS ==========================

    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return this.userId;
    }

    public int getTicketId() {
        return this.ticketId;
    }

    public int getCheckId() {
        return this.checkId;
    }

    public String getName() {
        // Return the name
        return this.name;
    }

    // ================= SETTERS ==========================

    public void setId(int _id) {
        this.id = _id;
    }

    public void setUserId(int _userId) {
        this.userId = _userId;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public void setTicketId(int _ticketId) {
        this.ticketId = _ticketId;
    }

    public void setCheckId(int _checkId) {
        this.checkId = _checkId;
    }
}