package controllers;

/**
 * This class controls Table Selection View.
 *
 * @author Sultan Al Bogami Last Updated: 05/01/2020
 *
 * Notes:
 * -> Switched the table view from pane to gridpane and had it filled dynamically.
 * -> Created a method accepting data from log-in.
 * -> Created a method tableAction, which checks the user auth.
 * -> and pass data to menu order.
 * -> Made sure the style-guide is followed.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Check;
import models.Table;
import models.Ticket;
import models.User;
import views.Frame;
import views.SetView;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderTypeController implements Initializable {
    @FXML
    private TextField selectedTable;
    private ArrayList<Table> tables;
    private ArrayList<Check> checks;
    private User currentUser;
    private Check check;
    private boolean debug = true;

    public OrderTypeController() {
    }

    /**
     * Called once implementing the controller.
     */
    public void initialize(URL _url, ResourceBundle _rb) {
        if (debug) {
            this.currentUser = createFakeUser(this.currentUser);
            this.tables = this.assembleFakeTableData();
            this.checks = this.assembleFakeCheckData();
        } else {
            // this.tableChecks = ;
            // this.checks = ;
        }
    }

    /**
     * A helper method accepting a user object.
     *
     * @param _user
     */
    public void initData(User _user) {
        this.currentUser = _user;
    }

    /**
     * Called when any of the scene buttons are clicked.
     *
     * @param _event triggered by any of the buttons clicked by the _currentUser
     */
    @FXML
    public void tableAction(ActionEvent _event) {
        Button button = (Button) _event.getSource();
        // Get the text of the button being clicked.
        String actionType = button.getText();
        ArrayList<Table> currentTables = this.tables;
        ArrayList<Check> currentChecks = this.checks;
        int selectedTableNum = Integer.parseInt(selectedTable.getText());
        helperMethod(selectedTableNum, currentChecks, actionType);
        Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        stage.close();
        new Frame(new Stage(), SetView.MENU_ORDER_VIEW);
    }

    private void helperMethod(int _selectedTableNum, ArrayList<Check> _currentChecks, String _actionType) {
        if (_actionType == "Start Check") {
            for (int i = 0; i < _currentChecks.size(); i++) {
                if (_currentChecks.get(i).getTableId() == _selectedTableNum && _currentChecks.get(i).getCheckStatus() == 0) {
                    // Instantiate a Date object for fake check & ticket
                    Date date = new Date();
                    // create the fake check
                    Check check = new Check(_selectedTableNum, this.currentUser.getId(), 1, date, null);
                    // create a fake ticket
                    Ticket ticket = new Ticket(date, this.currentUser.getId(), _selectedTableNum, 1, 1);
                }
                else {
                    System.out.println("Selected table does not exist!");
                }
            }

        } else {
            //
        }
    }

//    /**
//     * Called when a log-off button is clicked.
//     *
//     * @param _event triggered by the log-off button
//     */
//    @FXML
//    private void logOffAction(ActionEvent _event) {
//        Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
//        stage.close();
//        new Frame(new Stage(), SetView.LOG_IN_VIEW);
//    }

    /**
     * A helper method assigning fake info to user for debugging purposes.
     * @param _currentUser is a user object acting as a fake user.
     * @return a fake user object.
     */
    private User createFakeUser(User _currentUser) {
        _currentUser = new User(1, "1", 1, true, "I am a", "FAKE", "USER", 1);
        return _currentUser;
    }

    /**
     * A helper method generating fake checks data.
     *
     * @return an ArrayList of checks.
     */
    private ArrayList<Check> assembleFakeCheckData() {
        // Create an array list of checks
        ArrayList<Check> retVal = new ArrayList<Check>();
        Date dateStarted = new Date();
        // Create the check objects.
        Check check0 = new Check(1, this.currentUser.getId(), 1, dateStarted, null);
        Check check1 = new Check(1, 2, 0, dateStarted, null);
        Check check2 = new Check(1, 6, 1, dateStarted, null);
        Check check3 = new Check(1, this.currentUser.getId(), 0, dateStarted, null);
        Check check4 = new Check(1, 9, 1, dateStarted, null);
        Check check5 = new Check(1, this.currentUser.getId(), 1, dateStarted, null);
        retVal.add(check0);
        retVal.add(check1);
        retVal.add(check2);
        retVal.add(check3);
        retVal.add(check4);
        retVal.add(check5);
        return retVal;
    }

    /**
     * A helper method to fill the ArrayList of tables with fake data.
     *
     * @return an arrayList of dummy table data for debugging.
     */
    private ArrayList<Table> assembleFakeTableData() {
        // Declare the return value
        ArrayList<Table> retVal = new ArrayList<Table>();
        // Instantiate the table object.
        Table t0 = new Table("0");
        Table t1 = new Table("1");
        Table t2 = new Table("2");
        Table t3 = new Table("3");
        Table t4 = new Table("4");
        Table t5 = new Table("5");
        // Fill the ArrayList
        retVal.add(t0);
        retVal.add(t1);
        retVal.add(t2);
        retVal.add(t3);
        retVal.add(t4);
        retVal.add(t5);
        return retVal;
    }
//
//    /**
//     * Called when a log-off button is clicked.
//     *
//     * @param _event
//     */
//    @FXML
//    private void logOffAction(ActionEvent _event) {
//        Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
//        stage.close();
//        new Frame(new Stage(), SetView.LOG_IN_VIEW);
//    }
}
