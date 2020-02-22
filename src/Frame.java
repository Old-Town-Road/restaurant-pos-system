/**
 * This class initializes the frame.
 *
 * @author Sultan Albogami Last Updated: 2/22/2020
 */

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.GetSource;
import views.SetView;
import java.io.IOException;

public class Frame {

    // UDC
    public Frame(Stage _primaryStage, String _fileName) {

        // Set Parent to NULL
        Parent root = null;
        try {
            root = (Parent) GetSource.FXMLObjectLoader(_fileName);
            // Create a scene
            Scene scene = new Scene(root);
            // Set the window title
            _primaryStage.setTitle(SetView.WINDOW_TITLE);
            _primaryStage.setScene(scene);
            _primaryStage.setResizable(false);
            _primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
