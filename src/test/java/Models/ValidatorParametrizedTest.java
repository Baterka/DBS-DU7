package Models;

import java.util.Arrays;
import java.util.Collection;
import org.junit.*;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author David Bubeník (bubenda1)
 */
@RunWith(Parameterized.class)
public class ValidatorParametrizedTest {

    static Validator validator;

    static String nameIn;
    static String surnameIn;
    static String bdIn;
    static String bmIn;
    static String byIn;
    static String addressIn;
    static boolean expected;

    @BeforeClass
    public static void beforeClass() {
        validator = new Validator();
    }

    public ValidatorParametrizedTest(String nameIn, String surnameIn, String bdIn, String bmIn, String byIn, String addressIn, boolean expected) {
        this.nameIn = nameIn;
        this.surnameIn = surnameIn;
        this.bdIn = bdIn;
        this.bmIn = bmIn;
        this.byIn = byIn;
        this.addressIn = addressIn;
        this.expected = expected;
    }

    @Test
    public void testValidatePersonForm() throws Exception {

        // arrange
        boolean result;

        // act
        try {
            result = validator.validatePersonForm(nameIn, surnameIn, bdIn, bmIn, byIn, addressIn);
        } catch (Exception e) {
            result = false;
        }

        // assert
        assertEquals(expected, result);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            // Valid
            {"Karel", "Volný", "1", "3", "1997", "Praha, Polni 29", true},
            
            // Empty error
            {"", "Volný", "1", "3", "1997", "Praha, Polni 29", false},
            {"Karel", "", "1", "3", "1997", "Praha, Polni 29", false},
            {"Karel", "Volný", "", "3", "1997", "Praha, Polni 29", false},
            {"Karel", "Volný", "1", "", "1997", "Praha, Polni 29", false},
            {"Karel", "Volný", "1", "3", "", "Praha, Polni 29", false},
            {"Karel", "Volný", "1", "3", "1997", "", false},
            
            // Format error
            // Min
            {"Karel", "Volný", "0", "3", "1997", "Praha, Polni 29", false},
            {"Karel", "Volný", "1", "0", "1997", "Praha, Polni 29", false},
            {"Karel", "Volný", "1", "3", "1899", "Praha, Polni 29", false},
            // Max
            {"Karel", "Volný", "32", "3", "1997", "Praha, Polni 29", false},
            {"Karel", "Volný", "1", "13", "1997", "Praha, Polni 29", false},
            {"Karel", "Volný", "1", "3", "2019", "Praha, Polni 29", false}
        });
    }
}
