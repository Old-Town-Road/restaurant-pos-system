package controllers;

/**
 * This class controls log in view.
 *
 * @author Sultan Al Bogami Last Updated: 2/22/2020
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

public class LogInController implements Initializable {

    public LogInController() {
    }

    /**
     * Called once implementing the controller.
     *
     * @param _url
     * @param _rb
     */
    public void initialize(URL _url, ResourceBundle _rb) {
    }

    /**
     * Called when log in button is clicked.
     *
     * @param _event
     */
    @FXML
    private void logInAction(ActionEvent _event) {
        Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        stage.close();
        new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
    }
}
