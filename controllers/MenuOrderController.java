package controllers;

/**
 * This manages the menu ordering controller.
 * @author Ashim Chalise, Ian Wilhelmsen
 * Last Update : 05/01/2020
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import views.SetView;
import views.Frame;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import database.DatabaseStandardReads;
import java.util.ArrayList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import models.Menu;
import models.MenuItem;
import models.ModelConstants;

public class MenuOrderController {

	@FXML
	public TabPane tabPanes = new TabPane();
	@FXML
	private ListView<VBox> listOfItems = new ListView<VBox>();
	@FXML
	private Button VoidItem = new Button();
	@FXML
	private TextField tfTotal = new TextField();

	private double priceCount = 0;
	private boolean debug = false;

	private ArrayList<Menu> menusToTab = new ArrayList<Menu>();

	private enum num {
		tabHeight(25), tabWidth(100), btnHeight(5), btnWidth(200), insetsAndPadding(1), constantForPriceCount(100);

		private final double value;

		num(double value) {
			this.value = value;
		}

		public double getVal() {
			return value;
		}
	}

	public MenuOrderController() throws ClassNotFoundException {
		this.initialize();
	}

	public void initialize() throws ClassNotFoundException {
		// Get all menus for the tab pane and put them in an array.
		if (debug) {
			// this is where Ashim adds fake data.
			this.menusToTab = this.assembleFakeData();

		} else {
			// Get all menus from the DB and cast each to a the correct
			this.menusToTab = DatabaseStandardReads.getFilledListOfMenus();
		}

		// Feed the list to the tab pan
		for (Menu targetMenu : this.menusToTab) {
			this.addTab(targetMenu);
		}
	}

	/**
	 * This method helps the user cash out by navigating to close check window
	 * 
	 * @param _event
	 */
	@FXML
	public void cashOutAction(ActionEvent _event) {
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
		stage.close();
		new Frame(new Stage(), SetView.CLOSE_CHECK_VIEW);
	}

	/**
	 * This method helps the user send the order and get started again
	 * 
	 * @param _event
	 */
	@FXML
	public void sendAction(ActionEvent _event) {
		System.out.print("Order sent");
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
		stage.close();
		new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
	}

	/**
	 * This method adds tabs dynamically to the tabPane.
	 * 
	 * @param _targetMenu
	 */
	@FXML
	public void addTab(Menu _targetMenu) {
		Tab tab = new Tab(_targetMenu.getMenuName());
		this.tabPanes.setTabMinWidth(num.tabWidth.getVal());
		this.tabPanes.setTabMinHeight(num.tabHeight.getVal());
		tab.setStyle("-fx-border-color:YELLOWGREEN ; -fx-background-color: DEEPSKYBLUE ;");
		tab.setContent(createVboxAndPutButtons(_targetMenu));
		this.tabPanes.getTabs().add(tab);
	}

	/**
	 * This is the method to create Vboxes dynamically and put them inside tabs.
	 * This method is called by addTab() method.
	 * 
	 * @param _targetMenu
	 * @return vbox
	 */
	public VBox createVboxAndPutButtons(Menu _targetMenu) {

		VBox vbox = new VBox();

		// This code basically grooms the vbox
		vbox.setPadding(new Insets(num.insetsAndPadding.getVal(), num.insetsAndPadding.getVal(),
				num.insetsAndPadding.getVal(), num.insetsAndPadding.getVal()));
		vbox.setSpacing(num.insetsAndPadding.getVal());
		vbox.setStyle("-fx-background-color: wheat ;");
		// This code creates new buttons and adds them.
		for (int i = 0; i < _targetMenu.getItems().size(); i++) {
			Button btn = new Button(_targetMenu.getItems().get(i).getItemName());
			btn.setMaxWidth(num.btnWidth.getVal());
			btn.setMaxHeight(num.btnHeight.getVal());
			btn.setStyle("-fx-border-color:YELLOWGREEN ; -fx-background-color: DEEPSKYBLUE ;");
			double price = _targetMenu.getItems().get(i).getPrice();
			// This code is putting the items in the order list.
			btn.setOnAction(actionEvent -> {
				listOfItems.getItems().add(vBoxInList(btn, price));
			});
			vbox.getChildren().add(btn);
		}
		return vbox;
	}

	/**
	 * This method creates VBoxes to show name and price of items inside the List or
	 * Order. This method is called by createVboxAndPutButtons() method.
	 * 
	 * @param _btn
	 * @param _price
	 * @return vbox
	 */
	public VBox vBoxInList(Button _btn, double _price) {
		Text name = new Text(_btn.getText());
		Text priceText = new Text(String.valueOf(_price));

		// Updating the total priceCount.
		priceCount = Math.round((_price + priceCount) * num.constantForPriceCount.getVal())
				/ num.constantForPriceCount.getVal();
		tfTotal.setText(String.valueOf(priceCount));

		// Putting the lists in VBox.
		VBox vbox = new VBox(name, priceText);
		// Calls the method to remove item.
		removeItem(_price);
		return vbox;
	}

	/**
	 * This is the method for void button. It removes an item from the order.
	 * 
	 * @param _price
	 */
	public void removeItem(double _price) {
		VoidItem.setOnAction(actionEvent -> {
			int selectedIdx = listOfItems.getSelectionModel().getSelectedIndex();
			if (selectedIdx != -1) {
				// selects new item and deletes the selected item.
				int newSelectedIdx = (selectedIdx == listOfItems.getItems().size() - 1) ? selectedIdx - 1 : selectedIdx;
				listOfItems.getItems().remove(selectedIdx);
				listOfItems.getSelectionModel().select(newSelectedIdx);

				// Updates the total price;
				priceCount = Math.round((priceCount - _price) * num.constantForPriceCount.getVal())
						/ num.constantForPriceCount.getVal();
				tfTotal.setText(String.valueOf(priceCount));
			}
		});
	}

	/**
	 * This is the method that returns an ArrayList of fake data for the program.
	 * 
	 * @return retVal
	 */
	public ArrayList<Menu> assembleFakeData() {
		ArrayList<Menu> retVal = new ArrayList<Menu>();

		MenuItem pepPizza = new MenuItem(1, "Pepperoni Pizza", 13.99, 0, 0);
		pepPizza.setId(1);
		MenuItem chePizza = new MenuItem(1, "Cheese Pizza", 13.99, 0, 0);
		chePizza.setId(2);
		MenuItem cokeDrink = new MenuItem(2, "Coke", 3.99, 0, 0);
		cokeDrink.setId(3);
		MenuItem spriteDrink = new MenuItem(2, "Sprite", 3.99, 0, 0);
		spriteDrink.setId(4);
		MenuItem friesSide = new MenuItem(3, "Round Fries", 5.99, 0, 0);
		friesSide.setId(5);
		MenuItem chicNuggetsSide = new MenuItem(3, "Real Chicken Nuggets", 8.99, 0, 0);
		chicNuggetsSide.setId(6);

		MenuItem[] pMenu = { pepPizza, chePizza };
		MenuItem[] dMenu = { cokeDrink, spriteDrink };
		MenuItem[] sMenu = { friesSide, chicNuggetsSide };

		Menu pizzaMenu = new Menu("Pizza", ModelConstants.MENU_TYPE_PIZZAS);
		pizzaMenu.setItems(pMenu);
		pizzaMenu.setItems(dMenu);
		pizzaMenu.setItems(sMenu);
		Menu drinkMenu = new Menu("Drink", ModelConstants.MENU_TYPE_DRINKS);
		drinkMenu.setItems(dMenu);
		Menu sideMenu = new Menu("Sides", ModelConstants.MENU_TYPE_SIDES);
		sideMenu.setItems(sMenu);

		retVal.add(pizzaMenu);
		retVal.add(drinkMenu);
		retVal.add(sideMenu);

		return retVal;
	}
}