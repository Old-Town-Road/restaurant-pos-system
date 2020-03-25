package models;

/**
 * This class adds some universal support to the Model classes.
 * 
 * @author Ian Wilhelmsen last updated: 3/20/2020
 */
import java.util.HashMap;
import java.util.UUID;
import database.DataStoreAdapter;

public abstract class ModelObject {
	protected int id;
	protected String uuid;
	protected String name;
	protected boolean active = true;

	public ModelObject() {
		this.setUuid(ModelObject.generateUuid());
	}

	public ModelObject loadById(int _id) {
		HashMap<String, String> map = new HashMap<>();
		map.put("id", Integer.toString(_id));
		return this.loadByCondition(map);
	}

	public ModelObject loadByUuid(String _uuid) {
		HashMap<String, String> map = new HashMap<>();
		map.put("uuid", _uuid);
		return this.loadByCondition(map);
	}

	public ModelObject loadByCondition(String _name, String _value) {
		HashMap<String, String> map = new HashMap<>();
		map.put(_name, _value);
		return this.loadByCondition(map);
	}

	public boolean saveObjectFromDatabase() {
		// Initialize a return value for the caller defaulted to false.
		boolean retVal = false;
		// Has this object already been created?
		if (this.id == 0) {
			retVal = DataStoreAdapter.createObject(this);
		} else {
			retVal = DataStoreAdapter.updateObject(this);
		}
		// Return the final value to the caller.
		return retVal;
	}

	public boolean deleteObjectFromDatabase() {
		return DataStoreAdapter.deleteObject(this);
	}

	public void makeActive() {
		this.active = true;
	}

	public void makeInactive() {
		this.active = false;
	}

	protected static String generateUuid() {
		return UUID.randomUUID().toString();
	}

// ================================ GETTERS ====================================

	public String getName() {
		return this.name;
	}

	public String getUuid() {
		return this.uuid;
	}

	public int getId() {
		return this.id;
	}

// ================================ SETTERS ====================================

	public void setName(String _name) {
		this.name = _name;
	}

	public void setUuid(String _uuid) {
		this.uuid = _uuid;
	}

	public void setId(int _id) {
		this.id = _id;
	}
}
