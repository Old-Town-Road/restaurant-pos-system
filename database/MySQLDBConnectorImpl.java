package database;
/**
 * This class handles the connection to the MySQL DB.
 * Implements the DB ConnectorInterface.
 * Please do not use this class to access the DB.
 * 
 * @author Ian Wilhelmsen, last updated: 2/27/2020
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class MySQLDBConnectorImpl implements DBConnectorInterface {

	//java sql utilities
	private Connection conn;
	private CallableStatement sql;

	//connection information
	private final String dbmsDriverInfo = "jdbc:mysql:";
	private final String hostString = "//localhost:3306/";
	private final String dbName = "";
	private final String userName = "";
	private final String password = "";

	//SQL CallableStatements Strings for SQL statement creation
	private final String startOfSQLStatement ="{call ";
	private final String dbNameDefiner = dbName + ".";
	private final String dbParameterStart = "(";
	private final String sqlParameterPlaceholder = "?,";
	private final String endSQLStatement = ")}";

	MySQLDBConnectorImpl (){
		//start the connection
		this.connect();
	}

	@Override
	/**
	 * This method is the insert call for an object that has a direct match to the db
	 * @param _keyValuePairs the list of parameters from the object
	 * @param _table the name of the table
	 * @return int the ID of the created object
	 */
	public int createObject(Map<String, String> _keyValuePairs, String _table) {
		//init a response to 0
		int retVal = 0;

		//prepare the call in the a try catch
		//REMEMBER: we are going to include one more parameter for the return ID
		int numberOfParameters = _keyValuePairs.size() + 1;

		try {
			//construct the base call
			this.sql = this.conn.prepareCall(this.makeSQLPreparedCallString("insert" + _table, numberOfParameters));

			//update the call with the parameters.
			this.assembleCallableStatement(_keyValuePairs);

			//add the return parameter. REMEMBER: This SQL call starts the count at 1
			this.sql.registerOutParameter(numberOfParameters, java.sql.Types.INTEGER);

			//execute
			this.sql.executeQuery();
			
			//if this an int then prepare to return it
			if((!this.sql.getString(numberOfParameters).isEmpty()) && Integer.parseInt(this.sql.getString(numberOfParameters)) > 0) {
				retVal = Integer.parseInt(this.sql.getString(numberOfParameters));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block.
			e.printStackTrace();
		}

		//return the value to the caller
		return retVal;
	}

	@Override
	public HashMap<String, Object> readObject(Map<String, String> _keyValuePairs, String _table) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateObject(Map<String, String> _keyValuePairs, String _uuid, String _table) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteObject(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method assembles the callable statement from the key value pairs
	 * @param _keyValuePairs The key value pairs from the attributes of the class
	 */
	private void assembleCallableStatement(Map<String, String> _keyValuePairs) {
		//spin through the map and populate the call
		//REMEMBER: this SQL library call starts iterating at one. Start a counter
		int i = 1;
		for(Entry<String, String> keyValuePair : _keyValuePairs.entrySet()) {
			//add in the parameter in a try catch
			try {
				//Adding in the value for the call
				this.sql.setString(i, keyValuePair.getValue());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//update the counter
			i++;
		}
	}

	/**
	 * This method creates the inital arguement for a callable statement.
	 * The CallableStatement in the java.sql library requires a string encapsulated by braces,
	 * with a keyword 'call', the stored procedure name, a number of comma separated ?'s corresponding
	 * to the number of input parameters.
	 * @param _sqlStoredProcedureName This is the store procedure name
	 * @param _numberOfParameters this is the number of parameters of that stored procedure
	 * @return String the finalized string for the callable procedure.
	 */
	private String makeSQLPreparedCallString(String _sqlStoredProcedureName, int _numberOfParameters) {
		//initiate a return value as a string builder
		StringBuilder retVal = new StringBuilder();

		//start of the statement and add the stored procedure name
		retVal.append(this.startOfSQLStatement);
		retVal.append(this.dbNameDefiner);
		retVal.append(_sqlStoredProcedureName);
		retVal.append(this.dbParameterStart);

		//loop through the number of parameters and add placeholders to return value
		for(int i = 0; i < _numberOfParameters; i++) {
			//add a on a placeholder
			retVal.append(this.sqlParameterPlaceholder);
		}

		//complete the sql callable by removing the last trailing comma and adding the brace
		retVal.replace(retVal.length() - 1, retVal.length() - 1, this.endSQLStatement);

		//return the value to the caller
		return retVal.toString();
	}

	private void connect() {
		try {
            this.conn = DriverManager.getConnection(this.dbmsDriverInfo + this.hostString + this.dbName, this.userName, this.password);
        } catch (SQLException ex) {
            //TODO goto to logging or create an error report system
        }
	}
}
