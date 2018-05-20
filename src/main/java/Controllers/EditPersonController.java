package Controllers;

import Models.PersonCRUD;
import databaseModels.JavaPersons;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Models.Validator;
import java.util.Date;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class EditPersonController implements Initializable {

    private int id;

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
    private void edit(ActionEvent ae) throws ParseException {

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
            
            SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
            Date birthdate = sdf.parse(Integer.parseInt(bd.getText()) + "-" + Integer.parseInt(bm.getText()) + "-" + Integer.parseInt(by.getText()));

            // Update
            PersonCRUD.update(
                    id,
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

    public void setParentController(MainController mainController) {
        this.mainController = mainController;
    }

    public boolean checkExistance(int id) {
        JavaPersons person = Main.em.find(JavaPersons.class, id);
        if (person == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void setData(int id, String name, String surname, String bd, String bm, String by, String address) {
        this.id = id;
        this.name.setText(name);
        this.surname.setText(surname);
        this.bd.setText(bd);
        this.bm.setText(bm);
        this.by.setText(by);
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
            mainController.updatePersons();
        }

    }
}
