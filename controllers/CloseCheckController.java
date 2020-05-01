package controllers;

/**
 * This class controls CloseCheck view.
 *
 * @author Raiana Zaman, Sultan Al Bogami Last Updated: 05/01/2020
 * DEV-NOTES:
 * -> Created a method accepting data from other controllers.
 * -> Modified cashOutAction method to call a helper method.
 * -> Created a helper method processing the payment with relevent data.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.Frame;
import views.SetView;
import java.net.URL;
import java.util.ResourceBundle;

public class CloseCheckController implements Initializable {
	private User currentUser;
	private Check currentCheck;
	private Ticket currentTicket;
	private String cashOutSourceIdentifier;

	public CloseCheckController() {
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
	 * A helper method accepting the data for cash out Action.
	 * @param _check
	 * @param _ticket
	 * @param _cashOutSourceIdentifier
	 */
	public void initData(User _user, Check _check, Ticket _ticket, String _cashOutSourceIdentifier) {
		this.currentUser = _user;
		this.currentCheck = _check;
		this.currentTicket = _ticket;
		this.cashOutSourceIdentifier = _cashOutSourceIdentifier;
	}

	/**
	 * Called when cash button is clicked.
	 *
	 * @param _event
	 */
	@FXML private void cashOutAction(ActionEvent _event) {
		// Get the text of the button which determines the payment type.
		String paymentType = ((Button)_event.getSource()).getText();
		// Call the helper method to process the payment and do the cash-out.
		processPayment(this.currentUser, this.currentCheck, this.currentTicket, paymentType, this.cashOutSourceIdentifier);
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
		stage.close();
		new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
	}

	/**
	 * A helper method to process the payment and create a transaction.
	 * A cash-out source identifier is used to check whether a check or,
	 * a ticket is being cashed out.
	 *
	 * @param _currentUser
	 * @param _currentCheck
	 * @param _currentTicket
	 * @param _paymentType
	 * @param _cashOutSourceIdentifier
	 */
	private void processPayment(User _currentUser, Check _currentCheck, Ticket _currentTicket, String _paymentType,
								 String _cashOutSourceIdentifier) {
			// TODO create transaction history and update tables.
		}
	}
}
