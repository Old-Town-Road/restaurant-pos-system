package controllers;

/*
 * Author : Ashim Chalise, Date : 04/01/2020
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import views.SetView;
import views.Frame;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import models.Menu;
import models.MenuItem;

public class MenuOrderController {

	@FXML
	private TabPane tabPane;
	private boolean debug = true;

	public void MenuOrderController() {
		this.initialize();
	}

	public void initialize() {
		ArrayList<Menu> menusToTab = new ArrayList<Menu>();
		// Get all menus for the tab pane and put them in an array.
		if (debug) {
			// TODO
			// this is where Ashim adds fake data.
			// MenuItem(int _ID, int _menuID, String _itemName, int _price, int
			// _priorityScore, int _executionTime)
			MenuItem pepPizza = new MenuItem(1, 1, "Pepperoni Pizza", 13.99, 0, 0);
			MenuItem chePizza = new MenuItem(2, 1, "Cheese Pizza", 13.99, 0, 0);
			MenuItem cokeDrink = new MenuItem(3, 2, "Coke", 3.99, 0, 0);
			MenuItem spriteDrink = new MenuItem(4, 2, "Sprite", 3.99, 0, 0);
			MenuItem friesSide = new MenuItem(5, 3, "Round Fries", 5.99, 0, 0);
			MenuItem chicNuggetsSide = new MenuItem(6, 3, "Real Chicken Nuggets", 8.99, 0, 0);

			MenuItem[] pMenu = { pepPizza, chePizza };
			MenuItem[] dMenu = { cokeDrink, spriteDrink };
			MenuItem[] sMenu = { friesSide, chicNuggetsSide };

			Menu pizzaMenu = new Menu(1, "PizzaTrial", pMenu);
			Menu drinkMenu = new Menu(2, "DrinkTest", dMenu);
			Menu sideMenu = new Menu(3, "SidesHopeItWorks", sMenu);

			menusToTab.add(pizzaMenu);
			menusToTab.add(drinkMenu);
			menusToTab.add(sideMenu);

		} else {
			// TOD Ian attaches db functionality.
			// Get all menus from the DB that
			menusToTab = null;
		}
		// Feed the list to the tab pan
		for (Menu targetMenu : menusToTab) {
			this.addTab(targetMenu);
		}
	}

	@FXML
	private void addTab(Menu _targetMenu) {
		Tab tab = new Tab(_targetMenu.getMenuName());
		tabPane.getTabs().add(tab);
	}

	// This method helps the user cash out by navigating to close check window
	@FXML
	void cashOutAction(ActionEvent _event) {
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
		stage.close();
		new Frame(new Stage(), SetView.CLOSE_CHECK_VIEW);
	}

	// This method helps the user send the order and get started again
	@FXML
	void sendAction(ActionEvent _event) {
		System.out.print("Order sent");
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
		stage.close();
		new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
	}

}