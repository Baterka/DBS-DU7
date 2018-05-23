package Models;

import Controllers.ErrorController;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class ErrorWindow {

    private String title;
    private String message;

    private final Logger LOG = Logger.getLogger(getClass().getName());

    public ErrorWindow(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void open() {

        try {

            FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("Error.fxml"));
            Loader.load();
            ErrorController errorController = Loader.getController();
            errorController.setData(
                    title,
                    message
            );
            Stage stage = new Stage();
            stage.setScene(new Scene((AnchorPane) Loader.getRoot()));
            stage.show();

        } catch (IOException e) {
            LOG.warning(e.toString());
        }
    }
}
