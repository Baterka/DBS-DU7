package Models;

import org.postgresql.util.PSQLException;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class DatabaseErrors {
    
    public static String getMessage(Exception e) {
        Throwable throwable = e;
        while (throwable != null && !(throwable instanceof PSQLException)) {
            throwable = throwable.getCause();
        }
        
        // If there is PSQLException, then return message of it
        if (throwable instanceof PSQLException) {
            PSQLException pe = (PSQLException) throwable;
            return pe.getMessage();
        }
        
        // If there is not PSQLException, then return message of parent Exception
        return e.getMessage();
    }
}
