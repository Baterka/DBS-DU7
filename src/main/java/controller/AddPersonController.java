package controller;

import dbModel.JavaPersons;
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

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class AddPersonController implements Initializable {

    private String type;

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
        if (valid && (surname.getLength() < 1 || surname.getLength() > 255)) {
            valid = false;
            error = "Surname must be 1-255 characters long.";
        }
        if (valid && (address.getLength() < 1 || address.getLength() > 255)) {
            valid = false;
            error = "Address must be 1-255 characters long.";
        }

        try {
            if (valid && (Integer.parseInt(bd.getText()) < 1 || Integer.parseInt(bd.getText()) > 31)) {
                valid = false;
                error = "Birthdate day is in bad format.";
            }
            if (valid && (Integer.parseInt(bm.getText()) < 1 || Integer.parseInt(bm.getText()) > 12)) {
                valid = false;
                error = "Birthdate month is in bad format.";
            }
            if (valid && (Integer.parseInt(by.getText()) < 1970 || Integer.parseInt(by.getText()) > 2018)) {
                valid = false;
                error = "Birthdate year is in bad format.";
            }
        } catch (NumberFormatException e) {
            valid = false;
            error = "Birthdate must be integers.";
        }

        if (valid) {
            
            // CREATE
            JavaPersons person = new JavaPersons();
            person.setName(name.getText());
            person.setSurname(surname.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
            person.setBirthdate(sdf.parse(Integer.parseInt(bd.getText()) + "-" + Integer.parseInt(bm.getText()) + "-" + Integer.parseInt(by.getText())));
            person.setAddress(address.getText());
            Main.em.getTransaction().begin();
            Main.em.persist(person);
            Main.em.getTransaction().commit();
            
            this.parentController.updatePersons();
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
