package by.bsuir.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {
    private static final SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    private static final SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public Date convertFromString(String date) throws ParseException {
        return input.parse(date);
    }

    public boolean checkOnOldDate(Date date) {
        Date currentDate = new Date();
        return date.before(currentDate);
    }
}
