package models;

/**
 * This class holds the transaction information for a ticket being paid for.
 * @author Ian Wilhelmsen, last updated: 4/23/2020 
 */

import java.util.Date;
import database.DatabaseConstants;
import java.util.ArrayList;
import java.util.Collection;

@ModelAnnotations(key = DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_CHECK_VALUE)
public class Check extends ModelObject{
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TABLE_ID_VALUE)
	private int tableId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_USER_ID_VALUE)
	private int userId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_CHECK_STATUS_VALUE)
	private int checkStatus;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_DATE_STARTED_VALUE)
	private Date dateStarted;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_DATE_CLOSED_VALUE)
	private Date dateClosed;

	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

	public Check(int _tableId, int _userId, int _checkStatus, Date _dateStarted, Date _dateClosed) {
		super();
		this.setTableId(_tableId);
		this.setUserId(_userId);
		this.setCheckStatus(_checkStatus);
		this.setDateStarted(_dateStarted);
		this.setDateClosed(_dateClosed);
	}

	public Check(int _id, String _uuid, int _sortValue, boolean _isActive, int _tableId, int _userId, int _checkStatus, Date _dateStarted, Date _dateClosed) {
		this.setId(_id);
		this.setUuid(_uuid);
		this.setSortValue(_sortValue);
		this.setIsActive(_isActive);
		this.setTableId(_tableId);
		this.setUserId(_userId);
		this.setCheckStatus(_checkStatus);
		this.setDateStarted(_dateStarted);
		this.setDateClosed(_dateClosed);
	}

	/**
	 * This method adds a ticket to a check.
	 * @param _ticket a single ticket object.
	 * @return boolean success value.
	 */
	public boolean addTicketToCheck(Ticket _ticket) {
		return this.tickets.add(_ticket);
	}

	/**
	 * This method adds a collection of tickets and adds them to this object's collection.
	 * @param _tickets
	 * @return boolean success value.
	 */
	public boolean addTicketsToCheck(Collection<Ticket> _tickets) {
		return this.tickets.addAll(_tickets);
	}

	/**
	 * This method returns the current check total.
	 * @return Double representing the total price of the check.
	 */
	public double getCheckTotal() {
		double retVal = 0;
		for(Ticket currTicket: this.tickets) {
			retVal += currTicket.getPrice();
		}
		return retVal;
	}

//=====================Getters=====================================//
	public ArrayList<Ticket> getTickets() {
		return this.tickets;
	}

	public int getTableId() {
		return this.tableId;
	}

	public int getUserId() {
		return this.userId;
	}

	public int getCheckStatus() {
		return this.checkStatus;
	}

	public Date getDateStarted() {
		return this.dateStarted;
	}

	public Date getDateClosed() {
		return this.dateClosed;
	}
//=====================Setters=====================================//
	public void setTableId(int _tableId) {
		this.tableId = _tableId;
	}

	public void setUserId(int _userId) {
		this.userId = _userId;
	}

	public void setCheckStatus(int _checkStatus) {
		this.checkStatus = _checkStatus;
	}

	public void setDateStarted(Date _dateStarted) {
		this.dateStarted = _dateStarted;
	}

	public void setDateClosed(Date _dateClosed) {
		this.dateClosed = _dateClosed;
	}
}
