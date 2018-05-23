package Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class FormatterTest {

    @BeforeClass
    public static void beforeClass() {
    }

    @AfterClass
    public static void afterClass() {
    }

    @Test
    public void testDateFormatting_Valid() throws Exception {

        // arrange
        int bd = 1;
        int bm = 2;
        int by = 1997;

        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
        Date expected = sdf.parse(bd + "-" + bm + "-" + by);

        // act
        Date returned = Formatter.formatDate(bd, bm, by);

        // assert
        assertEquals(expected, returned);
    }
    
    @Test
    public void testGetFormattedDate_Valid() throws Exception {

        // arrange
        int bd = 1;
        int bm = 2;
        int by = 1997;

        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
        Date date = sdf.parse(bd + "-" + bm + "-" + by);
        String expected = bd + "." + bm + "." + by;

        // act
        String returned = Formatter.getFormattedDate(date);

        // assert
        assertEquals(expected, returned);
    }

    @Test(expected = Exception.class)
    public void testDateFormatting_ParseException() throws Exception {

        // arrange
        int bd = 50;
        int bm = 5;
        int by = 1990;

        // act
        Formatter.formatDate(bd, bm, by);
    }
}
