package models;

/**
 * This class adds some universal support to the Model classes.
 * 
 * @author Ian Wilhelmsen last updated: 3/20/2020
 */
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import database.DataStoreAdapter;
import database.DatabaseConstants;

public abstract class ModelObject {

	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_ID_VALUE)
	protected int id;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_UUID_VALUE)
	protected String uuid;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_IS_ACTIVE_VALUE)
	protected boolean active = true;

	public ModelObject() {
		this.setUuid(ModelObject.generateUuid());
	}

	public ModelObject loadById(int _id) {
		HashMap<String, String> keyValuePair = new HashMap<>();
		keyValuePair.put(DatabaseConstants.DB_ID_VALUE, Integer.toString(this.getId()));
		return (ModelObject) this.returnOnlyValueFromSingleResult(this.loadByCondition(keyValuePair));
	}

	public ModelObject loadByUuid(String _uuid) {
		HashMap<String, String> keyValuePair = new HashMap<>();
		keyValuePair.put(DatabaseConstants.DB_UUID_VALUE, this.getUuid());
		return (ModelObject) this.returnOnlyValueFromSingleResult(this.loadByCondition(keyValuePair));
	}

	public HashMap<String, Object> loadByCondition(String _name, String _value) {
		HashMap<String, String> keyValuePair = new HashMap<>();
		keyValuePair.put(_name, _value);
		return this.loadByCondition(keyValuePair);
	}

	public HashMap<String,String> getDataKeyValuePairs() throws IllegalArgumentException, IllegalAccessException {
		//Initialize a return value.
		HashMap<String, String> retVal = new HashMap<>();
		//Starting the current class spin through all levels of this class
		Class<?> targetClass = this.getClass();
		while (!targetClass.getName().equals(DatabaseConstants.TARGET_SUPER_CLASS)) {
			//Spin through all the fields.
			Field[] fields = targetClass.getDeclaredFields();
			for(Field targetField : fields) {
				//If this field is a private or protected field, continue.
				int fieldModifier = targetField.getModifiers();
				if(Modifier.isPrivate(fieldModifier) || Modifier.isProtected(fieldModifier)) {
					//Capture the field name based off the annotation.
					String dbFieldName = this.findValueFromFieldColumnDBAnnotation(targetField);
					//Capture the value from the field.
					String fieldValue = null;
					if(targetField.getType() == boolean.class) {
						fieldValue = (targetField.get(this).equals(true) ? "1" : "0");
					//}elseif() {
						
					}else {
						fieldValue = targetField.get(this).toString();
					}
					//Stow the values in the return value
					retVal.put(dbFieldName, fieldValue);
				}
			}
			//Traverse up to the parent class.
			targetClass = targetClass.getSuperclass();
		}
		//Return the final value to the caller.
		return retVal;
	}
	public HashMap<String, Object> loadByCondition(HashMap<String, String> _data) {
		try {
			//This method fills this object with data from the database.
			return DataStoreAdapter.readObject(_data, this.getClass());
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(ModelObject.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public boolean saveObjectFromDatabase() throws IllegalArgumentException, IllegalAccessException {
		// Initialize a return value for the caller defaulted to false.
		boolean retVal = false;
		// Has this object already been created?
		if (this.id == 0) {
			this.setId(DataStoreAdapter.createObject(this));
			retVal = true;
		} else {
			retVal = DataStoreAdapter.updateObject(this);
		}
		// Return the final value to the caller.
		return retVal;
	}

	public boolean deleteObjectFromDatabase() {
		return DataStoreAdapter.deleteObject(this);
	}

	public String findValueFromAnnotationKey(String _key) {
		// Initialize a return value for the caller.
		String retVal = null;
		// Grab the annotations from the class.
		Annotation[] classAnnotations = this.getClass().getAnnotations();
		// Find the table name by the key.
		for (Annotation annotation : classAnnotations) {
			// If this is the table name key, grab the value.
			if (annotation instanceof ModelAnnotations && ((ModelAnnotations) annotation).key() == _key) {
				retVal = ((ModelAnnotations) annotation).value();
				break;
			}
		}
		// Return the final value to the caller
		return retVal;
	}

	public String findValueFromFieldColumnDBAnnotation (Field _targetField) {
		//Initialize a return value.
		String retVal = null;
		//Grab the annotations from this field.
		Annotation[] fieldAnnotations = _targetField.getAnnotations();
		//Spin through the annotations until finding the column name.
		for(Annotation annotation : fieldAnnotations) {
			if(annotation instanceof ModelAnnotations && ((ModelAnnotations) annotation).key() 
					== DatabaseConstants.DB_COLUMN_NAME_KEY) {
				retVal = ((ModelAnnotations) annotation).value();
				break;
			}
		}
		//Return the value to the caller.
		return retVal;
	}

	public void makeActive() {
		this.active = true;
	}

	public void makeInactive() {
		this.active = false;
	}

	public Object returnOnlyValueFromSingleResult(HashMap<String, Object> _inputHash) {
		//Initialize a return value for the caller.
		Object retVal = null;
		//Grab the only key from the hash.
		for(String key : _inputHash.keySet()) {
			//Set the return to the only value in the hash.
			retVal = _inputHash.get(key);
		}
		//Return the final value to the caller.
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
}
