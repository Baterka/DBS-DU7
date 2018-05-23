package Models;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class ErrorWindowTest {

    @BeforeClass
    public static void beforeClass() {
    }

    @AfterClass
    public static void afterClass() {
    }

    @Test
    public void testDateFormatting_Valid() throws Exception {

        // act
        ErrorWindow ew = new ErrorWindow("Hello", "I am test text");

        // assert
        assertEquals("Hello", ew.getTitle());
        assertEquals("I am test text", ew.getMessage());
    }
}
