package helpers;

/**
 * SQLDatabase is the database helper service class that establishes
 * connection to the SQLite database as well as maintains the CRUD
 * operations necessary for database manipulation.
 * Some features of this class are:
 * <ul>
 * <li>Create a database if non existent.
 * <li>Create user table if non existent.
 * <li>Create Transaction table if non existent.
 * <li>Establish connection as well as disconnect to the database.
 * <li>Insert new users to the users table.
 * <li>Insert new transactions to the transactions table.
 * </ul>
 *
 * @author    Raiana
 */
import java.sql.*;
import java.sql.Connection; //raiana
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import Models.User;

import java.sql.DriverManager;

public class DatabaseHelper {
	private static final String dbName = "userinfo";
	Connection connection;
	Statement stmt = null;
	Timestamp date;

  public  Connection getConnection(){

        String userName="root";
        String password="12345678";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            	connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
            //	connection.close();

        } catch (Exception e) {
            e.printStackTrace();
          System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
       createUsersTable();
        return connection;
       
    }


	/**
	 * Creates the "Users" table in the database
	 *
	 */
	public void createUsersTable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName);
			stmt = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Users"
					+ "(Id 			INTEGER		 PRIMARY KEY		AUTOINCREMENT,"
					+ " Firstname 	TEXT					NOT NULL,"
					+ " Lastname		TEXT					NOT NULL,"
					+ " Username		TEXT					NOT NULL,"
					+ " Password		TEXT					NOT NULL,"
					+ " Email		TEXT					NOT NULL" + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("User table creation successful");
	}

	
	/**
	 * Adds user to the Users table.
	 *
	 * @param user
	 *            a user object containing the user information
	 * @see UserModel
	 */
	public void addUser(User user) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			String query = "INSERT INTO Users(Firstname, Lastname, Username, Password, TotalAmount, StockAmount, Email) "
					+ "SELECT ?, ?, ?, ?, ?, ?, ?" + "WHERE NOT EXISTS (SELECT * FROM Users WHERE Username = ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
//			statement.setDouble(5, user.getTotalAmount());
//			statement.setDouble(6, user.getStockAmount());
			statement.setString(7, user.getEmail());
//			statement.setString(8, user.getUserName());
			statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("User added successfully");
	}

	/**
	 * Updates user in the Users table.
	 *
	 * @param userName
	 *            user name of the user
	 * @param firstName
	 *            first name of the user
	 * @param lastName
	 *            last name of the user
	 * @param email
	 *            email of the user
	 * @param password
	 *            password of the user
	 */
	public void updateUser(String userName, String firstName, String lastName, String password, String email) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			String query = "UPDATE Users SET Firstname = ?, Lastname = ?, Password = ?, Email = ? WHERE Username = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, password);
			statement.setString(4, email);
			statement.setString(5, userName);
			statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("User updated successfully");
	}

	/**
	 * Adds transaction to the StockTransactions table.
	 *
	 * @param price
	 *            the price of the stock bought
	 * @param date
	 *            the time of transaction
	 * @param userId
	 *            the id of the user making the transaction
	 * @param numStocks
	 *            the number of stocks bought
	 */
	public void addTransactionToDb(String stock, double price, int userId, int numStocks) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			String query = "INSERT INTO StockTransactions(Stock, Price, Time, UserId, NumberOfStocks) "
					+ "VALUES(?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			date = new Timestamp(System.currentTimeMillis());
			statement.setString(1, stock);
			statement.setDouble(2, price);
			statement.setTimestamp(3, date);
			statement.setInt(4, userId);
			statement.setInt(5, numStocks);
			statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Transaction added successfully");
	}

	/**
	 * Authenticates user trying to log in.
	 *
	 * @param username
	 *            user name of the user
	 * @param password
	 *            password of the user
	 * @return true if the user is authenticated and false if not authenticated
	 */
	public boolean authenticateUser(String username, String password) {
		boolean found = false;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			String query = "SELECT EXISTS(SELECT 1 FROM Users WHERE Username = ? AND Password = ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				found = rs.getBoolean(1);
			}
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("User authenticated successful");
		return found;
	}

	/**
	 * Checks to see if the username already exists in the users table.
	 *
	 * @param username
	 *            user name of the user
	 * @return true if the username is found and returns false if not found
	 */
	public boolean checkExistingUsername(String username) {
		boolean found = false;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			String query = "SELECT EXISTS(SELECT 1 FROM Users WHERE Username = ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				found = rs.getBoolean(1);
			}
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Existing username checking successful");
		return found;
	}

	/**
	 * Gets the userId of a user.
	 *
	 * @param username
	 *            user name of the user
	 * @return the userId of the user
	 */
	public int getUserId(String username) {
		int userId = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			String query = "SELECT Id FROM Users WHERE Username = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				userId = rs.getInt(1);
			}
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Get UserId successful");
		return userId;
	}

	/**
	 * Get information on a user and parse into a UserModel object
	 *
	 * @param userId
	 *            userId of the user
	 * @return a UserModel object associated with the userId
	 * @see UserModel
	 */
	public User getUserInfo(int userId) {
		User user = new User(userId, null, null, null);
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			String query = "SELECT * FROM Users WHERE Id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user.setID(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setUsername(rs.getString(4));
//				user.setTotalAmount(rs.getDouble(6));
//				user.setStockAmount(rs.getDouble(7));
				user.setEmail(rs.getString(8));
			}
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("GetUserInfo successful");
		return user;
	}

	public void addBankTransactionToDb(int userId, double amount) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			double totalAmount = 0.0;
			String query = "SELECT TotalAmount FROM Users WHERE Id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				totalAmount = rs.getDouble(1);
			}

			double newAmount = totalAmount + amount;
			if (newAmount >= 0) {
				date = new Timestamp(System.currentTimeMillis());
				query = "INSERT INTO BankTransactions(UserId, Amount, Time) " + "VALUES(?, ?, ?);";
				statement = connection.prepareStatement(query);
				statement.setInt(1, userId);
				statement.setDouble(2, amount);
				statement.setTimestamp(3, date);
				statement.executeUpdate();
				query = "UPDATE Users SET TotalAmount = ? WHERE Id = ?;";
				statement = connection.prepareStatement(query);
				statement.setDouble(1, newAmount);
				statement.setInt(2, userId);
				statement.executeUpdate();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Error:");
				alert.setContentText("Insufficient funds.\nPlease try again.");
				alert.showAndWait();
			}
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Transaction added successfully");
	}

}
