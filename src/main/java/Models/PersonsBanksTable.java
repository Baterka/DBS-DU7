package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class PersonsBanksTable {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty person;
    private final SimpleStringProperty bank;
    private final SimpleStringProperty assigned;

    public PersonsBanksTable(int id, String person, String bank, String assigned) {
        this.id = new SimpleIntegerProperty(id);
        this.person = new SimpleStringProperty(person);
        this.bank = new SimpleStringProperty(bank);
        this.assigned = new SimpleStringProperty(assigned);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getPerson() {
        return person.get();
    }

    public void setPerson(String person) {
        this.person.set(person);
    }

    public String getBank() {
        return bank.get();
    }

    public void setBank(String bank) {
        this.bank.set(bank);
    }

    public String getAssigned() {
        return assigned.get();
    }

    public void setAssigned(String assigned) {
        this.assigned.set(assigned);
    }

}
