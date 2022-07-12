package by.bsuir.app.validator;

import by.bsuir.app.exception.IncorrectPasswordException;
import org.junit.Test;

import static org.junit.Assert.*;

public class FormDataValidatorTest {
    private static final int CORRECT_INT = 20;
    private static final int INCORRECT_INT = 102;
    private static final FormDataValidator validator = new FormDataValidator();

    @Test
    public void textCheckCourseFormDataShouldPassTestWhenCorrectTitleLength() {
        validator.checkCourseInputData("Correct title.", "Correct description.",
                CORRECT_INT, CORRECT_INT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void textCheckCourseFormDataShouldThrowExceptionWhenIncorrectTitleLength() {
        validator.checkCourseInputData("Correct title.", "Correct description.",
                INCORRECT_INT, INCORRECT_INT);
    }

    @Test
    public void testCheckPasswordsShouldPassTestWhenEqualsValidPasswords() {
        String password = "12345";
        validator.checkPasswords(password, password);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void testCheckPasswordsShouldThrowExceptionWhenEqualsInvalidPasswords() {
        String password = "12";
        validator.checkPasswords(password, password);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void testCheckPasswordsShouldThrowExceptionWhenNotEqualsValidPasswords() {
        validator.checkPasswords("password", "assword");
    }
}