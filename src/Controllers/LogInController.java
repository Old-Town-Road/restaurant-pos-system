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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import views.SetView;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button logIn;
    @FXML
    private Hyperlink hpl = new Hyperlink();
    @FXML
    private String url = "https://support.google.com/accounts/answer/7682439?hl=en&ref_topic=3382255";
    @FXML
    private final WebView browser = new WebView();
    @FXML
    private final WebEngine webEngine = browser.getEngine();

    private static final String FROM_FXML_FILE_PATH = "views/fxml/";

    public LogInController() {
    }


    /**
     * Called once implementing the controller.
     *
     * @Override
     */
    public void initialize(URL _url, ResourceBundle _rb) {
    }

    /**
     * Called when log in button is clicked.
     *
     * @param _event
     */
    private void ActionLogin(ActionEvent _event) {
        Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        stage.close();
        new Frame(SetView.SERVICE_OPTIONS_VIEW);
    }

    /**
     * Called when a Hyperlink is clicked.
     *
     * @param _event
     */
    @FXML
    private void handleHyperlinkAction(ActionEvent _event) {
        // Hyperlink was clicked, go to ...
        Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        stage.close();
        webEngine.load(url);
    }
}