package Models;
/**
 * This class is used to organize items to be consumed by the menu view
 * @author ashimchalise
 * @since UPDATED: 2/5/20
 */
public class Menu {
    private int ID;
    private String menuName;
    private String items;
    
    public Menu(int ID, String menuName, String items){
        this.ID=ID;
        this.menuName=menuName;
        this.items=items;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public String getMenuName(){
        return this.menuName;
    }
    
    public String getItems(){
        return this.items;
    }
    
    public void setID(int ID){
        this.ID=ID;
    }
    
    public void setMenuName(String menuName){
        this.menuName=menuName;
    }
    
    public void setItems(String items){
        this.items=items;
    }

}
