package by.bsuir.app.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionLocaleFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String locale = req.getParameter("lang");
        if (locale != null) {
            req.getSession().setAttribute("lang", locale);
        }
        chain.doFilter(request, response);
    }
}