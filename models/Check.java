package models;

/**
 * This class holds the transaction information for a ticket being paid for.
 * @author Ian Wilhelmsen, last updated: 4/20/2020 
 */
import java.util.Date;
import database.DatabaseConstants;

@ModelAnnotations(key = DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_CHECK_VALUE)
public class Check extends ModelObject{
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TABLE_ID_VALUE)
	private int tableId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_CHECK_STATUS_VALUE)
	private int checkStatus;
	private int paymentType;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_DATE_STARTED_VALUE)
	private Date dateStarted;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_DATE_CLOSED_VALUE)
	private Date dateClosed;

	public Check(int _id, String _uuid, boolean _isActive, int _sortValue, int _tableId, int _checkStatus, int _paymentType, Date _dateStarted, Date _dateClosed) {
		this.setId(_id);
		this.setUuid(_uuid);
		this.setTableId(_tableId);
		this.setCheckStatus(_checkStatus);
		this.setDateStarted(_dateStarted);
		this.setDateClosed(_dateClosed);
	}
//=====================Getters=====================================//
	public int getTableId() {
		return tableId;
	}

	public int getCheckStatus() {
		return checkStatus;
	}

	public int getPaymentType() {
		return this.paymentType;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public Date getDateClosed() {
		return dateClosed;
	}
//=====================Setters=====================================//
	public void setTableId(int _tableId) {
		this.tableId = _tableId;
	}

	public void setCheckStatus(int _checkStatus) {
		this.checkStatus = _checkStatus;
	}

	public void setPaymentType(int _paymentType) {
		this.paymentType = _paymentType;
	}

	public void setDateStarted(Date _dateStarted) {
		this.dateStarted = _dateStarted;
	}

	public void setDateClosed(Date _dateClosed) {
		this.dateClosed = _dateClosed;
	}
}
