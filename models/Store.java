package models;

/**
 * This class is to build store object as part of the system.
 *
 * @author Sultan Albogami Last Updated: 2/6/2020
 */

public class Store {

	int storeId;
	String storeName;

	public Store(int _storeId, String _storeName) {
		this.storeId = _storeId;
		this.storeName = _storeName;
	}

	// ================= GETTERS ==========================
	public int getStoreId() {
		return this.storeId;
	}

	public String getStoreName() {
		return this.storeName;
	}

	// ================= SETTERS ==========================
	public void setStoreId(int _storeId) {
		this.storeId = _storeId;
	}

	public void setStoreName(String _storeName) {
		this.storeName = _storeName;
	}
}