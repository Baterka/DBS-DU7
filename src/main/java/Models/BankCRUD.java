package Models;

import Controllers.Main;
import databaseModels.JavaBanks;
import java.util.Collection;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class BankCRUD {

    public static int create(String name, String codename, String address) throws Exception {

        JavaBanks bank = new JavaBanks();

        bank.setName(name);
        bank.setCodename(codename);
        bank.setAddress(address);

        try {
            Main.em.getTransaction().begin();
            Main.em.persist(bank);
            Main.em.getTransaction().commit();
            return bank.getId();
        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }

    public static JavaBanks read(int id) throws Exception {

        JavaBanks row;

        try {
            row = Main.em.find(JavaBanks.class, id);
        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

        if (row == null) {
            throw new Exception("Bank with ID: " + id + " not found.");
        }

        return row;

    }

    public static Collection<JavaBanks> readAll() throws Exception {

        try {
            return Main.em.createQuery("SELECT e FROM JavaBanks as e ORDER BY e.id DESC").getResultList();
        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }

    public static void update(int id, String name, String codename, String address) throws Exception {

        JavaBanks row = read(id);

        try {

            Main.em.getTransaction().begin();

            row.setName(name);
            row.setCodename(codename);
            row.setAddress(address);

            Main.em.getTransaction().commit();

        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }

    public static void delete(int id) throws Exception {

        JavaBanks row = read(id);

        try {

            Main.em.getTransaction().begin();
            Main.em.remove(row);
            Main.em.getTransaction().commit();

        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }
}
