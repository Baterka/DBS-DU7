package Models;

import Controllers.Main;
import databaseModels.JavaPersons;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class PersonCRUD {

    public static void create(String name, String surname, Date birthdate, String address) throws Exception {

        JavaPersons person = new JavaPersons();

        person.setName(name);
        person.setSurname(surname);
        person.setBirthdate(birthdate);
        person.setAddress(address);

        try {

            Main.em.getTransaction().begin();
            Main.em.persist(person);
            Main.em.getTransaction().commit();

        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }

    public static JavaPersons read(int id) throws Exception {

        JavaPersons row;

        try {
            row = Main.em.find(JavaPersons.class, id);
        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

        if (row == null) {
            throw new Exception("Person with ID: " + id + " not found.");
        }

        return row;
    }

    public static Collection<JavaPersons> readAll() throws Exception {

        try {
            return Main.em.createQuery("SELECT e FROM JavaPersons as e ORDER BY e.id DESC").getResultList();
        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }

    public static void update(int id, String name, String surname, Date birthdate, String address) throws Exception {

        JavaPersons row = read(id);

        try {

            Main.em.getTransaction().begin();

            row.setName(name);
            row.setSurname(surname);
            row.setBirthdate(birthdate);
            row.setAddress(address);

            Main.em.getTransaction().commit();

        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }

    public static void delete(int id) throws Exception {

        JavaPersons row = read(id);

        try {

            Main.em.getTransaction().begin();
            Main.em.remove(row);
            Main.em.getTransaction().commit();

        } catch (Exception e) {
            throw new Exception(DatabaseErrors.getMessage(e));
        }

    }
}
