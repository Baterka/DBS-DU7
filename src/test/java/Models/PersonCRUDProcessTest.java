package Models;

import Controllers.Main;
import databaseModels.JavaPersons;
import java.text.ParseException;
import java.util.Date;
import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class PersonCRUDProcessTest {

    static String name = "Karel";
    static String surname = "Malý";
    static int bd = 1;
    static int bm = 2;
    static int by = 1997;
    static String address = "Praha, Komorany";
    static Date birthdate;

    @Before
    public void setUp() {
        Main main = new Main();
        main.initDatabase();
    }

    @BeforeClass
    public static void beforeClass() throws ParseException, Exception {
        birthdate = Formatter.formatDate(bd, bm, by);
    }

    @AfterClass
    public static void afterClass() {
    }

    @Test
    public void testCreateRead_Valid() throws Exception {

        // act
        int id = PersonCRUD.create(name, surname, birthdate, address);
        JavaPersons person = PersonCRUD.read(id);
        PersonCRUD.delete(id);

        // assert
        assertEquals(new JavaPersons(id, name, surname, birthdate, address), person);
    }

    @Test(expected = Exception.class)
    public void testDelete_Valid() throws Exception {

        //act
        int id = PersonCRUD.create(name, surname, birthdate, address);
        PersonCRUD.delete(id);
        PersonCRUD.read(id);
    }
    
    @Test
    public void testUpdate_Valid() throws Exception {

        // arrange
        String name2 = "Pepa";
        String surname2 = "Kohak";
        String address2 = "Brno, U mesice 11";
        Date birthdate2 = Formatter.formatDate(1, 2, 1999);

        // act
        int id = PersonCRUD.create(name, surname, birthdate, address);
        PersonCRUD.update(id, name2, surname2, birthdate2, address2);
        JavaPersons person = PersonCRUD.read(id);
        PersonCRUD.delete(id);

        assertEquals(name2, person.getName());
        assertEquals(surname2, person.getSurname());
        assertEquals(address2, person.getAddress());
        assertEquals(Formatter.getFormattedDate(birthdate2), person.getBirthdate());
    }
}
