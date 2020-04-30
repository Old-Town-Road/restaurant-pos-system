package models;

/**
 * This class adds some universal support to the Model classes.
 * 
 * @author Ian Wilhelmsen
 * Last Updated: 4/27/2020
 */
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import database.DataStoreAdapter;
import database.DatabaseConstants;
import java.util.ArrayList;
import java.util.Collection;

public abstract class ModelObject {

	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_ID_VALUE)
	protected int id;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_UUID_VALUE)
	protected String uuid;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_IS_ACTIVE_VALUE)
	protected boolean active = true;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_SORT_VALUE_VALUE)
	protected int sortValue;

	public ModelObject() {
		this.setUuid(ModelObject.generateUuid());
		this.setId(DatabaseConstants.DEFAULT_ID_VALUE);
		this.setSortValue(DatabaseConstants.DEFAULT_SORT_VALUE);
	}

	/**
	 * This method returns a HashMap of key/value pairs for updating the model in
	 * the database.
	 * 
	 * @return LinkedHashMap of db column names and string values.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public LinkedHashMap<String, String> getDataKeyValuePairs()
			throws IllegalArgumentException, IllegalAccessException {
		// Initialize a return value.
		LinkedHashMap<String, String> retVal = new LinkedHashMap<String, String>();
		// Starting the current class spin through all levels of this class
		Class<?> targetClass = this.getClass();
		while (!targetClass.getName().equals(DatabaseConstants.TARGET_SUPER_CLASS)) {
			// Spin through all the fields.
			Field[] fields = targetClass.getDeclaredFields();
			for (Field targetField : fields) {
				// If this field is a private or protected field, continue.
				targetField.setAccessible(true);
				int fieldModifier = targetField.getModifiers();
				if ((Modifier.isPrivate(fieldModifier) || Modifier.isProtected(fieldModifier))
						&& (!Collection.class.isAssignableFrom(targetField.getType()))) {
					// Capture the field name based off the annotation.
					String dbFieldName = this.findValueFromFieldColumnDBAnnotation(targetField);
					// Capture the value from the field.
					String fieldValue = null;
					if (targetField.getType() == boolean.class) {
						fieldValue = (targetField.get(this).equals(true) ? "1" : "0");
					} else {
						fieldValue = targetField.get(this).toString();
					}
					// Stow the values in the return value
					retVal.put(dbFieldName, fieldValue);
				}
			}
			// Traverse up to the parent class.
			targetClass = targetClass.getSuperclass();
		}
		// Return the final value to the caller.
		return retVal;
	}

	/**
	 * This method returns an int array that contains the return types of this method.
	 * @return
	 */
	public int[] getReturnTypeSet() {
		ArrayList<Integer> retVal = new ArrayList<Integer>();
		// Starting the current class spin through all levels of this class
		Class<?> targetClass = this.getClass();
		while (!targetClass.getName().equals(DatabaseConstants.TARGET_SUPER_CLASS)) {
			// Spin through all the fields.
			Field[] fields = targetClass.getDeclaredFields();
			for (Field targetField : fields) {
				// If this field is a private or protected field, continue.
				targetField.setAccessible(true);
				int fieldModifier = targetField.getModifiers();
				if ((Modifier.isPrivate(fieldModifier) || Modifier.isProtected(fieldModifier))
						&& (!Collection.class.isAssignableFrom(targetField.getType()))) {
					retVal.add(this.convertTypeToSQLType(targetField.getType()));
				}
			}
		}
		return retVal.stream().mapToInt(i->i).toArray();
	}

	/**
	 * Converts a type to an int that corresponds to the sql type.
	 * @param _type
	 * @return
	 */
	public int convertTypeToSQLType(Class<?> _type) {
		if (_type == int.class) {
			return java.sql.Types.INTEGER;
		} else if (_type == long.class) {
			return java.sql.Types.BIGINT;
		} else if (_type == double.class) {
			return java.sql.Types.DOUBLE;
		} else if (_type == float.class) {
			return java.sql.Types.FLOAT;
		} else if (_type == boolean.class) {
			return java.sql.Types.BOOLEAN;
		} else if (_type == byte.class) {
			return java.sql.Types.BIT;
		} else if (_type == char.class) {
			return java.sql.Types.CHAR;
		} else if (_type == short.class) {
			return java.sql.Types.SMALLINT;
		} else {
			return java.sql.Types.VARCHAR;
		}
	}
	public int[] getSingleIntegerReutrnTypeSet() {
		int[] retVal = {java.sql.Types.INTEGER};
		return retVal;
	}
	/**
	 * This method returns the string casted value of a targeted field found by
	 * reflection.
	 * 
	 * @param _targetField
	 * @return
	 */
	public String findValueFromFieldColumnDBAnnotation(Field _targetField) {
		// Initialize a return value.
		String retVal = null;
		// Grab the annotations from this field.
		Annotation[] fieldAnnotations = _targetField.getAnnotations();
		// Spin through the annotations until finding the column name.
		for (Annotation annotation : fieldAnnotations) {
			// If this is the table name key, grab the value.
			if (annotation instanceof ModelAnnotations) {
				ModelAnnotations myAnnotation = ((ModelAnnotations) annotation);
				if (myAnnotation.key().equals(DatabaseConstants.DB_COLUMN_NAME_KEY)) {
					retVal = myAnnotation.value();
					break;
				}
			}
		}
		// Return the value to the caller.
		return retVal;
	}

	/*
	 * public ModelObject loadById(int _id) { HashMap<String, String> keyValuePair =
	 * new HashMap<>(); keyValuePair.put(DatabaseConstants.DB_ID_VALUE,
	 * Integer.toString(this.getId())); return (ModelObject)
	 * this.returnOnlyValueFromSingleResult(this.loadByCondition(keyValuePair)); }
	 * 
	 * public ModelObject loadByUuid(String _uuid) { HashMap<String, String>
	 * keyValuePair = new HashMap<>();
	 * keyValuePair.put(DatabaseConstants.DB_UUID_VALUE, this.getUuid()); return
	 * (ModelObject)
	 * this.returnOnlyValueFromSingleResult(this.loadByCondition(keyValuePair)); }
	 */

	/**
	 * This is a load by condition of a singular key value pair.
	 * 
	 * @param _name
	 * @param _value
	 * @return
	 */
	public ArrayList<ModelObject> loadByCondition(String _name, String _value) {
		LinkedHashMap<String, String> keyValuePair = new LinkedHashMap<String, String>();
		keyValuePair.put(_name, _value);
		return this.loadByCondition(keyValuePair);
	}

	/**
	 * This method is used to load the rest of the object based on a map of key
	 * value pairs.
	 * 
	 * @param _data
	 * @return
	 */
	public ArrayList<ModelObject> loadByCondition(LinkedHashMap<String, String> _data) {
		ArrayList<ModelObject> retVal = new ArrayList<ModelObject>();
		try {
			// This method fills this object with data from the database.
			retVal = DataStoreAdapter.readObject(_data, this);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(ModelObject.class.getName()).log(Level.SEVERE, null, ex);
		}
		return retVal;
	}

	/**
	 * This is a broiler plate method that will update or create an object in the
	 * database depending on the value of the ID.
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public boolean saveObjectInDatabase() throws IllegalArgumentException, IllegalAccessException {
		// Initialize a return value for the caller defaulted to false.
		boolean retVal = false;
		// Has this object already been created?
		if (this.id == DatabaseConstants.DEFAULT_ID_VALUE) {
			this.setId(DataStoreAdapter.createObject(this));
			retVal = true;
		} else {
			retVal = DataStoreAdapter.updateObject(this);
		}
		// Return the final value to the caller.
		return retVal;
	}

	/**
	 * This method makes a row in the database corresponding to this object
	 * inactive.
	 * 
	 * @return
	 */
	public boolean deleteObjectFromDatabase() {
		return DataStoreAdapter.deleteObject(this);
	}

	/**
	 * This returns the value from the annotation key value pair for class
	 * parameters.
	 * 
	 * @param _key
	 * @return
	 */
	public String findValueFromAnnotationKey(String _key) {
		// Initialize a return value for the caller.
		String retVal = null;
		// Grab the annotations from the class.
		Annotation[] classAnnotations = this.getClass().getAnnotations();
		// Find the table name by the key.
		for (Annotation annotation : classAnnotations) {
			// If this is the table name key, grab the value.
			if (annotation instanceof ModelAnnotations) {
				ModelAnnotations myAnnotation = ((ModelAnnotations) annotation);
				if (myAnnotation.key().equals(_key)) {
					retVal = myAnnotation.value();
					break;
				}
			}
		}
		// Return the final value to the caller
		return retVal;
	}

	/**
	 * Makes the active field true.
	 */
	public void makeActive() {
		this.active = true;
	}

	/**
	 * Makes the active field false.
	 */
	public void makeInactive() {
		this.active = false;
	}

	/**
	 * This returns a single object from a hash of strings and objects
	 * 
	 * @param _inputHash
	 * @return
	 * @deprecated
	 */
	public Object returnOnlyValueFromSingleResult(LinkedHashMap<String, Object> _inputHash) {
		// Initialize a return value for the caller.
		Object retVal = null;
		// Grab the only key from the hash.
		for (String key : _inputHash.keySet()) {
			// Set the return to the only value in the hash.
			retVal = _inputHash.get(key);
		}
		// Return the final value to the caller.
		return retVal;
	}

	protected static String generateUuid() {
		return UUID.randomUUID().toString();
	}

// ================================ GETTERS ====================================

	public String getUuid() {
		return this.uuid;
	}

	public int getId() {
		return this.id;
	}

	public int getSortValue() {
		return this.sortValue;
	}

	public String getClassName() {
		return this.getClass().getName();
	}

	public String getTableName() {
		return this.findValueFromAnnotationKey(DatabaseConstants.TABLE_NAME_ANNOTATION);
	}
// ================================ SETTERS ====================================

	public void setUuid(String _uuid) {
		this.uuid = _uuid;
	}

	public void setId(int _id) {
		this.id = _id;
	}

	public void setSortValue(int _sortValue) {
		this.sortValue = _sortValue;
	}

	public void setIsActive(boolean _isActive) {
		if (_isActive) {
			this.makeActive();
		} else {
			this.makeInactive();
		}
	}
}
