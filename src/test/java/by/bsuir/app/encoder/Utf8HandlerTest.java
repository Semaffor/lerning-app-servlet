package by.bsuir.app.encoder;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

public class Utf8HandlerTest {

    private static final String ATTRIBUTE_NAME = "attr";
    private static final String ATTRIBUTE = "Дима";
    private static final Utf8Handler handler = new Utf8Handler();

    private final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);

    @Test
    public void testReEncodeShouldReturnTrueWhenCorrectEncoded() {
        String attribute = new String(ATTRIBUTE.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        Mockito.when(httpServletRequest.getParameter(ATTRIBUTE_NAME)).thenReturn(attribute);
        String result = handler.reEncode(httpServletRequest, ATTRIBUTE_NAME);
        Assert.assertEquals(ATTRIBUTE, result);
    }

    @Test
    public void testReEncodeShouldReturnFalseWhenIncorrectEncoded() {
        String attribute = new String(ATTRIBUTE.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        Mockito.when(httpServletRequest.getParameter(ATTRIBUTE_NAME)).thenReturn(attribute);
        String result = handler.reEncode(httpServletRequest, ATTRIBUTE_NAME);
        Assert.assertNotEquals(ATTRIBUTE, result);
    }
}