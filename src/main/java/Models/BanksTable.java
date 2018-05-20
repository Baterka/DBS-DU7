package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class BanksTable {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty codename;
    private final SimpleStringProperty address;

    public BanksTable(int id, String name, String codename, String address) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.codename = new SimpleStringProperty(codename);
        this.address = new SimpleStringProperty(address);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCodename() {
        return codename.get();
    }

    public void setCodename(String codename) {
        this.codename.set(codename);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

}
