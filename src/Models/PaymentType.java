package Models;

public class PaymentType {
	public boolean loadPaymentTypeByStoreID(int StoreID) {
		return true;
	};
	private enum paymentType {
		cash, card
	}
	public PaymentType(Object paymentType) {

	}
}
