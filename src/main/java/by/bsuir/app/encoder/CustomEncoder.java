package by.bsuir.app.encoder;

import javax.servlet.http.HttpServletRequest;

public interface CustomEncoder {
    String reEncode(HttpServletRequest request, String attribute);
}
