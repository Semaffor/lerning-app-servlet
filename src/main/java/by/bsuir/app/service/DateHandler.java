package by.bsuir.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {
    private static final SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
    private final Logger LOGGER  = LoggerFactory.getLogger(this.getClass());

    public Date convertFromString(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        return input.parse(date);
    }

    public boolean checkOnOldDate(Date date) {
        Date currentDate = new Date();
        return date.before(currentDate);
    }

    public Date changeDateFormat(Date oldDateFormat) throws ParseException {
        if (oldDateFormat == null) {
            return null;
        }
        String newDate = output.format(oldDateFormat);
        return output.parse(newDate);
    }
}
