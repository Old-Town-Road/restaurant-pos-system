package Models;
/*
 * This class represents the User Object
 * @author Ashim Chalise, Ian Wilhelmsen last updated - 02/19/2020
 * 
 * NOTES:
 * 2/19/2020 IMW
 * -> Updated some formating and added method comments.
 */

import java.util.LinkedList;

//import models.StockModel;

public class User {

    private int userID;
    private String userName;
    private String firstName;
    private String lastName;
    
    // Raiana
    private String password; //Hash password later
    private String fullName;
	private String email;
	
	
//	private double totalAmount;
//	private double stockAmount;
//	private LinkedList<StockModel> stockPortfolio = new LinkedList<StockModel>();
//	

    public User(int _ID, String _username, String _firstName, String _lastName) {
	this.userID = _ID;
	this.userName = _username;
	this.firstName = _firstName;
	this.lastName = _lastName;
    }

    /**
     * This is to fill the parameters of this instance from the database.
     * @param _ID int the userID to load
     * @return boolean returns whether the method was successful or not
     */
    public boolean loadUser(int _ID) {
	boolean retVal = false;

	// @ todo attach controller for login.
	return retVal;
    }

    // ====================GETTERS====================

    public int getID() {
	return this.userID;
    }
    
    /**
	 * Getter for user name
	 */

    public String getUsername() {
	return this.userName;
    }

    public String getFirstName() {
	return this.firstName;
    }

    public String getLastName() {
	return this.lastName;
    }
    public String getPassword(){
		return password;
	}
	
    /**
	 * Getter for user email
	 */
	public String getEmail(){
		return email;
	}
	

    // ====================SETTERS====================

    public void setID(int _ID) {
	this.userID = _ID;
    }

    public void setUsername(String _username) {
	this.userName = _username;
    }

    public void setFirstName(String _firstName) {
	this.firstName = _firstName;
    }
    
	/**
	 * Setter for password
	 */
	public void setPassword(String password){
		this.password = password;
	}

    public void setLastName(String _lastName) {
	this.lastName = _lastName;
    }
    
    /**
	 * Setter for user email
	 */
	public void setEmail(String email){
		this.email = email;
	}
}
