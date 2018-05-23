package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class Formatter {

    public static Date formatDate(int bd, int bm, int by) throws Exception {
        if (bd < 1 || bd > 31) {
            throw new Exception("Out of range");
        }

        if (bm < 1 || bm > 12) {
            throw new Exception("Out of range");
        }

        if (by < 1) {
            throw new Exception("Out of range");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
        return sdf.parse(bd + "-" + bm + "-" + by);
    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("d.M.yyyy");
        return formatter.format(date);
    }
}
