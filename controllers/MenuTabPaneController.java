package controllers;

/**
 * This class is a controller that creates custom tabs on db calls.
 */
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import models.Menu;

public class MenuTabPaneController {

	@FXML
	private TabPane tabPane;
	private boolean debug = true;

	public void initialize() {
		Menu [] menusToTab = null;
		//Get all menus for the tab pane and put them in an array.
		if(debug) {
			//TODO
			//this is where Ashim adds fake data.
			menusToTab = null;
		} else {
			//TOD Ian attaches db functionality.
			//Get all menus from the DB that 
			menusToTab = null;
		}
		//Feed the list to the tab pan
		for(Menu targetMenu : menusToTab) {
			this.addTab(targetMenu);
		}
	}

	@FXML
	private void addTab(Menu _targetMenu) {
		Tab tab = new Tab(_targetMenu.getMenuName());
		tabPane.getTabs().add(tab);
	}

}