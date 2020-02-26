package models;
/*
 * This class represents the User Object
 * @author Ashim Chalise, Ian Wilhelmsen last updated - 02/19/2020
 * 
 * NOTES:
 * 2/19/2020 IMW
 * -> Updated some formating and added method comments.
 */

public class User {

    private int ID;
    private String username;
    private String firstName;
    private String lastName;

    public User(int _ID, String _username, String _firstName, String _lastName) {
	this.ID = _ID;
	this.username = _username;
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
	return this.ID;
    }

    public String getUsername() {
	return this.username;
    }

    public String getFirstName() {
	return this.firstName;
    }

    public String getLastName() {
	return this.lastName;
    }

    // ====================SETTERS====================

    public void setID(int _ID) {
	this.ID = _ID;
    }

    public void setUsername(String _username) {
	this.username = _username;
    }

    public void setFirstName(String _firstName) {
	this.firstName = _firstName;
    }

    public void setLastName(String _lastName) {
	this.lastName = _lastName;
    }
}
