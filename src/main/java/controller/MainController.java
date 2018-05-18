package controller;

import dbModel.JavaBanks;
import dbModel.JavaPersons;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BanksTable;
import model.PersonsTable;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class MainController implements Initializable {

    @FXML
    TableView<PersonsTable> personsTable;
    @FXML
    TableColumn<PersonsTable, Integer> ptID;
    @FXML
    TableColumn<PersonsTable, String> ptName;
    @FXML
    TableColumn<PersonsTable, String> ptSurname;
    @FXML
    TableColumn<PersonsTable, String> ptBirthdate;
    @FXML
    TableColumn<PersonsTable, String> ptAddress;

    @FXML
    TableView<BanksTable> banksTable;
    @FXML
    TableColumn<BanksTable, Integer> btID;
    @FXML
    TableColumn<BanksTable, String> btName;
    @FXML
    TableColumn<BanksTable, String> btCodename;
    @FXML
    TableColumn<BanksTable, String> btAddress;

    ObservableList<PersonsTable> personsData = FXCollections.observableArrayList();
    ObservableList<BanksTable> banksData = FXCollections.observableArrayList();

    private final MainController mainController;

    public MainController() {
        mainController = this;
    }

    @FXML
    private void AddPerson(ActionEvent ae) throws ParseException {
        FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("AddPerson.fxml"));

        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        AddPersonController addPersonController = Loader.getController();
        addPersonController.setParentController(mainController);
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setTitle("Add Person");
        stage.show();
    }

    @FXML
    private void AddBank(ActionEvent ae) throws ParseException {
        FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("AddBank.fxml"));

        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        AddBankController addBankController = Loader.getController();
        addBankController.setParentController(mainController);
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setTitle("Add Bank");
        stage.show();
    }

    @FXML
    private void ReloadPersons() throws ParseException {
        updatePersons();
    }

    @FXML
    private void ReloadBanks() throws ParseException {
        updateBanks();
    }

    public void updatePersons() throws ParseException {

        personsTable.getItems().forEach((item) -> {
            personsTable.getItems().clear();
        });

        // READ
        Collection<JavaPersons> results = Main.em.createQuery("SELECT e FROM JavaPersons e").getResultList();
        results.forEach((person) -> {
            SimpleDateFormat formatter = new SimpleDateFormat("d.M.yyyy");
            String birthdate = formatter.format(person.getBirthdate());
            personsData.add(new PersonsTable(person.getId(), person.getName(), person.getSurname(), birthdate, person.getAddress()));
        });
    }

    public void updateBanks() throws ParseException {

        banksTable.getItems().forEach((item) -> {
            banksTable.getItems().clear();
        });

        // READ
        Collection<JavaBanks> results = Main.em.createQuery("SELECT e FROM JavaBanks e").getResultList();
        results.forEach((bank) -> {
            banksData.add(new BanksTable(bank.getId(), bank.getName(), bank.getCodename(), bank.getAddress()));
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ptID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ptName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ptSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        ptBirthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        ptAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        personsTable.setItems(personsData);

        personsTable.setOnMousePressed((MouseEvent event) -> {
            FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("Popup.fxml"));

            try {
                Loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

            PopupController popupController = Loader.getController();
            popupController.setParentController(mainController);
            popupController.setData(
                    "persons",
                    Integer.toString(personsTable.getSelectionModel().getSelectedItem().getId()),
                    personsTable.getSelectionModel().getSelectedItem().getName(),
                    personsTable.getSelectionModel().getSelectedItem().getSurname(),
                    personsTable.getSelectionModel().getSelectedItem().getBirthdate(),
                    personsTable.getSelectionModel().getSelectedItem().getAddress()
            );
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.setTitle("Person");
            stage.show();
        });

        btID.setCellValueFactory(new PropertyValueFactory<>("id"));
        btName.setCellValueFactory(new PropertyValueFactory<>("name"));
        btCodename.setCellValueFactory(new PropertyValueFactory<>("codename"));
        btAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        banksTable.setItems(banksData);

        banksTable.setOnMousePressed((MouseEvent event) -> {
            FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("Popup.fxml"));

            try {
                Loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

            PopupController popupController = Loader.getController();
            popupController.setParentController(mainController);
            popupController.setData(
                    "banks",
                    Integer.toString(banksTable.getSelectionModel().getSelectedItem().getId()),
                    banksTable.getSelectionModel().getSelectedItem().getName(),
                    banksTable.getSelectionModel().getSelectedItem().getCodename(),
                    banksTable.getSelectionModel().getSelectedItem().getAddress(),
                    ""
            );
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.setTitle("Bank");
            stage.show();
        });
    }

}
