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
	private MenuItem pepPizza = new MenuItem(1, "alfredo", 13.99, 0, 0);
	private MenuItem chePizza = new MenuItem(1, "ragu", 13.99, 0, 0);
	private MenuItem cokeDrink = new MenuItem(2, "bourbon", 3.99, 0, 0);
	private MenuItem spriteDrink = new MenuItem(2, "scotch", 3.99, 0, 0);
	private MenuItem friesSide = new MenuItem(3, "nachos", 5.99, 0, 0);
	private MenuItem chicNuggetsSide = new MenuItem(3, "olives", 8.99, 0, 0);

	private Menu pastaMenu = new Menu("Pasta", ModelConstants.MENU_TYPE_PIZZAS);
	private Menu boozeMenu = new Menu("booze", ModelConstants.MENU_TYPE_DRINKS);
	private Menu fingerFoodsMenu = new Menu("FingerFood", ModelConstants.MENU_TYPE_SIDES);
	private Menu pizzaMenu;
	private Menu drinksMenu;
	private Menu sidesMenu;

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

		this.pastaMenu.setItems(pMenu);
		this.boozeMenu.setItems(dMenu);
		this.fingerFoodsMenu.setItems(sMenu);
	}
	@Test
	final void testCreateObject() {
		//Test basic creations
		try {
			assertTrue("failed creation pasta menu", this.pastaMenu.saveMenuAndContents());
			assertTrue("failed creation booze menu", this.boozeMenu.saveMenuAndContents());
			assertTrue("failed creation finger food menu", this.fingerFoodsMenu.saveMenuAndContents());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	final void testDeleteObject() {
		assertTrue("failed creation pasta menu", this.pastaMenu.saveMenuAndContents());
		assertTrue("failed creation booze menu", this.boozeMenu.saveMenuAndContents());
		assertTrue("failed creation finger food menu", this.fingerFoodsMenu.saveMenuAndContents());
		assertTrue("fail delete pasta menu", this.pastaMenu.deleteObjectFromDatabase());
		assertTrue("fail delete booze menu", this.boozeMenu.deleteObjectFromDatabase());
		assertTrue("fail delete finger menu", this.fingerFoodsMenu.deleteObjectFromDatabase());
	}

	/*
	 * @Test final void testReadObject() { fail("Not yet implemented"); // TODO }
	 * 
	 * @Test final void testUpdateObject() { fail("Not yet implemented"); // TODO }
	 * 
	 *  // TODO }
	 */

}
