package database;

/**
 * This class handles the connection to the MySQL DB.
 * Implements the DB ConnectorInterface.
 * Please do not use this class to access the DB.
 * 
 * @author Ian Wilhelmsen, last updated: 3/9/2020
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;

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
	private final String createStoredProcedurePrefix = "create";
	private final String readStoredProcedurePrefix = "read";
	private final String updateStoredProcedurePrefix = "update";
	private final String deleteStoredProcedurePrefix = "delete";
	private final String uuidColumnString = "UUID";

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
	 * @param _table         the name of the table
	 * @return int the ID of the created object
	 */
	public int createObject(Map<String, String> _keyValuePairs, String _table) {
		// init a response to 0
		int retVal = 0;

		// stubbed method value for debug mode
		if (debugMode) {
			retVal = 42;
		} else {
			// prepare the call in the a try catch
			// REMEMBER: we are going to include one more parameter for the return ID
			int numberOfParameters = _keyValuePairs.size() + this.singleStoredProcedureReturn;

			try {
				// construct the base call
				this.sql = this.conn.prepareCall(
						this.makeSQLPreparedCallString(this.createStoredProcedurePrefix + _table, numberOfParameters));

				// update the call with the parameters.
				this.assembleCallableStatement(_keyValuePairs);

				// add the return parameter. REMEMBER: This SQL call starts the count at 1
				this.sql.registerOutParameter(numberOfParameters, java.sql.Types.INTEGER);

				// execute
				this.sql.executeQuery();

				// if this an int then prepare to return it
				if ((!this.sql.getString(numberOfParameters).isEmpty())
						&& Integer.parseInt(this.sql.getString(numberOfParameters)) > this.minIDReturnVal) {
					retVal = Integer.parseInt(this.sql.getString(numberOfParameters));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block.
				e.printStackTrace();
			}
		}

		// return the value to the caller
		return retVal;
	}

	@Override
	/**
	 * This function is a basic get for an object from the database
	 */
	public HashMap<String, Object> readObject(Map<String, String> _keyValuePairs, String _table) {
		// init a return value
		HashMap<String, Object> retVal = new HashMap<String, Object>();

		// if we are not in debug mode
		if (!debugMode) {
			// prepare the call in a try catch block
			// the number of parameters is the size of the hashmap
			int numberOfParameters = _keyValuePairs.size();

			try {
				// construct the base call
				this.sql = this.conn.prepareCall(
						this.makeSQLPreparedCallString(this.readStoredProcedurePrefix + _table, numberOfParameters));

				// update the call with the parameters with the call
				this.assembleCallableStatement(_keyValuePairs);

				// TODO investigate ResultSets
				// result set here?

				// execute
				this.sql.executeQuery();

				// validate the return
				// TODO check the result set
				if (true) {
					// pass back the result set
					// TODO consume the result set into the return value
					// retVal =
				}
			} catch (SQLException e) {
				// TODO find a common solution for this
				e.printStackTrace();
			}
		}

		// return the value to the caller
		return retVal;
	}

	@Override
	/**
	 * This function is an update function for an object in the database
	 */
	public Boolean updateObject(Map<String, String> _keyValuePairs, String _uuid, String _table) {
		// init a return value. Default FALSE
		boolean retVal = false;

		// if debug is on then bypass all of this
		if (!debugMode) {
			// prepare the call in a try catch block
			// the number of parameters is the size of the hashmap plus one for the boolean
			// return
			int numberOfParameters = _keyValuePairs.size() + this.singleStoredProcedureReturn;

			try {
				// construct the base call
				this.sql = this.conn.prepareCall(
						this.makeSQLPreparedCallString(this.updateStoredProcedurePrefix + _table, numberOfParameters));

				// update the call with the parameters
				this.assembleCallableStatement(_keyValuePairs);

				// register the return value
				// add the return parameter. REMEMBER: This SQL call starts the count at 1
				this.sql.registerOutParameter(numberOfParameters, java.sql.Types.INTEGER);

				// execute
				this.sql.executeQuery();

				// if this is the correct return value then return it as a boolean
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

		// return the value to caller
		return retVal;
	}

	@Override
	public Boolean deleteObject(String _uuid, String _table) {
		// init a return value. default to FALSE
		boolean retVal = false;

		// if debug is on then by pass all of this
		if (!debugMode) {
			// we are going to homogenize the inputs
			// create a Hashmap of 1 key value pair
			HashMap<String, String> keyValuePair = new HashMap<String, String>();
			keyValuePair.put(this.uuidColumnString, _uuid);

			// grab the size of the hashmap as the number of parameters
			int numberOfParameters = keyValuePair.size();

			// prepare the call in a try catch block
			try {
				// construct the base call
				this.sql = this.conn.prepareCall(
						this.makeSQLPreparedCallString(this.deleteStoredProcedurePrefix + _table, numberOfParameters));

				// update the call with the parameters
				this.assembleCallableStatement(keyValuePair);

				// register the return value
				// add the return parameter. REMEMBER: This SQL call starts the count at 1
				this.sql.registerOutParameter(numberOfParameters, java.sql.Types.INTEGER);

				// execute
				this.sql.executeQuery();

				// if this is the correct return value then return it as a boolean
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

		// return the value to the caller
		return retVal;
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

	private void connect() {
		try {
			this.conn = DriverManager.getConnection(this.dbmsDriverInfo + this.hostString + this.dbName, this.userName,
					this.password);
		} catch (SQLException ex) {
			// TODO goto to logging or create an error report system
		}
	}
}
