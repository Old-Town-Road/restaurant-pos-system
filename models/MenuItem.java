package models;
/*
 * This class represents the Menu Items
 * @author- Ashim Chalise, Ian Wilhelmsen updated - 2/19/2020
 * NOTES:
 * 2/19/2020
 * -> formating check and verification
 */
import java.util.ArrayList;

public class MenuItem extends Menu {
    private ArrayList<String> itemName = new ArrayList<String>();
    private int price;
    private int priorityScore;
    private int executionTime;
    

    public MenuItem(int _ID, String _menuName, String _item, ArrayList<String> _itemName, int _price, int _priorityScore, int _executionTime) {
	super(_ID, _menuName, _item);
	//this.itemName = _itemName;
	this.price = _price;
	this.priorityScore = _priorityScore;
	this.executionTime = _executionTime;
	
	  itemName.add("hawaiian");
	  itemName.add("chicken");
	  itemName.add("pepperoni");
    }

    
    
    
    
    
    
//====================GETTERS====================

    public ArrayList<String> getItemName() {
	return this.itemName;
    }

    public int getPrice() {
	return this.price;
    }

    public int getPriorityScore() {
	return this.priorityScore;
    }

    public int getExecutionTime() {
	return this.executionTime;
    }

//====================SETTERS====================

    public void setItemName(ArrayList<String> _itemName) {
	this.itemName = _itemName;
    }

    public void setPrice(int _price) {
	this.price = _price;
    }

    public void setPrioritySCore(int _priorityScore) {
	this.priorityScore = _priorityScore;
    }

    public void setExectuionTime(int _executionTime) {
	this.executionTime = _executionTime;
    }

}
