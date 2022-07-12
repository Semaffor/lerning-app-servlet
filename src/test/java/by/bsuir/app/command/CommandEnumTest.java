package by.bsuir.app.command;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandEnumTest {

    @Test
    public void testConvertCommandFromStringShouldConvertWhenCommandExists() {
        CommandEnum result = CommandEnum.convertCommandFromString("items");
        Assert.assertNotNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertCommandFromStringShouldThrowExceptionWhenCommandNotExist() {
        CommandEnum.convertCommandFromString("incorrect");
    }
}