package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class ErrorController implements Initializable {

    @FXML
    Text title;
    @FXML
    Text text;

    @FXML
    private void Close(ActionEvent ae) {
        Stage stage = (Stage) title.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setData(String title, String text) {
        this.title.setText(title);
        this.text.setText(text);
    }
}
