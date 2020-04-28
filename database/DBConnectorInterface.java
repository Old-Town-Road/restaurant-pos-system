package database;

/**
 * This class is intended to maintain the integrity of the code base for interactions
 * with any db.
 * @author Ian Wilhelmsen
 * Last Updated: 4/27/2020
 */

import java.util.LinkedHashMap;
import models.ModelObject;
import java.util.ArrayList;

public interface DBConnectorInterface {

	public abstract int createObject(LinkedHashMap<String, String> linkedHashMap, String _table);

	public abstract ArrayList<ModelObject> readObject(LinkedHashMap<String, String> _keyValuePairs, Class<?> _class);

	public abstract boolean updateObject(LinkedHashMap<String, String> _keyValuePairs, String _uuid, String _table);

	public abstract boolean deleteObject(LinkedHashMap<String, String> _keyValuePairs, String _table);
}
