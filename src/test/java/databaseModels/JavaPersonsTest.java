package databaseModels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class JavaPersonsTest {

    static JavaPersons javaPersons;

    @BeforeClass
    public static void beforeClass() throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2018-05-22");

        javaPersons = new JavaPersons(1, "David", "Testeroni", date, "Praha, TEstovni 20");
    }

    @AfterClass
    public static void afterClass() {
    }

    @Test
    public void testBirthdateGetter_Valid() {

        // assert
        assertEquals("22.5.2018", javaPersons.getBirthdate());
    }

    @Test
    public void testEquality_Valid() throws ParseException {

        //arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2018-05-22");
        JavaPersons javaPersons2 = new JavaPersons(1, "David", "Testeroni", date, "Praha, TEstovni 20");

        // assert
        assertTrue(javaPersons.equals(javaPersons2));
    }

    @Test
    public void testEquality_Invalid() throws ParseException {

        //arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2018-05-22");

        // assert
        assertFalse(javaPersons.equals(new JavaPersons(2, "David", "Testeroni", date, "Praha, TEstovni 20")));
    }
}
