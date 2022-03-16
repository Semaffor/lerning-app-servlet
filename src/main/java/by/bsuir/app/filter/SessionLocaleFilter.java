package by.bsuir.app.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionLocaleFilter implements Filter {

    private final static String LOCALE_NAME_ATTR = "lang";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String locale = req.getParameter(LOCALE_NAME_ATTR);
        if (locale != null) {
            req.getSession().setAttribute(LOCALE_NAME_ATTR, locale);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}