package database;

/**
 * 
 * @author Ian Wilhelmsen last update: 3/20/2020
 */

import java.util.HashMap;
import java.util.Map;
import models.ModelObject;

public class DataStoreAdapter {
	private static final DBConnectorInterface connector = new MySQLDBConnectorImpl();

	public static int createObject (ModelObject _targetObject) throws IllegalArgumentException, IllegalAccessException {
		return connector.createObject(_targetObject.getDataKeyValuePairs(), _targetObject.getTableName());
	}

	public static HashMap<String, Object> readObject (Map<String, String> _keyValuePairs, Class<?> _targetClass) {
		return connector.readObject(_keyValuePairs, _targetClass);
	}

	public static boolean updateObject (ModelObject _targetObject) throws IllegalArgumentException, IllegalAccessException {
		return connector.updateObject(_targetObject.getDataKeyValuePairs(), _targetObject.getUuid(), _targetObject.getTableName());
	}

	public static boolean deleteObject (ModelObject _targetObject) {
		//Create a hashmap of the uuid and its value.
		HashMap<String, String> keyValuePair = new HashMap<>();
		keyValuePair.put(DatabaseConstants.DB_UUID_VALUE, _targetObject.getUuid());
		return connector.deleteObject(keyValuePair, _targetObject.getTableName());
	}
}