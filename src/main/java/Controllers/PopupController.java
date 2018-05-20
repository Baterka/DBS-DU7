package Controllers;

import Enums.PopupType;
import Models.BankCRUD;
import Models.ErrorWindow;
import Models.PersonBankCRUD;
import Models.PersonCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class PopupController implements Initializable {

    private PopupType popupType;

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

    private MainController mainController;

    private final Logger LOG = Logger.getLogger(getClass().getName());

    @FXML
    private void close(ActionEvent ae) {
        closeWindow();
    }

    @FXML
    private void delete(ActionEvent ae) {
        try {
            Object row = null;
            int id = Integer.parseInt(text1.getText());

            switch (popupType) {
                case Persons:
                    PersonCRUD.delete(id);
                    break;
                case Banks:
                    BankCRUD.delete(id);
                    break;
                case PersonsBanks:
                    PersonBankCRUD.delete(id);
                    break;
            }

            callUpdate();
            closeWindow();

        } catch (Exception e) {
            new ErrorWindow(
                    "Deletion failed",
                    e.getMessage()
            ).open();
        }

    }

    @FXML
    private void edit(ActionEvent ae) {

        try {
            switch (popupType) {
                case Persons:
                    editPerson();
                    break;
                case Banks:
                    editBank();
                    break;
                case PersonsBanks:
                    // NOT IMPLEMENTED
                    break;
            }

            closeWindow();

        } catch (Exception e) {
            LOG.warning(e.toString());
        }

    }

    public void setData(PopupType popupType, String text1, String text2, String text3, String text4) {
        
        this.popupType = popupType;
        this.text1.setText(text1);
        this.text2.setText(text2);
        this.text3.setText(text3);
        this.text4.setText(text4);
        hideEditButton();
        
    }
    
    public void setData(PopupType popupType, String text1, String text2, String text3, String text4, String text5) {
        
        this.popupType = popupType;
        this.text1.setText(text1);
        this.text2.setText(text2);
        this.text3.setText(text3);
        this.text4.setText(text4);
        this.text5.setText(text5);
        hideEditButton();
        
    }

    public void setParentController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    private void editPerson() throws Exception {

        FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("EditPerson.fxml"));
        Loader.load();

        EditPersonController editPersonController = Loader.getController();
        editPersonController.setParentController(mainController);
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

        Stage stage = new Stage();
        stage.setTitle("Edit Person");
        stage.setScene(new Scene(Loader.getRoot()));
        stage.show();
    }

    private void editBank() throws Exception {

        FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("EditBank.fxml"));
        Loader.load();

        EditBankController editBankController = Loader.getController();
        editBankController.setParentController(mainController);
        editBankController.setData(
                Integer.parseInt(text1.getText()),
                text2.getText(),
                text3.getText(),
                text4.getText()
        );

        Stage stage = new Stage();
        stage.setTitle("Edit Bank");
        stage.setScene(new Scene(Loader.getRoot()));
        stage.show();
    }

    private void closeWindow() {

        Stage stage = (Stage) text1.getScene().getWindow();
        stage.close();

    }

    private void callUpdate() {

        if (mainController != null) {

            switch (popupType) {
                case Persons:
                    mainController.updatePersons();
                    break;
                case Banks:
                    mainController.updateBanks();
                    break;
                case PersonsBanks:
                    mainController.updatePersonsBanks();
                    break;
            }

        }

    }
    
    private void hideEditButton(){
        if(popupType == PopupType.PersonsBanks)
            btnEdit.setVisible(false);
    }
}
