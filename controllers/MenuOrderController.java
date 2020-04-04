package controllers;

/*
 * Author : Ashim Chalise, Date : 04/01/2020
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import views.SetView;
import views.Frame;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import models.Menu;
import models.MenuItem;

public class MenuOrderController {

	@FXML
	private TabPane tabPane;
	@FXML
	private ListView listOfItems = new ListView();
	@FXML
	private boolean debug = true;
	private ArrayList<Menu> menusToTab = new ArrayList<Menu>();

	public void MenuOrderController() {
		this.initialize();
	}

	public void initialize() {
		// Get all menus for the tab pane and put them in an array.
		if (debug) {
			// this is where Ashim adds fake data.
			this.menusToTab = this.assembleFakeData();
		} else {
			// TODO Ian attaches db functionality.
			// Get all menus from the DB that
			menusToTab = null;
		}

		// Feed the list to the tab pan
		for (Menu targetMenu : this.menusToTab) {
			this.addTab(targetMenu);
		}

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

	// this method adds tabs dynamically to the tabPane.
	@FXML
	private void addTab(Menu _targetMenu) {
		Tab tab = new Tab(_targetMenu.getMenuName());
		tab.setContent(createVboxAndPutButtons(_targetMenu));
		tabPane.getTabs().add(tab);
	}

	// this is the method to create vboxes dynamically and put them inside tabs.
	// this method is called by addTab() method.
	private VBox createVboxAndPutButtons(Menu _targetMenu) {
		VBox _vbox = new VBox();
		for (int i = 0; i < _targetMenu.getItems().size(); i++) {
			Button btn = new Button(_targetMenu.getItems().get(i).getItemName());
			btn.setOnAction(actionEvent -> {
				listOfItems.getItems().add(btn.getText());
			});
			_vbox.getChildren().add(btn);
		}

		return _vbox;
	}

	// This is the method that returns an ArrayList of fake data for the program.
	private ArrayList<Menu> assembleFakeData() {
		ArrayList<Menu> retVal = new ArrayList<Menu>();
		MenuItem pepPizza = new MenuItem(1, 1, "Pepperoni Pizza", 13.99, 0, 0);
		MenuItem chePizza = new MenuItem(2, 1, "Cheese Pizza", 13.99, 0, 0);
		MenuItem cokeDrink = new MenuItem(3, 2, "Coke", 3.99, 0, 0);
		MenuItem spriteDrink = new MenuItem(4, 2, "Sprite", 3.99, 0, 0);
		MenuItem friesSide = new MenuItem(5, 3, "Round Fries", 5.99, 0, 0);
		MenuItem chicNuggetsSide = new MenuItem(6, 3, "Real Chicken Nuggets", 8.99, 0, 0);

		MenuItem[] pMenu = { pepPizza, chePizza };
		MenuItem[] dMenu = { cokeDrink, spriteDrink };
		MenuItem[] sMenu = { friesSide, chicNuggetsSide };

		Menu pizzaMenu = new Menu(1, "Pizza", pMenu, models.MenuType.pizzas);
		Menu drinkMenu = new Menu(2, "Drink", dMenu, models.MenuType.drinks);
		Menu sideMenu = new Menu(3, "Sides", sMenu, models.MenuType.sides);

		retVal.add(pizzaMenu);
		retVal.add(drinkMenu);
		retVal.add(sideMenu);

		return retVal;
	}
}