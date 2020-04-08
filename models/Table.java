package models;

import database.DatabaseConstants;

/**
 * This class is to construct table model and allow some table functionalities.
 *
 * @author Sultan Albogami, Ian Wilhelmsen Last Updated: 2/19/2020
 * 
 *         NOTES: 2/19/2020 ->adjusted formating and added method comments
 */
@ModelAnnotations(key = DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_TABLES_VALUE)
public class Table extends ModelObject{
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_USER_ID_VALUE)
	protected int userId;
	protected int ticketId;
	protected int checkId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TABLE_NAME_VALUE)
	protected String tableName;

	public Table(int _ID, String _UUID, int _userId, int _ticketId, int _checkId, String _tableName) {
		this.setId(_ID);
		this.setUuid(_UUID);
		this.userId = _userId;
		this.ticketId = _ticketId;
		this.checkId = _checkId;
		this.tableName = _tableName;
	}

	/**
	 * Called when a new check is opened.
	 * 
	 * @param int _userId the userId that is starting the check
	 * @return boolean A return status to check if an operation succeeded
	 */
	boolean startCheck(int _userId) {
		return true;
	}

	/**
	 * Called when a ticket is added to the check.
	 *
	 * @param int _ticketId the ticketId with items that we are adding to the
	 *            running total of the check
	 * @return boolean A return status to check if an operation succeeded
	 */
	boolean addTicket(int _ticketId) {
		return true;
	}

	/**
	 * Called when a ticket is cashed out.
	 *
	 * @param int _checkId the id of the check to cash out
	 * @return boolean a return status to check if the method succeeded
	 */
	boolean cashOutTicket(int _checkId) {
		return true;
	}

	// ================= GETTERS ==========================
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