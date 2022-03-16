package by.bsuir.app.validator;

import java.util.Date;

public class DataValidator {
    private static final int MAX_TITLE_LENGTH = 90;
    private static final int MAX_DESCRIPTION_LENGTH = 255;
    private static final int MAX_DURATION = 100;
    private static final int MAX_MAX_PUPILS = 100;
    private static final int MIN_MARK = 1;
    private static final int MAX_MARK = 10;

    public void checkCourseInputData(String title, String description, int duration, int pupilsCount) {
        checkTitle(title);
        checkDescription(description);
        checkDuration(duration);
        checkPupilsQuantity(pupilsCount);
    }

    public void checkTaskInputData(String title, String description, Date date) {
        checkTitle(title);
        checkDescription(description);
        checkDate(date);
    }

    public void checkTitle(String title) {
        if (checkStringLength(title, MAX_TITLE_LENGTH)) {
            throw new IllegalArgumentException("Title over the prescribed length or empty.");
        }

    }

    public void checkDescription(String description) {
        if (checkStringLength(description, MAX_DESCRIPTION_LENGTH)) {
            throw new IllegalArgumentException("Description over the prescribed length or empty.");
        }
    }

    public void checkDate(Date date) {
        DateHandler dateHandler = new DateHandler();
        if (dateHandler.checkOnOldDate(date)) {
            throw new IllegalArgumentException("Incorrect date.");
        }
    }

    public void checkDuration(int duration) {
        if (duration > MAX_DURATION) {
            throw new IllegalArgumentException("Duration isn't correct.");
        }
    }

    public void checkMark(int mark) {
        if (mark > MAX_MARK  || mark < MIN_MARK ) {
            throw new IllegalArgumentException("Mark is out of range 1 to 10.");
        }
    }

    public void checkPupilsQuantity(int maxPupilsQuantity) {
        if (maxPupilsQuantity > MAX_MAX_PUPILS) {
            throw new IllegalArgumentException("Too many pupils.");
        }
    }

    private boolean checkStringLength(String field, int maxLength) {
        return field.isEmpty() || field.length() > maxLength;
    }
}
