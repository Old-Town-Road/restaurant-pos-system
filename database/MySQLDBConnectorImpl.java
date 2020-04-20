package database;

import java.util.ArrayList;
/**
 * This class handles the connection to the MySQL DB.
 * Implements the DB ConnectorInterface.
 * Please do not use this class to access the DB.
 * 
 * @author Ian Wilhelmsen, last updated: 3/19/2020
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import models.ModelAnnotations;
import models.ModelObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class MySQLDBConnectorImpl implements DBConnectorInterface {

	// java sql utilities
	private Connection conn;
	private CallableStatement sql;

	// connection information
	private final String dbmsDriverInfo = "jdbc:mysql:";
	private final String hostString = "//localhost:3306/";
	private final String dbName = "";
	private final String userName = "";
	private final String password = "";

	// NOTE: CLASS DEBUG MODE
	// TODO flip this value for production
	// This will give back stubbed values while true
	private final boolean debugMode = true;

	// SQL CallableStatements Strings for SQL statement creation
	private final String startOfSQLStatement = "{call ";
	private final String dbNameDefiner = dbName + ".";
	private final String dbParameterStart = "(";
	private final String sqlParameterPlaceholder = "?,";
	private final String endSQLStatement = ")}";
	private final String createStoredProcedurePrefix = "create_";
	private final String readStoredProcedurePrefix = "read_";
	private final String updateStoredProcedurePrefix = "update_";
	private final String deleteStoredProcedurePrefix = "delete_";
	private final String descriptionColumnString = "Description";

	// annotation constants
	private final String tableNameAnnotation = DatabaseConstants.TABLE_NAME_ANNOTATION;
	private final String notPrimitiveErrorMessage = "Input was not a primitive";

	// SQL CallableStatements integer Constants
	private final int successValue = 1;
	private final int singleStoredProcedureReturn = 1;
	private final int minIDReturnVal = 0;

	MySQLDBConnectorImpl() {
		// start the connection
		this.connect();
	}

	@Override
	/**
	 * This method is the insert call for an object that has a direct match to the
	 * db
	 * 
	 * @param _keyValuePairs the list of parameters from the object
	 * @param _table         the name of the class needed to store.
	 * @return int the ID of the created object
	 */
	public int createObject(Map<String, String> _keyValuePairs, String _table) {
		// Initialize a response with a default of 0.
		int retVal = 0;
		// This is a stubbed response if the debug mode is on.
		if (this.debugMode) {
			retVal = 42;
		} else {
			// Prepare the call in the a try catch.
			try {
				this.prepCallableStatement(_keyValuePairs, this.createStoredProcedurePrefix, _table);
				// Grab the number of parameters.
				int numberOfParameters = this.findNumberOfParametersWithReturn(_keyValuePairs,
						this.createStoredProcedurePrefix);
				// Execute the prepared call.
				this.sql.executeQuery();
				// If this an integer, then prepare to return it to the caller.
				if ((!this.sql.getString(numberOfParameters).isEmpty())
						&& Integer.parseInt(this.sql.getString(numberOfParameters)) > this.minIDReturnVal) {
					// Set the return value to the ID that was returned from the DB.
					retVal = Integer.parseInt(this.sql.getString(numberOfParameters));
				}
				// If there was an issue, the exception here catches the error.
			} catch (SQLException e) {
				// TODO Auto-generated catch block.
				e.printStackTrace();
			}
		}
		// Return the value to the caller.
		return retVal;
	}

	@Override
	/**
	 * This function is a basic get for an object from the database.
	 * 
	 * @param Map<String, String> _keyValuePairs: The key value pairs of context
	 *                    parameters that narrow down the which specific object from
	 *                    the database we need.
	 * @param Class<?>    _class: The class desired in the return value.
	 * @return HashMap<String, Object> The series of return objects from the stored
	 *         procedure call.
	 */
	public ArrayList<ModelObject> readObject(Map<String, String> _keyValuePairs, Class<?> _class) {
		// Initialize a return value for the caller.
		ArrayList<ModelObject> retVal = new ArrayList<ModelObject>();
		// If we are not in debug mode then proceed.
		if (!debugMode) {
			try {
				// Grab the table name from the class.
				String tableName = this.getTableNameFromClass(_class);
				// Construct the base call.
				this.prepCallableStatement(_keyValuePairs, this.readStoredProcedurePrefix, tableName);
				// Initialize the result set as the result of the sql execution.
				ResultSet results = this.sql.executeQuery();
				// Spin through the result set while there are still values.
				while (results.next()) {
					// Create a new instance of the class.
					ModelObject resultObject = (ModelObject) this.fillOutObject(results, _class);
					retVal.add(resultObject);
				}
			} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
				// TODO find a common solution for this
				e.printStackTrace();
			}
		}
		// return the value to the caller
		return retVal;
	}

	@Override
	/**
	 * This function is an update function for an object in the database.
	 * 
	 * @param _keyValuePairs: This is parameters to use for the stored procedure.
	 * @param _uuid:          The Unique Universal Identification string.
	 * @param _table:         The string representing the table name to check.
	 * 
	 * @return boolean: Returns true if call was successful.
	 */
	public boolean updateObject(Map<String, String> _keyValuePairs, String _uuid, String _table) {
		// Initialize a return value and default FALSE.
		boolean retVal = false;

		// If debug is on then bypass all of this.
		if (!debugMode) {
			// Prepare the call in a try catch block.
			try {
				this.prepCallableStatement(_keyValuePairs, this.updateStoredProcedurePrefix, _table);
				// Determine the number of parameters to double check the result.
				int numberOfParameters = this.findNumberOfParametersWithReturn(_keyValuePairs,
						this.updateStoredProcedurePrefix);
				// Execute the statement.
				this.sql.executeQuery();
				// if this is the correct return value then return it as a boolean.
				if ((!this.sql.getString(numberOfParameters).isEmpty())
						&& Integer.parseInt(this.sql.getString(numberOfParameters)) > this.minIDReturnVal) {
					retVal = (Integer.parseInt(this.sql.getString(numberOfParameters)) == this.successValue ? true
							: false);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block.
				e.printStackTrace();
			}
		}
		// Return the value to caller.
		return retVal;
	}

	@Override
	/**
	 * This method flags a row in the database as inactive.
	 * 
	 * @param _uuid:  The Unique Universal Identification assigned to the object
	 * @param _table: The string representing the table to look for this row.
	 * 
	 * @return boolean: True if operation successful and false otherwise.
	 */
	public boolean deleteObject(Map<String, String> _keyValuePairs, String _table) {
		// Initialize a return value and default to FALSE
		boolean retVal = false;
		// If debug is on then bypass all of this.
		if (!debugMode) {
			// Prepare the call in a try catch block.
			try {
				this.prepCallableStatement(_keyValuePairs, this.deleteStoredProcedurePrefix, _table);
				// Find the number of returnable parameters.
				int numberOfParameters = this.findNumberOfParametersWithReturn(_keyValuePairs,
						this.deleteStoredProcedurePrefix);
				// Execute the query.
				this.sql.executeQuery();
				// If this is the correct return value then return it as a boolean.
				if ((!this.sql.getString(numberOfParameters).isEmpty())
						&& Integer.parseInt(this.sql.getString(numberOfParameters)) > this.minIDReturnVal) {
					retVal = (Integer.parseInt(this.sql.getString(numberOfParameters)) == this.successValue ? true
							: false);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block.
				e.printStackTrace();
			}
		}
		// Return the value to the caller.
		return retVal;
	}

	/**
	 * This is a broiler plate helper method to create the callable statement.
	 * 
	 * @param _keyValuePairs:         The parameters to be passed in to the stored
	 *                                procedure.
	 * @param _storedProcedurePrefix: The string that is the prefix for stored
	 *                                procedure.
	 * @param _tableName:             The string for the table name.
	 */
	private void prepCallableStatement(Map<String, String> _keyValuePairs, String _storedProcedurePrefix,
			String _tableName) {
		// This must be in a try catch block.
		try {
			// Initialize a count of the number of parameters needed for the stored
			// procedure call.
			int numberOfParameters = this.findNumberOfParametersWithReturn(_keyValuePairs, _storedProcedurePrefix);
			// Construct the base call.
			this.sql = this.conn.prepareCall(
					this.makeSQLPreparedCallString(_storedProcedurePrefix + _tableName, numberOfParameters));
			// update the call with the parameters
			this.assembleCallableStatement(_keyValuePairs);
			// If there are parameters to be registered then do so.
			this.registerOutParameters(numberOfParameters, _keyValuePairs.size());
			// Catch any errors that come up.
		} catch (SQLException e) {
			// TODO Auto-generated catch block.
			e.printStackTrace();
		}
	}

	/**
	 * This method assembles the callable statement from the key value pairs
	 * 
	 * @param _keyValuePairs The key value pairs from the attributes of the class
	 */
	private void assembleCallableStatement(Map<String, String> _keyValuePairs) {
		// spin through the map and populate the call
		// REMEMBER: this SQL library call starts iterating at one.
		// Start a counter
		int i = 1;
		// spin through the key value pairs
		for (Entry<String, String> keyValuePair : _keyValuePairs.entrySet()) {
			// add in the parameter in a try catch
			try {
				// Adding in the value for the call
				this.sql.setString(i, keyValuePair.getValue());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// update the counter
			i++;
		}
	}

	/**
	 * This method creates the inital arguement for a callable statement. The
	 * CallableStatement in the java.sql library requires a string encapsulated by
	 * braces, with a keyword 'call', the stored procedure name, a number of comma
	 * separated ?'s corresponding to the number of input parameters.
	 * 
	 * @param _sqlStoredProcedureName This is the store procedure name
	 * @param _numberOfParameters     this is the number of parameters of that
	 *                                stored procedure
	 * @return String the finalized string for the callable procedure.
	 */
	private String makeSQLPreparedCallString(String _sqlStoredProcedureName, int _numberOfParameters) {
		// initiate a return value as a string builder
		StringBuilder retVal = new StringBuilder();
		// start of the statement and add the stored procedure name
		retVal.append(this.startOfSQLStatement);
		retVal.append(this.dbNameDefiner);
		retVal.append(_sqlStoredProcedureName);
		retVal.append(this.dbParameterStart);
		// loop through the number of parameters and add placeholders to return value
		for (int i = 0; i < _numberOfParameters; i++) {
			// add a on a placeholder
			retVal.append(this.sqlParameterPlaceholder);
		}
		// complete the sql callable by removing the last trailing comma and adding the
		// brace
		retVal.replace(retVal.length() - 1, retVal.length() - 1, this.endSQLStatement);
		// return the value to the caller
		return retVal.toString();
	}

	/**
	 * Helper function to add on an additional parameter in the case that we need a
	 * single return from a stored procedure.
	 * 
	 * @param _keyValuePairs:         The arguments specified for the store
	 *                                procedure call.
	 * @param _storedProcedurePrefix: This string distinguishes between create,
	 *                                read, update, delete stored procedures.
	 * @return int: This is the number of parameters needed for the stored procedure
	 *         prepared call.
	 */
	private int findNumberOfParametersWithReturn(Map<String, String> _keyValuePairs, String _storedProcedurePrefix) {
		// Initialize a return value for the caller.
		int retVal = 0;
		// Determine the size of the hashmap as the number of parameters. If this is a
		// create, delete or update statement then we are expect to an back.
		if (_storedProcedurePrefix == this.readStoredProcedurePrefix) {
			// This is an read statement and we only need the number of parameters in the
			// hashmap.
			retVal = _keyValuePairs.size();
		}
		// Otherwise, count an additional parameter for the return.
		else {
			retVal = _keyValuePairs.size() + this.singleStoredProcedureReturn;
		}
		// return the final value to the caller
		return retVal;
	}

	/**
	 * This function automatically registers the outputs with data type.
	 * 
	 * @param _numberOfParameters: The number of parameters expected in the stored
	 *                             procedure call.
	 * @param _keyValuePairs:      The number of parameters being passed.
	 * @param _typesToReturn:      A string array of data types desired for each
	 *                             output parameter IN ORDER.
	 */
	private void registerOutParameters(int _numberOfParameters, int _numberOfKeyValuePairs, int[] _typesToReturn) {
		// This is a sql contruct, surround this with a try catch block
		try {
			// If there is a difference in the length of the parameters needed for the call
			// and the number of parameters requested then register those.
			if (_numberOfParameters != _numberOfKeyValuePairs) {
				// Initialize a number of parametes needed.
				int numberOfParametersNeeded = _numberOfParameters - _numberOfKeyValuePairs;
				// For the number of parameters needed, register that index as an out.
				for (int i = 0; i <= numberOfParametersNeeded; i++) {
					// Register this i in the callable statement.
					// If the array of return data types is empty then assume it is an integer.
					if (_typesToReturn.length == 0) {
						this.sql.registerOutParameter(i + _numberOfKeyValuePairs, java.sql.Types.INTEGER);
					}
					// Else, decode the data type and register it.
					else {
						this.sql.registerOutParameter(i + _numberOfKeyValuePairs, _typesToReturn[i]);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Override method for easier calling. See other method of same name in this
	 * class with an additional argument.
	 */
	private void registerOutParameters(int _numberOfParameters, int _numberOfKeyValuePairs) {
		// Initialize and empty integer array and pass it to the override method.
		int[] emptyArray = null;
		this.registerOutParameters(_numberOfParameters, _numberOfKeyValuePairs, emptyArray);
	}

	/**
	 * This function gets the table name from the class by reflection.
	 * 
	 * @param _class
	 * @return
	 */
	private String getTableNameFromClass(Class<?> _class) {
		// Initialize a return value for the caller.
		String retVal = null;
		// Grab the annotations from the class.
		Annotation[] classAnnotations = _class.getAnnotations();
		// Find the table name by the key.
		for (Annotation annotation : classAnnotations) {
			// If this is the table name key, grab the value.
			if (annotation instanceof ModelAnnotations
					&& ((ModelAnnotations) annotation).key() == this.tableNameAnnotation) {
				retVal = ((ModelAnnotations) annotation).value();
				break;
			}
		}
		// Return the final value to the caller
		return retVal;
	}

	private Object makeModelObject(Class<?> _targetClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object retVal = _targetClass.getConstructor().newInstance();
		return _targetClass.cast(retVal);
	}

	/**
	 * This method fills up the object with results from the db.
	 * 
	 * @param _results:      The ResultSet from the DB.
	 * @param _targetObject: The object to fill up from the ResultSet.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private Object fillOutObject(ResultSet _results, Class<?> _targetClass)
			throws IllegalArgumentException, IllegalAccessException, SQLException, ClassNotFoundException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// Create a return value for the method
		Object retVal = this.makeModelObject(_targetClass);
		// Loop through each field in the target class and
		for (Field field : _targetClass.getDeclaredFields()) {
			// Use this to change the access of the field to public for this instance.
			field.setAccessible(true);
			// Grab the field annotation from the field.
			Annotation fieldAnnotaion = field.getAnnotation(ModelAnnotations.class);
			// There should be only one annotation pair; key:columnName and value:the column
			// name in the db. Get that value from the result set.
			Object value = _results.getObject(((ModelAnnotations) fieldAnnotaion).value());
			// Type cast all of the pieces.
			Class<?> type = field.getType();
			if (this.checkPrimitiveType(type)) {
				Class<?> typeToCast = this.getCastTypeForField(type);
				value = typeToCast.cast(value);
			}
			// Fill the object up with all the values.
			field.set(retVal, value);
		}
		return retVal;
	}

	/**
	 * Method checks the type supplied.
	 * 
	 * @param type: The type to check.
	 * @return boolean: Returns if the type is is a primitive that we need to cast.
	 */
	private boolean checkPrimitiveType(Class<?> type) {
		return (type == int.class || type == long.class || type == double.class || type == float.class
				|| type == boolean.class || type == byte.class || type == char.class || type == short.class);
	}

	/**
	 * This method translates the primitive types for the object casts.
	 * @param type: The input type to translate.
	 * @return Class: The return is the target cast for consumption.
	 */
	private Class<?> getCastTypeForField(Class<?> _type) {
		if (_type == int.class) {
			return Integer.class;
		} else if (_type == long.class) {
			return Long.class;
		} else if (_type == double.class) {
			return Double.class;
		} else if (_type == float.class) {
			return Float.class;
		} else if (_type == boolean.class) {
			return Boolean.class;
		} else if (_type == byte.class) {
			return Byte.class;
		} else if (_type == char.class) {
			return Character.class;
		} else if (_type == short.class) {
			return Short.class;
		} else {
			throw new IllegalArgumentException(this.notPrimitiveErrorMessage);
		}
	}

	private void connect() {
		try {
			this.conn = DriverManager.getConnection(this.dbmsDriverInfo + this.hostString + this.dbName, this.userName,
					this.password);
		} catch (SQLException e) {
			// TODO goto to logging or create an error report system
			e.printStackTrace();
		}
	}
}