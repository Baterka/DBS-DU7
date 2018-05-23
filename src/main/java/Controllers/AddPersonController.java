package Controllers;

import Models.Formatter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Models.PersonCRUD;
import Models.Validator;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class AddPersonController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField address;
    @FXML
    TextField bd;
    @FXML
    TextField bm;
    @FXML
    TextField by;
    @FXML
    Text error;

    private MainController mainController;

    @FXML
    private void add(ActionEvent ae) {

        Validator validator = new Validator();
        try {

            // Validate
            validator.validatePersonForm(
                    name.getText(),
                    surname.getText(),
                    bd.getText(),
                    bm.getText(),
                    by.getText(),
                    address.getText()
            );

            Date birthdate = Formatter.formatDate(Integer.parseInt(bd.getText()), Integer.parseInt(bm.getText()), Integer.parseInt(by.getText()));

            // Create
            PersonCRUD.create(
                    name.getText(),
                    surname.getText(),
                    birthdate,
                    address.getText()
            );
            callUpdate();
            closeWindow();

        } catch (Exception e) {
            error.setText(e.getMessage());
        }

    }

    public void setMainController(MainController parentController) {
        this.mainController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void closeWindow() {

        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();

    }

    private void callUpdate() {

        if (mainController != null) {
            mainController.updatePersons();
        }

    }
}
