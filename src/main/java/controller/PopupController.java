package controller;

import dbModel.JavaBanks;
import dbModel.JavaPersons;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class PopupController implements Initializable {

    private String type;

    @FXML
    Text text1;
    @FXML
    Text text2;
    @FXML
    Text text3;
    @FXML
    Text text4;
    @FXML
    Text text5;
    @FXML
    Button btnClose;
    @FXML
    Button btnDelete;
    @FXML
    Button btnEdit;
    @FXML
    Text error;

    private MainController parentController;

    @FXML
    private void Close(ActionEvent ae) {
        Stage stage = (Stage) text1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Delete(ActionEvent ae) {
        try {
            Object row = null;

            switch (type) {
                case "persons":
                    row = Main.em.find(JavaPersons.class, Integer.parseInt(text1.getText()));
                    break;
                case "banks":
                    row = Main.em.find(JavaBanks.class, Integer.parseInt(text1.getText()));
                    break;
            }

            if (row == null) {
                throw new Exception("Row in '" + type + "' with ID: " + text1.getText() + " not found.");
            }
            Main.em.getTransaction().begin();
            Main.em.remove(row);
            Main.em.getTransaction().commit();

            switch (type) {
                case "persons":
                    parentController.updatePersons();
                    break;
                case "banks":
                    parentController.updateBanks();
                    break;
            }

        } catch (Exception e) {
            FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("Error.fxml"));

            try {
                Loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ErrorController errorController = Loader.getController();
            errorController.setData(
                    "Delete failed",
                    e.getMessage()
            );
            Stage stage = new Stage();
            stage.setScene(new Scene((AnchorPane) Loader.getRoot()));
            stage.show();
            return;
        }
        this.Close(new ActionEvent());

    }

    @FXML
    private void Edit(ActionEvent ae) {
        FXMLLoader Loader = null;

        Stage stage = new Stage();
        switch (type) {
            case "persons":
                Loader = new FXMLLoader(getClass().getClassLoader().getResource("EditPerson.fxml"));

                try {
                    Loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }

                EditPersonController editPersonController = Loader.getController();
                editPersonController.setParentController(parentController);
                String[] birthdate = text4.getText().split("\\.");
                editPersonController.setData(
                        Integer.parseInt(text1.getText()),
                        text2.getText(),
                        text3.getText(),
                        birthdate[0],
                        birthdate[1],
                        birthdate[2],
                        text5.getText()
                );

                stage.setTitle("Edit Person");
                break;
            case "banks":
                Loader = new FXMLLoader(getClass().getClassLoader().getResource("EditBank.fxml"));

                try {
                    Loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }

                EditBankController editBankController = Loader.getController();
                editBankController.setParentController(parentController);
                editBankController.setData(
                        Integer.parseInt(text1.getText()),
                        text2.getText(),
                        text3.getText(),
                        text4.getText()
                );

                stage.setTitle("Edit Bank");
                break;
        }
        Parent p = Loader.getRoot();
        stage.setScene(new Scene(p));
        stage.show();
        this.Close(new ActionEvent());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setData(String type, String text1, String text2, String text3, String text4, String text5) {
        this.type = type;
        this.text1.setText(text1);
        this.text2.setText(text2);
        this.text3.setText(text3);
        this.text4.setText(text4);
        this.text5.setText(text5);
    }

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }
}
