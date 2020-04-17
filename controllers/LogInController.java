package controllers;

/**
 * This class controls log in view.
 *
 * @author Sultan Al Bogami Last Updated: 2/22/2020
 * @author Raiana zaman Last Updated: 3/18/2020
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.net.URL;
import java.util.ResourceBundle;

import API.GoogleAuthWrapper;
import API.OAuthException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import views.Frame;
import views.SetView;

public class LogInController implements Initializable {


    GoogleAuthWrapper google;

    public LogInController() throws IOException {

        google = new GoogleAuthWrapper(8000,
                "297008996238-d2fmttvlbl4mnetvgf7837okn7squj1e.apps.googleusercontent.com",
                "XESnLdvdh2e6Eiege72NgEtO");
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
     * @throws OAuthException
     * @throws IOException
     */

    @FXML
    private void logInAction(ActionEvent _event) throws IOException, OAuthException {
        // Map<String, String> userInfo = google.getUserInfo();
        Map<String, String> userInfo = null;
        try {
            userInfo = google.getUserInfo();
            Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
            stage.close();
            // new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
        } catch (IOException | OAuthException e) {
            e.printStackTrace();
        }

        userInfo.forEach((k, v) -> System.out.println(k + " : " + v));
        openNewStage(userInfo.get("name"));


        //System.out.println("newvalue-1: "+ userInfo.get("given_name"));
        //System.out.println("newvalue-2: "+ userInfo.get("name"));
    }

    /**
     * This class pass data between the controller.
     *
     * @author Raiana zaman Last Updated: 4/16/2020
     */

    void openNewStage(String Val) throws IOException {
        /**Loading the fxml of second controller where you want to send the data*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/OrderType.fxml"));
        Parent root = (Parent) loader.load();


        /*** Creating the object of second controller */
        OrderTypeController secController = loader.getController();
        secController.myFunction(Val);


        /** Loading the stage */
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }
}