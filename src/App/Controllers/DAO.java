package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DAO is a data access object. It creates connections to the db and takes back
 * data
 * 
 * @updated 1/20/20
 * @author Ian Wilhelmsen
 *
 */
public class DAO {

    Connection conn;
    String sql;

    DAO() {
	this.connectDB();
    }

    /**
     * Creates a persistent connection that can be used over and over again.
     */
    void connectDB() {
	try {
	    //set up the connection strings
	    String dbURL = "jdbc:sqlserver://localhost\\sqlexpress";
	    String user = "sa";
	    String pass = "secret";
	    
	    //try to start a connection
	    this.conn = DriverManager.getConnection(dbURL, user, pass);
	} catch (SQLException ex) {
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
    
    
    /**
     * Stores the sql statement. This should be an execute statement and not a sql query.
     * @param _sqlStatement
     */
    void setSQL(String _sqlStatement) {
	this.sql = _sqlStatement;
    }
}
