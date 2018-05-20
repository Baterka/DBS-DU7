package Controllers;

import Models.BankCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Models.Validator;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class EditBankController implements Initializable {
    
    private int id;

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
    private void edit(ActionEvent ae) {
        
        Validator validator = new Validator();
        try {

            // Validate
            validator.validateBankForm(
                    name.getText(),
                    codename.getText(),
                    address.getText()
            );

            // Update
            BankCRUD.update(
                    id,
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

    public void setParentController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void setData(int id, String name, String codename, String address) {
        
        this.id = id;
        this.name.setText(name);
        this.codename.setText(codename);
        this.address.setText(address);
        
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
