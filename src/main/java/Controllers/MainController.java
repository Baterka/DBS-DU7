package Controllers;

import Enums.PopupType;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import Models.BankCRUD;
import Models.BanksTable;
import Models.PersonBankCRUD;
import Models.PersonsBanksTable;
import Models.PersonCRUD;
import Models.PersonsTable;
import databaseModels.JavaBanks;
import databaseModels.JavaPersons;
import javafx.scene.text.Text;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class MainController implements Initializable {

    // Persons TAB
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

    // Banks TAB
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

    // Persons in Banks TAB
    @FXML
    TableView<PersonsBanksTable> personsBanksTable;
    @FXML
    TableColumn<BanksTable, Integer> pbtID;
    @FXML
    TableColumn<BanksTable, String> pbtPerson;
    @FXML
    TableColumn<BanksTable, String> pbtBank;
    @FXML
    TableColumn<BanksTable, String> pbtAssigned;

    @FXML
    Text error;

    ObservableList<PersonsTable> personsTableData = FXCollections.observableArrayList();
    ObservableList<BanksTable> banksTableData = FXCollections.observableArrayList();
    ObservableList<PersonsBanksTable> personsBanksTableData = FXCollections.observableArrayList();

    private final MainController mainController;

    private final Logger LOG = Logger.getLogger(getClass().getName());

    public MainController() {
        this.mainController = this;
    }

    @FXML
    private void addPerson(ActionEvent ae) throws ParseException {

        try {

            FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("AddPerson.fxml"));
            Loader.load();

            AddPersonController addPersonController = Loader.getController();
            addPersonController.setMainController(mainController);

            Stage stage = new Stage();
            stage.setScene(new Scene(Loader.getRoot()));
            stage.setTitle("Add Person");
            stage.show();

        } catch (Exception e) {

            LOG.warning(e.toString());

        }

    }

    @FXML
    private void addBank(ActionEvent ae) throws ParseException {

        try {

            FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("AddBank.fxml"));
            Loader.load();

            AddBankController addBankController = Loader.getController();
            addBankController.setMainController(mainController);

            Stage stage = new Stage();
            stage.setScene(new Scene(Loader.getRoot()));
            stage.setTitle("Add Bank");
            stage.show();

        } catch (Exception e) {

            LOG.warning(e.toString());

        }

    }

    @FXML
    private void addPersonBank(ActionEvent ae) {

        try {

            FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("AddPersonBank.fxml"));
            Loader.load();

            AddPersonBankController addPersonBankController = Loader.getController();
            addPersonBankController.setMainController(mainController);

            Stage stage = new Stage();
            stage.setScene(new Scene(Loader.getRoot()));
            stage.setTitle("Add PersonBank");
            stage.show();

        } catch (Exception e) {

            LOG.warning(e.toString());

        }
    }

    @FXML
    private void reloadPersons() {
        updatePersons();
    }

    @FXML
    private void reloadBanks() {
        updateBanks();
    }

    @FXML
    private void reloadPersonsBanks() {
        updatePersonsBanks();
    }

    public void updatePersons() {

        // Clear
        personsTableData.clear();

        try {

            // Read All
            PersonCRUD.readAll().forEach((person) -> {
                personsTableData.add(
                        new PersonsTable(
                                person.getId(),
                                person.getName(),
                                person.getSurname(),
                                person.getBirthdate(),
                                person.getAddress()
                        )
                );
            });

        } catch (Exception e) {
            this.error.setText("Error occured while fetching Persons");
        }

    }

    public void updateBanks() {

        // Clear
        banksTableData.clear();

        try {

            // Read All
            BankCRUD.readAll().forEach((bank) -> {
                banksTableData.add(
                        new BanksTable(
                                bank.getId(),
                                bank.getName(),
                                bank.getCodename(),
                                bank.getAddress()
                        )
                );
            });

        } catch (Exception e) {
            this.error.setText("Error occured while fetching Banks");
        }

    }

    public void updatePersonsBanks() {

        // Clear
        personsBanksTableData.clear();

        try {

            // Read All
            PersonBankCRUD.readAll().forEach((row) -> {

                JavaPersons person = row.getPerson();
                JavaBanks bank = row.getBank();

                personsBanksTableData.add(
                        new PersonsBanksTable(
                                row.getId(),
                                person.getName() + " " + person.getSurname(),
                                bank.getName(),
                                row.getAssigned()
                        )
                );
            });

        } catch (Exception e) {
            this.error.setText("Error occured while fetching PersonsBanks");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializePersonsTable();
        initializeBanksTable();
        initializePersonsBanksTable();

        updateBanks();
        updatePersons();
        updatePersonsBanks();

    }

    private void initializePersonsTable() {

        ptID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ptName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ptSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        ptBirthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        ptAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        personsTable.setItems(personsTableData);

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
                    PopupType.Persons,
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

    }

    private void initializeBanksTable() {

        btID.setCellValueFactory(new PropertyValueFactory<>("id"));
        btName.setCellValueFactory(new PropertyValueFactory<>("name"));
        btCodename.setCellValueFactory(new PropertyValueFactory<>("codename"));
        btAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        banksTable.setItems(banksTableData);

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
                    PopupType.Banks,
                    Integer.toString(banksTable.getSelectionModel().getSelectedItem().getId()),
                    banksTable.getSelectionModel().getSelectedItem().getName(),
                    banksTable.getSelectionModel().getSelectedItem().getCodename(),
                    banksTable.getSelectionModel().getSelectedItem().getAddress()
            );
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.setTitle("Bank");
            stage.show();
        });

    }

    private void initializePersonsBanksTable() {

        pbtID.setCellValueFactory(new PropertyValueFactory<>("id"));
        pbtPerson.setCellValueFactory(new PropertyValueFactory<>("person"));
        pbtBank.setCellValueFactory(new PropertyValueFactory<>("bank"));
        pbtAssigned.setCellValueFactory(new PropertyValueFactory<>("assigned"));

        personsBanksTable.setItems(personsBanksTableData);

        personsBanksTable.setOnMousePressed((MouseEvent event) -> {
            FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("Popup.fxml"));

            try {
                Loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

            PopupController popupController = Loader.getController();
            popupController.setParentController(mainController);
            popupController.setData(
                    PopupType.PersonsBanks,
                    Integer.toString(personsBanksTable.getSelectionModel().getSelectedItem().getId()),
                    personsBanksTable.getSelectionModel().getSelectedItem().getPerson(),
                    personsBanksTable.getSelectionModel().getSelectedItem().getBank(),
                    personsBanksTable.getSelectionModel().getSelectedItem().getAssigned()
            );
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.setTitle("Bank");
            stage.show();
        });

    }

}
