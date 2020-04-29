package testing;

/**
 * This is a base tester for the DataStoreAdapterTest.
 */
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.Menu;
import models.MenuItem;
import models.ModelConstants;

class TestDataStoreAdapter {
	//Static objects for testing.
	private MenuItem pepPizza = new MenuItem(1, "Pepperoni Pizza", 13.99, 0, 0);
	private MenuItem chePizza = new MenuItem(1, "Cheese Pizza", 13.99, 0, 0);
	private MenuItem cokeDrink = new MenuItem(2, "Coke", 3.99, 0, 0);
	private MenuItem spriteDrink = new MenuItem(2, "Sprite", 3.99, 0, 0);
	private MenuItem friesSide = new MenuItem(3, "Round Fries", 5.99, 0, 0);
	private MenuItem chicNuggetsSide = new MenuItem(3, "Real Chicken Nuggets", 8.99, 0, 0);

	private Menu pizzaMenu = new Menu("Pizza", ModelConstants.MENU_TYPE_PIZZAS);
	private Menu drinkMenu = new Menu("Drink", ModelConstants.MENU_TYPE_DRINKS);
	private Menu sideMenu = new Menu("Sides", ModelConstants.MENU_TYPE_SIDES);

	private boolean alignmentFlag = false;

	@BeforeEach
	void setUp() throws Exception {
		if(!alignmentFlag) {
			alignmentSetUp();
		}

		this.alignmentFlag = true;
	}

	/**
	 * This method assigns information to each other.
	 */
	void alignmentSetUp() {
		MenuItem[] pMenu = { pepPizza, chePizza };
		MenuItem[] dMenu = { cokeDrink, spriteDrink };
		MenuItem[] sMenu = { friesSide, chicNuggetsSide };

		pizzaMenu.setItems(pMenu);
		drinkMenu.setItems(dMenu);
		sideMenu.setItems(sMenu);
	}
	@Test
	final void testCreateObject() {
		//Test basic creations
		try {
			assertTrue("Not a successful creation", this.pepPizza.saveObjectInDatabase());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @Test final void testReadObject() { fail("Not yet implemented"); // TODO }
	 * 
	 * @Test final void testUpdateObject() { fail("Not yet implemented"); // TODO }
	 * 
	 * @Test final void testDeleteObject() { fail("Not yet implemented"); // TODO }
	 */

}
