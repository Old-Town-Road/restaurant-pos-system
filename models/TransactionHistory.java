package models;

/**
 * This records the information associated with the transaction history.
 * @author Ian Wilhelmsen, last updated: 4/23/2020
 *
 */
import java.util.Date;
import database.DatabaseConstants;

public class TransactionHistory extends ModelObject {
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TRANSACTION_HISTORY_FINAL_TOTAL)
	private double finalTotal;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TRANSACTION_HISTORY_CHECK_ID)
	private int checkId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_USER_ID_VALUE)
	private int userId;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TRANSACTION_HISTORY_PAYMENT_TYPE)
	private int paymentType;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TRANSACTION_HISTORY_PAYMENT_STATUS)
	private int paymentStatus;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TRANSACTION_HISTORY_PAYMENT_DATE)
	private Date paymentDate;

	public TransactionHistory(double _finalTotal, int _checkId, int _userId, int _paymentType, int _paymentStatus, Date _paymentDate) {
		this.setSortValue(DatabaseConstants.DEFAULT_SORT_VALUE);
		this.setFinalTotal(_finalTotal);
		this.setCheckId(_checkId);
		this.setUserId(_userId);
		this.setPaymentType(_paymentType);
		this.setPaymentStatus(_paymentStatus);
		this.setPaymentDate(_paymentDate);
	}

	public TransactionHistory(int _id, String _uuid, int _sortValue, boolean _isActive, double _finalTotal, int _checkId, int _userId, int _paymentType, int _paymentStatus, Date _paymentDate) {
		this.setId(_id);
		this.setUuid(_uuid);
		this.setSortValue(_sortValue);
		this.setIsActive(_isActive);
		this.setFinalTotal(_finalTotal);
		this.setCheckId(_checkId);
		this.setUserId(_userId);
		this.setPaymentType(_paymentType);
		this.setPaymentStatus(_paymentStatus);
		this.setPaymentDate(_paymentDate);
	}

//=====================Getters=====================================//
	public int getPaymentType() {
		return this.paymentType;
	}

	public int getPaymentStatus() {
		return this.paymentStatus;
	}

	public int getUserId() {
		return this.userId;
	}

	public double getFinalTotal() {
		return this.finalTotal;
	}

	public int getCheckId() {
		return this.checkId;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}
//=====================Setters=====================================//
	public void setUserId(int _userId) {
		this.userId = _userId;
	}
	
	public void setPaymentType(int _paymentType) {
		this.paymentType = _paymentType;
	}

	public void setPaymentStatus(int _paymentStatus) {
		this.paymentStatus = _paymentStatus;
	}

	public void setFinalTotal(double _finalTotal) {
		this.finalTotal = _finalTotal;
	}

	public void setCheckId(int _checkId) {
		this.checkId = _checkId;
	}

	public void setPaymentDate(Date _paymentDate) {
		this.paymentDate = _paymentDate;
	}
}