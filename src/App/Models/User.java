package Models;

public class User {
	private int ID;
    private String username;
    private String firstName;
    private String lastName;
    
    public User(int ID, String username, String firstName, String lastName){
        this.ID=ID;
        this.username=username;
        this.firstName=firstName;
        this.lastName=lastName;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public void setID(int ID){
        this.ID=ID;
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    
    public boolean loadUser(int ID){
        boolean retVal = false;
        
        //@ todo attach controller for login.
        return retVal;
    }

}
