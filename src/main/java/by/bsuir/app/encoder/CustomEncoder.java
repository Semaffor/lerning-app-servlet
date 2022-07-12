package by.bsuir.app.encoder;

import javax.servlet.http.HttpServletRequest;

/**
 * Shows that implementing classes will realise specific method
 * which will re-encode necessary parameter.
 *
 * @author Dima Buyvid
 */
public interface CustomEncoder {
    /**
     * Re-encode special parameter from HttpServletRequest attributes.
     *
     * @param request - httpServletRequest from user.
     * @param attribute - parameter name.
     */
    String reEncode(HttpServletRequest request, String attribute);
}
