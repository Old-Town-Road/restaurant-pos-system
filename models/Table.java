package models;

/**
 * This class is to construct table model and allow some table functionalities.
 *
 * @author Sultan Albogami, Ian Wilhelmsen
 * Last Updated: 4/23/2020
 */
import database.DatabaseConstants;

@ModelAnnotations(key = DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_TABLES_VALUE)
public class Table extends ModelObject{
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_TABLE_NAME_VALUE)
	private String tableName;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_STORE_ID_VALUE)
	private int storeId;

	public Table(String _tableName) {
		super();
		this.setTableName(_tableName);
		this.setStoreId(ModelConstants.STORE_TYPE_POS);
	}

	public Table(int _id, String _uuid, int _sortValue, boolean _isActive, String _tableName) {
		this.setId(_id);
		this.setUuid(_uuid);
		this.setSortValue(_sortValue);
		this.setIsActive(_isActive);
		this.setTableName(_tableName);
		this.setStoreId(ModelConstants.STORE_TYPE_POS);
	}

	// ================= GETTERS ==========================
	public String getPOSTableName() {
		return this.tableName;
	}

	public int storeId() {
		return this.storeId;
	}
	// ================= SETTERS ==========================
	public void setTableName(String _tableName) {
		this.tableName = _tableName;
	}

	public void setStoreId(int _storeId) {
		this.storeId = _storeId;
	}
}