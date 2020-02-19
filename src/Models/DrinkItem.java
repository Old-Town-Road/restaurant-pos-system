package Models;
/**
 * This class represents the Drink Item
 * @author Ashim Chalise, Ian Wilhelmsen Last Updated: 2/19/2020
 * 
 * NOTES:
 * 2/19/2020 IMW
 * -> Changed the type of _ticketStatus to new enum created.
 * -> Verified the styling.updated - 2/12/20
 */
public class DrinkItem extends MenuItem{

    public DrinkItem(int _ID, String _menuName, String _item, String _itemName, int _price, int _priorityScore, int _executionTime) {
	super(_ID, _menuName, _item, _itemName, _price, _priorityScore, _executionTime);
	// TODO Auto-generated constructor stub
    }

}