package by.bsuir.app.encription;

import org.junit.Assert;
import org.junit.Test;

public class EncryptorMd5Test {

    private static final EncryptorMd5 md5 = new EncryptorMd5();
    private static final String PASSWORD_ENCRYPTED = "827ccb0eea8a706c4c34a16891f84e7b";
    private static final String PASSWORD = "12345";

    @Test
    public void testEncryptShouldReturnTrueWhenCorrectEncrypted() {
        String result = md5.encrypt(PASSWORD);
        Assert.assertEquals(PASSWORD_ENCRYPTED, result);
    }

    @Test
    public void testEncryptShouldReturnFalseWhenIncorrectEncrypted() {
        String result = md5.encrypt(PASSWORD + "2");
        Assert.assertNotEquals(PASSWORD_ENCRYPTED, result);
    }
}