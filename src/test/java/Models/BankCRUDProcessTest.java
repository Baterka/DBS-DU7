package Models;

import Controllers.Main;
import databaseModels.JavaBanks;
import java.text.ParseException;
import java.util.Date;
import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class BankCRUDProcessTest {

    static String name = "Komerční banka testovní";
    static String code = "KBTst";
    static String address = "Praha, Komorany";
    static Date birthdate;

    @Before
    public void setUp() {
        Main main = new Main();
        main.initDatabase();
    }

    @BeforeClass
    public static void beforeClass() {
    }

    @AfterClass
    public static void afterClass() {
    }

    @Test
    public void testCreateRead_Valid() throws Exception {

        // act
        int id = BankCRUD.create(name, code, address);
        JavaBanks bank = BankCRUD.read(id);
        BankCRUD.delete(id);

        // assert
        assertEquals(new JavaBanks(id, name, code, address), bank);
    }

    @Test(expected = Exception.class)
    public void testDelete_Valid() throws Exception {

        //act
        int id = BankCRUD.create(name, code, address);
        BankCRUD.delete(id);
        BankCRUD.read(id);
    }

    @Test
    public void testDelete_Exists() throws Exception {

        // arrange
        boolean result = true;

        //act
        int id = BankCRUD.create(name, code, address);
        try {
            BankCRUD.create(name, code, address);
        } catch (Exception e) {
            result = false;
        }
        BankCRUD.delete(id);

        assertFalse(result);
    }

    @Test
    public void testUpdate_Valid() throws Exception {

        // arrange
        String name2 = "Ceska socialne obecna banka Test";
        String code2 = "CSOBT";
        String address2 = "Brno, U mesice 11";

        // act
        int id = BankCRUD.create(name, code, address);
        BankCRUD.update(id, name2, code2, address2);
        JavaBanks bank = BankCRUD.read(id);
        BankCRUD.delete(id);

        assertEquals(name2, bank.getName());
        assertEquals(code2, bank.getCodename());
        assertEquals(address2, bank.getAddress());
    }
}
