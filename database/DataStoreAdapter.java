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

	public static boolean createObject (ModelObject _targetObject) {
		return connector.createObject(_keyValuePairs, _table);
	}

	public static HashMap<String, Object> readObject (Map<String, String> _keyValuePairs, Class<?> _targetClass) {
		return connector.readObject(_keyValuePairs, _targetClass);
	}

	public static boolean updateObject (ModelObject _targetObject) {
		return connector.updateObject(_keyValuePairs, _targetObject.getUuid(), _table);
	}

	public static boolean deleteObject (ModelObject _targetObject) {
		return connector.deleteObject(_targetObject.getUuid(), _table);
	}
}