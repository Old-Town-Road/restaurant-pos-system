package models;

/*
 * This class represents the User Object
 * @author Ashim Chalise, Ian Wilhelmsen last updated - 4/23/2020
 */
import database.DatabaseConstants;
@ModelAnnotations(key = DatabaseConstants.TABLE_NAME_ANNOTATION, value = DatabaseConstants.DB_TABLE_USER_VALUE)
public class User extends ModelObject {

	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_USER_USERNAME_VALUE)
	private String username;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_USER_FIRST_NAME_VALUE)
	private String firstName;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_USER_LAST_NAME_VALUE)
	private String lastName;
	@ModelAnnotations(key = DatabaseConstants.DB_COLUMN_NAME_KEY, value = DatabaseConstants.DB_USER_ROLEID_VALUE)
	private int roleId;

	public User(int _id, String _uuid, int _sortValue, boolean _isActive, String _username, String _firstName, String _lastName, int _roleId) {
		this.setId(_id);
		this.setUuid(_uuid);
		this.setSortValue(_sortValue);
		this.setIsActive(_isActive);
		this.setUsername(_username);
		this.setFirstName(_firstName);
		this.setLastName(_lastName);
		this.setRoleId(_roleId);
	}

	public User() {
	}

	// ====================GETTERS====================
	public String getUsername() {
		return this.username;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public int getRoleId() {
		return this.roleId;
	}
	// ====================SETTERS====================
	public void setUsername(String _username) {
		this.username = _username;
	}

	public void setFirstName(String _firstName) {
		this.firstName = _firstName;
	}

	public void setLastName(String _lastName) {
		this.lastName = _lastName;
	}

	public void setRoleId(int _roleId) {
		this.roleId = _roleId;
	}
}
