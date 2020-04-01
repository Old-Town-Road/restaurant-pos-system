package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuView {
	
	TabPane tabPane = new TabPane();

    Tab tab1 = new Tab("Planes", new Label("Show all planes available"));
    Tab tab2 = new Tab("Cars"  , new Label("Show all cars available"));
    Tab tab3 = new Tab("Boats" , new Label("Show all boats available"));

    tabPane.getTabs().add(tab1);
    tabPane.getTabs().add(tab2);
    tabPane.getTabs().add(tab3);

    VBox vBox = new VBox(tabPane);
    Scene scene = new Scene(vBox);
	    
}
