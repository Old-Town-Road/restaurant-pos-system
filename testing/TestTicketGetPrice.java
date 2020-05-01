package testing;

/**
 * The point of this is to test the getPrice method from Ticket
 * @author Ian Wilhelmsen
 * last updated: 4/30/2020
 */
import java.util.ArrayList;
import models.Menu;
import models.MenuItem;
import models.ModelConstants;
import models.Ticket;

public class TestTicketGetPrice {

	public static void main(String[] args) {
		//Some variables for testing data and to measure success
		ArrayList<Menu> testData = assembleFakeData();
		double[] expectedValues = {0, -20, 0, 8, 16};
		Ticket testTicket = new Ticket();
		boolean successFlag = true;
		String[] passMessages = {"Passed the Error case.",
				"We can add two negative numbers.",
				"We can add a negative and a positive number.",
				"We can add positive numbers.",
				"We can add positive numbers again.",
		};
		String failMessage = "Wow we suck at life.";
		String successMessage = "The function passed all tests.";

		for(int x = 0; x < testData.size(); x++) {
			if(successFlag) {
				testTicket = new Ticket();
				testTicket.addMenuItems(testData.get(x).getItems());
				successFlag = checkPrice(expectedValues[x], testTicket);
				System.out.println(successFlag ? passMessages[x] : failMessage);
			}
		}
		System.out.println(successFlag ? successMessage : failMessage);
	}

	public static boolean checkPrice(double _expectedPrice, Ticket _currTicket) {
		return (_expectedPrice == _currTicket.getPrice());
	}
	public static ArrayList<Menu> assembleFakeData() {
		ArrayList<Menu> retVal = new ArrayList<Menu>();

		MenuItem pepPizza = new MenuItem(1, "Pepperoni Pizza", 10.00, 0, 0);
		MenuItem chePizza = new MenuItem(1, "Cheese Pizza", -10.00, 0, 0);
		MenuItem cokeDrink = new MenuItem(2, "Coke", 4, 0, 0);
		MenuItem[] aMenuList = { chePizza, chePizza };
		MenuItem[] bMenuList = { pepPizza, chePizza };
		MenuItem[] cMenuList = { cokeDrink, cokeDrink};
		MenuItem[] dMenuList = { cokeDrink, cokeDrink, cokeDrink, cokeDrink};
		Menu nullMenu = new Menu("Pizza", ModelConstants.MENU_TYPE_PIZZAS);
		Menu aMenu = new Menu("Pizza", ModelConstants.MENU_TYPE_PIZZAS);
		Menu bMenu = new Menu("Pizza", ModelConstants.MENU_TYPE_PIZZAS);
		Menu cMenu = new Menu("Pizza", ModelConstants.MENU_TYPE_PIZZAS);
		Menu dMenu = new Menu("Pizza", ModelConstants.MENU_TYPE_PIZZAS);
		aMenu.setItems(aMenuList);
		bMenu.setItems(bMenuList);
		cMenu.setItems(cMenuList);
		dMenu.setItems(dMenuList);
		retVal.add(nullMenu);
		retVal.add(aMenu);
		retVal.add(bMenu);
		retVal.add(cMenu);
		retVal.add(dMenu);
		return retVal;
	}
}