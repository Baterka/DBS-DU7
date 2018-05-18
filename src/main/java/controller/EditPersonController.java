/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbModel.JavaPersons;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.PersonsTable;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class EditPersonController implements Initializable {

    private String type;

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

    private MainController parentController;

    @FXML
    private void Edit(ActionEvent ae) throws ParseException {

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

            // READ
            JavaPersons person = Main.em.find(JavaPersons.class, id);

            // UPDATE
            Main.em.getTransaction().begin();
            person.setName(name.getText());
            person.setSurname(surname.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
            person.setBirthdate(sdf.parse(Integer.parseInt(bd.getText()) + "-" + Integer.parseInt(bm.getText()) + "-" + Integer.parseInt(by.getText())));
            person.setAddress(address.getText());
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
}
