package by.bsuir.app.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    private static final SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");

    public Date convertFromString(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        return input.parse(date);
    }

    public boolean isDateBeforeCurrent(Date date) {
        Date currentDate = new Date();
        return date.before(currentDate);
    }
}
