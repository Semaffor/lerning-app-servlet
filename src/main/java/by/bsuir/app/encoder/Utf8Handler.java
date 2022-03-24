package by.bsuir.app.encoder;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

public class Utf8Handler implements CustomEncoder {
    @Override
    public String reEncode(HttpServletRequest request, String attributeName) {
        return new String(request.getParameter(attributeName).getBytes(StandardCharsets.ISO_8859_1),
                StandardCharsets.UTF_8);
    }
}
