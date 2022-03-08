//package by.bsuir.app.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class CookieLocaleFilter implements Filter {
//
//    private final static String PARAMETER = "lang";
//    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        LOGGER.error("INIT ENCODING");
//    }
//
//    @Override
//    public void destroy() {
//        LOGGER.error("DESTRoY ENCODING");
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        LOGGER.error("DO ENCODING");
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        String locale = req.getParameter(PARAMETER);
//        LOGGER.info("Current locale: " + locale);
//        if (locale != null) {
//            Cookie cookie = new Cookie("lang", locale);
//            res.addCookie(cookie);
//        }
//
//        chain.doFilter(request, response);
//    }
//}
