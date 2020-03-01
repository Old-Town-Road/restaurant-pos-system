package database;

/**
 * DAO is a data access object. It creates connections to the db and takes back
 * data
 * 
 * @author Ian Wilhelmsen Last Updated 2/19/20
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    Connection conn;
    String sql;

    DAO() {
	this.connectDB();
    }

    /**
     * Creates a persistent connection that can be used over and over again.
     */
    protected void connectDB() {
	try {
	    // set up the connection strings
	    // these are temporary place holders
	    String dbURL = "jdbc:sqlserver://localhost\\sqlexpress";
	    String user = "sa";
	    String pass = "secret";

	    // try to start a connection
	    this.conn = DriverManager.getConnection(dbURL, user, pass);
	} catch (SQLException ex) {
	    // print out the stack
	    // TODO in the future this should get written to a error log
	    ex.printStackTrace();
	}
    }

    /**
     * Overrides the finalize to close the persistent connection
     */
    protected void finalize() {
	try {
	    if (this.conn != null && !this.conn.isClosed()) {
		this.conn.close();
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
    }

//--------------Setters---------------------------------
    /**
     * Stores the sql statement. This should be an execute statement and not a sql
     * query.
     * 
     * @param _sqlStatement
     */
    void setSQL(String _sqlStatement) {
	this.sql = _sqlStatement;
    }

}