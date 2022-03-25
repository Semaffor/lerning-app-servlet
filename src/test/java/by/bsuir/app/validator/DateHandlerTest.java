package by.bsuir.app.validator;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

public class DateHandlerTest {

    private static final DateHandler dateHandler = new DateHandler();

    private static final String CORRECT_DATE = "2004-12-12T12:00";
    private static final String DATE_FOR_INSTANT = "2004-12-12T09:00:00.00Z";
    private static final String INCORRECT_DATE = "2004-'T'12:00";

    @Test
    public void testConvertFromStringShouldReturnDateWhenInputValidDate() throws ParseException {
        Instant instant = Instant.parse(DATE_FOR_INSTANT);
        Date date = Date.from(instant);

        Date result = dateHandler.convertFromString(CORRECT_DATE);
        Assert.assertEquals(date, result);
    }

    @Test(expected = ParseException.class)
    public void testConvertFromStringShouldThrowExceptionWhenInputInvalidDate() throws ParseException {
        dateHandler.convertFromString(INCORRECT_DATE);
    }

    @Test
    public void testCheckOnOldDateShouldReturnTrueWhenDateExpiredOnToday() {
        Instant instant = Instant.parse(DATE_FOR_INSTANT);
        Date date = Date.from(instant);
        boolean result = dateHandler.checkOnOldDate(date);
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckOnOldDateShouldReturnFalseWhenDateAfterCurrentDate() {
        Instant instant = Instant.parse("2034-12-12T09:00:00.00Z");
        Date date = Date.from(instant);
        boolean result = dateHandler.checkOnOldDate(date);
        Assert.assertFalse(result);
    }
}