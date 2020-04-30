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

	public abstract int createObject(LinkedHashMap<String, String> linkedHashMap, int[] _returnTypeArray, Class<?> _class);

	public abstract ArrayList<ModelObject> readObject(LinkedHashMap<String, String> _keyValuePairs, int[] _returnTypeArray, Class<?> _class);

	public abstract boolean updateObject(LinkedHashMap<String, String> _keyValuePairs, int[] _returnTypeArray, String _uuid, Class<?> _class);

	public abstract boolean deleteObject(LinkedHashMap<String, String> _keyValuePairs, int[] _returnTypeArray, Class<?> _class);
}
