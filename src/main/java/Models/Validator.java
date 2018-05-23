package Models;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class Validator {

    public boolean validatePersonForm(String name, String surname, String birthdateDay, String birthdateMonth, String birthYear, String address) throws Exception {

        if (name.length() < 1 || name.length() > 255) {
            throw new Exception("Name must be 1-255 characters long.");
        }
        if (surname.length() < 1 || surname.length() > 255) {
            throw new Exception("Surname must be 1-255 characters long.");
        }
        if (address.length() < 1 || address.length() > 255) {
            throw new Exception("Address must be 1-255 characters long.");
        }

        int d;
        int m;
        int y;
        try {
            d = Integer.parseInt(birthdateDay);
            m = Integer.parseInt(birthdateMonth);
            y = Integer.parseInt(birthYear);
        } catch (NumberFormatException e) {
            throw new Exception("Birthdate must be integers.");
        }

        if (d < 1 || d > 31) {
            throw new Exception("Birthdate day is in bad format.");
        }
        if (m < 1 || m > 12) {
            throw new Exception("Birthdate month is in bad format.");
        }
        if (y < 1900 || y > 2018) {
            throw new Exception("Birthdate year is in bad format.");
        }
        
        return true;

    }

    public boolean validateBankForm(String name, String codename, String address) throws Exception {

        if (name.length() < 1 || name.length() > 255) {
            throw new Exception("Name must be 1-255 characters long.");
        }
        if (codename.length() < 1 || codename.length() > 5) {
            throw new Exception("Codename must be 1-5 characters long.");
        }
        if (address.length() < 1 || address.length() > 255) {
            throw new Exception("Address must be 1-255 characters long.");
        }

        return true;
    }
}
