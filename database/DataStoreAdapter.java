package database;

/**
 * This class fetches and stores information for the models in the database.
 * @author Ian Wilhelmsen
 * last update: 4/27/2020
 */

import java.util.LinkedHashMap;
import java.util.ArrayList;
import models.ModelObject;

public class DataStoreAdapter {
	private static final DBConnectorInterface connector = new MySQLDBConnectorImpl();

	/**
	 * This saves the parameters of the target object into the database as a new row.
	 * @param _targetObject
	 * @return boolean the success value of the method.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static int createObject (ModelObject _targetObject) throws IllegalArgumentException, IllegalAccessException {
		return connector.createObject(_targetObject.getDataKeyValuePairs(), _targetObject.getTableName());
	}

	/**
	 * This method pulls down an array list of objects specified by the map provided. See StandardDatabaseReads for common use.
	 * @param _keyValuePairs
	 * @param _targetClass
	 * @return Array list filled with the results
	 */
	public static ArrayList<ModelObject> readObject (LinkedHashMap<String, String> _keyValuePairs, Class<?> _targetClass) {
		return connector.readObject(_keyValuePairs, _targetClass);
	}

	/**
	 * This updates the row of the object in the database.
	 * @param _targetObject
	 * @return boolean the success value of the method.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static boolean updateObject (ModelObject _targetObject) throws IllegalArgumentException, IllegalAccessException {
		return connector.updateObject(_targetObject.getDataKeyValuePairs(), _targetObject.getUuid(), _targetObject.getTableName());
	}

	/**
	 * This method makes the row inactive in the database.
	 * @param _targetObject
	 * @return boolean the success value of the method.
	 */
	public static boolean deleteObject (ModelObject _targetObject) {
		//Create a linked hashmap of the uuid and its value.
		LinkedHashMap<String, String> keyValuePair = new LinkedHashMap<String, String>();
		keyValuePair.put(DatabaseConstants.DB_ID_VALUE, String.valueOf(_targetObject.getId()));
		keyValuePair.put(DatabaseConstants.DB_UUID_VALUE, _targetObject.getUuid());
		keyValuePair.put(DatabaseConstants.DB_IS_ACTIVE_VALUE, DatabaseConstants.booleanToBit(false));
		return connector.deleteObject(keyValuePair, _targetObject.getTableName());
	}
}