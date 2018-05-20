package Models;

import Controllers.Main;
import databaseModels.JavaBanks;
import databaseModels.JavaPersons;
import databaseModels.JavaPersonsBanks;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class PersonBankCRUD {

    public static void create(JavaPersons person, JavaBanks bank) throws Exception {
        JavaPersonsBanks personBank = new JavaPersonsBanks();

        personBank.setPerson(person);
        personBank.setBank(bank);
        personBank.setAssigned(new Date());

        try {

            Main.em.getTransaction().begin();
            Main.em.persist(personBank);
            Main.em.getTransaction().commit();

        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }

    public static JavaPersonsBanks read(int id) throws Exception {

        JavaPersonsBanks row;

        try {
            row = Main.em.find(JavaPersonsBanks.class, id);
        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

        if (row == null) {
            throw new Exception("PersonBank with ID: " + id + " not found.");
        }

        return row;
    }

    public static Collection<JavaPersonsBanks> readAll() throws Exception {

        try {
            return Main.em.createQuery("SELECT e FROM JavaPersonsBanks as e ORDER BY e.id DESC").getResultList();
        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }

    public static void update(int id, JavaPersons person, JavaBanks bank) throws Exception {

        JavaPersonsBanks row = read(id);

        try {

            Main.em.getTransaction().begin();

            row.setPerson(person);
            row.setBank(bank);

            Main.em.getTransaction().commit();

        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }

    public static void delete(int id) throws Exception {

        JavaPersonsBanks row = read(id);

        try {

            Main.em.getTransaction().begin();
            Main.em.remove(row);
            Main.em.getTransaction().commit();

        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }
}
