package views;

/**
 * This class helps return the constants for the views.
 *
 * @author Sultan Al Bogami Last Updated: 2/22/2020
 */

import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class GetSource {
    private static final String FXML_FILE_EXTENSION = ".fxml";
    private static final String FXML_FILE_PATH = "/views/fxml/";

    /**
     * Called when an fxml file is passed as an argument.
     *
     * @param _FXMLFileName
     * @return
     */
    public static String getFXML(String _FXMLFileName) {
        return FXML_FILE_PATH + _FXMLFileName + FXML_FILE_EXTENSION;
    }

    /**
     * Called when fxml file is loaded.
     *
     * @param _fileName
     * @return
     * @throws IOException
     */
    public static Object FXMLObjectLoader(String _fileName) throws IOException {
        return FXMLLoader.load(GetSource.class.getResource(getFXML(_fileName)));
    }
}