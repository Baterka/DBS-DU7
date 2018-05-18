package controller;

import dbModel.JavaBanks;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class AddBankController implements Initializable {

    private String type;

    @FXML
    TextField name;
    @FXML
    TextField address;
    @FXML
    TextField codename;
    @FXML
    Text error;
    
    private MainController parentController;

    @FXML
    private void Add(ActionEvent ae) throws ParseException {

        //Validation
        boolean valid = true;
        String error = "";
        if (name.getLength() < 1 || name.getLength() > 255) {
            valid = false;
            error = "Name must be 1-255 characters long.";
        }
        if (valid && (codename.getLength() < 1 || codename.getLength() > 5)) {
            valid = false;
            error = "Codename must be 1-5 characters long.";
        }
        if (valid && (address.getLength() < 1 || address.getLength() > 255)) {
            valid = false;
            error = "Address must be 1-255 characters long.";
        }

        if (valid) {
            
            // CREATE
            JavaBanks bank = new JavaBanks();
            bank.setName(name.getText());
            bank.setCodename(codename.getText());
            bank.setAddress(address.getText());
            Main.em.getTransaction().begin();
            Main.em.persist(bank);
            Main.em.getTransaction().commit();
            
            this.parentController.updateBanks();
            Stage stage = (Stage) name.getScene().getWindow();
            stage.close();

        } else {
            this.error.setText(error);
        }
    }

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
