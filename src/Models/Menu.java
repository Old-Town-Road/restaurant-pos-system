package Models;
/**
 * This class is used to organize items to be consumed by the menu view
 * @author Ashimchalise
 * @since UPDATED: 2/12/20
 */
public class Menu {
    private int ID;
    private String menuName;
    private String items;
    
    public Menu(int _ID, String _menuName, String _items){
        this.ID = _ID;
        this.menuName = _menuName;
        this.items = _items;
    }
    
  //====================GETTERS====================
    public int getID(){
        return this.ID;
    }
    
    public String getMenuName(){
        return this.menuName;
    }
    
    public String getItems(){
        return this.items;
    }
    
  //====================SETTERS====================
    
    public void setID(int _ID){
        this.ID = _ID;
    }
    
    public void setMenuName(String _menuName){
        this.menuName = _menuName;
    }
    
    public void setItems(String _items){
        this.items = _items;
    }

}
