package Controllers;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Models.BankCRUD;
import Models.Validator;
import org.postgresql.util.PSQLException;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class AddBankController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField address;
    @FXML
    TextField codename;
    @FXML
    Text error;

    private MainController mainController;

    @FXML
    private void add(ActionEvent ae) {

        Validator validator = new Validator();
        try {

            // Validate
            validator.validateBankForm(
                    name.getText(),
                    codename.getText(),
                    address.getText()
            );

            // Create
            BankCRUD.create(
                    name.getText(),
                    codename.getText(),
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
            mainController.updateBanks();
        }

    }
}
