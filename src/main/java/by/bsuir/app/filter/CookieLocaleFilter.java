package by.bsuir.app.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieLocaleFilter implements Filter {

    private final static String PARAMETER = "lang";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getParameter(PARAMETER) != null) {
            Cookie cookie = new Cookie("lang", req.getParameter(PARAMETER));
            res.addCookie(cookie);
        }

        chain.doFilter(request, response);
    }
}
