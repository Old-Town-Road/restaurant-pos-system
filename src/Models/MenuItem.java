package Models;

public class MenuItem extends Menu{
    private String itemName;
    private int price;
    private int priorityScore;
    private int executionTime;
    
    public MenuItem(int ID, String menuName, String item, String itemName, int price, int priorityScore, int executionTime){
        super(ID, menuName, itemName);
        this.itemName=itemName;
        this.price=price;
        this.priorityScore=priorityScore;
        this.executionTime=executionTime;
    }
    
    public String getItemName() {
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
    
    public void setItemName(String itemName) {
    	this.itemName=itemName;
    }
    public void setPrice(int price) {
    	this.price=price;
    }
    public void setPrioritySCore(int priorityScore) {
    	this.priorityScore=priorityScore;
    }
    public void setExectuionTime(int executionTime) {
    	this.executionTime=executionTime;
    }
}
