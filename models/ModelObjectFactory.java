package models;

import database.DataStoreAdapter;
import database.DatabaseConstants;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ModelObjectFactory {

	public HashMap<String, String> getDataKeyValuePairs() throws IllegalArgumentException, IllegalAccessException {
		// Initialize a return value.
		HashMap<String, String> retVal = new HashMap<>();
		// Starting the current class spin through all levels of this class
		Class<?> targetClass = this.getClass();
		while (!targetClass.getName().equals(DatabaseConstants.TARGET_SUPER_CLASS)) {
			// Spin through all the fields.
			Field[] fields = targetClass.getDeclaredFields();
			for (Field targetField : fields) {
				// If this field is a private or protected field, continue.
				int fieldModifier = targetField.getModifiers();
				if (Modifier.isPrivate(fieldModifier) || Modifier.isProtected(fieldModifier)) {
					// Capture the field name based off the annotation.
					String dbFieldName = this.findValueFromFieldColumnDBAnnotation(targetField);
					// Capture the value from the field.
					String fieldValue = null;
					if (targetField.getType() == boolean.class) {
						fieldValue = (targetField.get(this).equals(true) ? "1" : "0");
						// }elseif() {

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

	public String findValueFromFieldColumnDBAnnotation(Field _targetField) {
		// Initialize a return value.
		String retVal = null;
		// Grab the annotations from this field.
		Annotation[] fieldAnnotations = _targetField.getAnnotations();
		// Spin through the annotations until finding the column name.
		for (Annotation annotation : fieldAnnotations) {
			if (annotation instanceof ModelAnnotations
					&& ((ModelAnnotations) annotation).key() == DatabaseConstants.DB_COLUMN_NAME_KEY) {
				retVal = ((ModelAnnotations) annotation).value();
				break;
			}
		}
		// Return the value to the caller.
		return retVal;
	}

	/**
	 * This game factory method generates blank objects based on the class type
	 * given.
	 * 
	 * @param _class
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object objectFactory(String _class)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class thisClass = Class.forName(_class);
		Object thisObject = thisClass.newInstance();
		thisClass.cast(thisObject);
		return thisObject;
	}

	/**
	 * This game factory overloaded method generates new blank objects of the given
	 * type, and then populates this object with the given name-value pairs loaded
	 * from the database.
	 * 
	 * @param _class
	 * @param _data
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object objectFactory(String _class, HashMap<String, Object> _data)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			IllegalArgumentException, InvocationTargetException {
		// Generate a Class object based on the class name (ie. actors.Zombies);
		Class thisClass = Class.forName(_class);
		// Create an empty instance of this class.
		Object thisObject = thisClass.newInstance();
		// Create an array of class objects to represent the parameters for the
		// setProperties method.
		Class[] params = new Class[1];
		params[0] = HashMap.class;
		// Create a method object based on the method name and list of parameters.
		Method thisMethod = thisClass.getMethod("setProperties", params);
		// Invoke the setProperties method.
		thisMethod.invoke(thisObject, _data);
		// Return the generic object.
		return thisObject;
	}

	public String getDataTable() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Class<?> c = this.getClass();
		Field f = c.getDeclaredField("dataTable");
		return f.get(this).toString();
	}

	/**
	 * Returns the class name of this object.
	 * 
	 * @return
	 */
	public String getClassName() {
		return this.getClass().getName();
	}

	/**
	 * Generic method for loading an object's data by any given condition.
	 * 
	 * @param _data
	 * @return
	 */
	public HashMap<String, Object> loadByCondition(HashMap<String, String> _data) {
		HashMap<String, Object> retVal = null;
		try {
			retVal = DataStoreAdapter.readObject(_data, this.getClass());
		} catch (IllegalArgumentException ex) {
			;
		}
		return retVal;

	}
}
