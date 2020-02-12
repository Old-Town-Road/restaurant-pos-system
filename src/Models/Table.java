package Models;

/**
 * This class is to construct table model and allow some table functionalities.
 *
 * @author Sultan Albogami Last Updated: 2/6/2020
 */

public class Table {

    protected int tableId;
    protected int userId;
    protected int ticketId;
    protected int checkId;
    protected String tableName;

    public Table(int _tableId, int _userId, int _ticketId, int _checkId, String _tableName) {
        this.tableId = _tableId;
        this.userId = _userId;
        this.ticketId = _ticketId;
        this.checkId = _checkId;
        this.tableName = _tableName;
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
    public int getTableId() {
        return this.tableId;
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

    public String getTableName() {
        return this.tableName;
    }

    // ================= SETTERS ==========================
    public void setTableId(int _tableId) {
        this.tableId = _tableId;
    }

    public void setUserId(int _userId) {
        this.userId = _userId;
    }

    public void setTicketId(int _ticketId) {
        this.ticketId = _ticketId;
    }

    public void setCheckId(int _checkId) {
        this.checkId = _checkId;
    }
    public void setTableName(String _tableName) {
        this.tableName = _tableName;
    }
}