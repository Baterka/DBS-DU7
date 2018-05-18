/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbModel.JavaBanks;
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
public class EditBankController implements Initializable {

    private String type;
    
    private int id;

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
    private void Edit(ActionEvent ae) throws ParseException {

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
            
            // READ
            JavaBanks person = Main.em.find(JavaBanks.class, id);

            // UPDATE
            Main.em.getTransaction().begin();
            person.setName(name.getText());
            person.setCodename(codename.getText());
            person.setAddress(address.getText());
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
    
    public boolean checkExistance(int id) {
        JavaBanks person = Main.em.find(JavaBanks.class, id);
        if (person == null) {
            return true;
        } else {
            return false;
        }
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
}
