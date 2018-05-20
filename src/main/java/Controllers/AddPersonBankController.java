package Controllers;

import Models.BankCRUD;
import Models.PersonBankCRUD;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Models.PersonCRUD;
import Models.Validator;
import databaseModels.JavaBanks;
import databaseModels.JavaPersons;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Pair;
import javafx.util.StringConverter;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class AddPersonBankController implements Initializable {

    @FXML
    ComboBox<KeyValuePair> personsCombo;
    @FXML
    ComboBox<KeyValuePair> banksCombo;
    @FXML
    Text error;

    private MainController mainController;

    @FXML
    private void add(ActionEvent ae) {

        Validator validator = new Validator();
        try {

            // Validate
            // Not implemented because it is not possible to insert bad values.
            
            // Create
            PersonBankCRUD.create((JavaPersons) personsCombo.getValue().getValue(),
                    (JavaBanks) banksCombo.getValue().getValue()
            );
            
            callUpdate();
            closeWindow();

        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void setMainController(MainController parentController) {
        this.mainController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populatePersonsCombo();
        populateBanksCombo();
    }

    private void populatePersonsCombo() {
        ObservableList<KeyValuePair> personOptions = FXCollections.observableArrayList();

        try {

            Collection<JavaPersons> persons = PersonCRUD.readAll();

            for (JavaPersons person : persons) {
                personOptions.add(new KeyValuePair(person.getName() + " " + person.getSurname(), person));
            }

            personsCombo.setItems(personOptions);

            // Show the correct text
            personsCombo.setCellFactory((ListView<KeyValuePair> param) -> {
                final ListCell<KeyValuePair> cell = new ListCell<KeyValuePair>() {

                    @Override
                    protected void updateItem(KeyValuePair t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(String.valueOf(t.getKey()));
                        } else {
                            setText(null);
                        }
                    }

                };
                return cell;
            });

            personsCombo.setConverter(new StringConverter<KeyValuePair>() {
                @Override
                public String toString(KeyValuePair object) {
                    return object.getKey();
                }

                @Override
                public KeyValuePair fromString(String string) {
                    return null;
                }
            });

        } catch (Exception e) {
            this.error.setText(e.getMessage());
        }
    }

    private void populateBanksCombo() {
        ObservableList<KeyValuePair> bankOptions = FXCollections.observableArrayList();

        try {

            Collection<JavaBanks> banks = BankCRUD.readAll();

            for (JavaBanks bank : banks) {
                bankOptions.add(new KeyValuePair(bank.getName(), bank));
            }

            banksCombo.setItems(bankOptions);

            // Show the correct text
            banksCombo.setCellFactory((ListView<KeyValuePair> param) -> {
                final ListCell<KeyValuePair> cell = new ListCell<KeyValuePair>() {

                    @Override
                    protected void updateItem(KeyValuePair t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(String.valueOf(t.getKey()));
                        } else {
                            setText(null);
                        }
                    }

                };
                return cell;
            });

            banksCombo.setConverter(new StringConverter<KeyValuePair>() {
                @Override
                public String toString(KeyValuePair object) {
                    return object.getKey();
                }

                @Override
                public KeyValuePair fromString(String string) {
                    return null;
                }
            });

        } catch (Exception e) {
            this.error.setText(e.getMessage());
        }
    }

    private void closeWindow() {

        Stage stage = (Stage) personsCombo.getScene().getWindow();
        stage.close();

    }

    private void callUpdate() {

        if (mainController != null) {
            mainController.updatePersonsBanks();
        }

    }

    class KeyValuePair {

        private final String key;
        private final Object value;

        public KeyValuePair(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }
}
