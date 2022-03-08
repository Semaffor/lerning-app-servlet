//package by.bsuir.app.filter;
//
//import by.bsuir.app.command.CommandEnum;
//import by.bsuir.app.entity.enums.Role;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.*;
//
//public class AuthFilter implements Filter {
//    private final List<String> urls = new ArrayList<String>();
//    private final Set<CommandEnum> commonCommands = new HashSet<>();
//    private final Set<CommandEnum> clientCommands = new HashSet<>();
//    private final Set<CommandEnum> adminCommands = new HashSet<>();
//    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
//
//    public void init(FilterConfig fConfig) throws ServletException {
//        LOGGER.error("INIT");
//        urls.addAll(Arrays.asList(
//                "/static/*",
//                fConfig.getInitParameter("avoid-url")));
//        commonCommands.add(CommandEnum.LOGIN);
//
//        clientCommands.addAll(commonCommands);
//        clientCommands.addAll(Arrays.asList(
//                CommandEnum.LOGOUT,
//                CommandEnum.SHOW_COURSE_PAGE,
//                CommandEnum.SUBSCRIBE,
//                CommandEnum.UNSUBSCRIBE));
//
//        adminCommands.addAll(clientCommands);
//        adminCommands.addAll(Arrays.asList(
//                CommandEnum.SHOW_MANAGEMENT_COURSES,
//                CommandEnum.SHOW_MANAGEMENT_COUCHES,
//                CommandEnum.MANAGEMENT_COURSES_ACTION));
//
////        LOGGER.debug("Start initializing filter: "
////                + AuthFilter.class.getSimpleName());
////        String avoidURLs = fConfig.getInitParameter("avoid-urls");
////        StringTokenizer token = new StringTokenizer(avoidURLs, ",");
////
////        while (token.hasMoreTokens()) {
////            urls.add(token.nextToken());
////        }
////        LOGGER.debug("Finished initializing filter: "
////                + AuthFilter.class.getSimpleName());
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//        LOGGER.error("DO FILTER");
//        chain.doFilter(request, response); // request for accessible url
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
////        String path = req.getRequestURI().substring(req.getContextPath().length());
////        System.out.println(path);
//        String commandString = req.getParameter("command");
//        HttpSession session = req.getSession();
//
//        if (commandString == null || session == null) {
//            chain.doFilter(req, res);
//        } else {
//            CommandEnum command = CommandEnum.convertCommandFromString(commandString);
//            String roleString = (String) session.getAttribute("userRole");
//
//            if (roleString != null) {
//                Role role = Role.getRoleFromString(roleString);
//
//                switch (role) {
//                    case ADMIN: {
//                        if (adminCommands.contains(command)) {
//                            chain.doFilter(req, res);
//                        } else {
//                            LOGGER.debug("Unauthorized access to resource. Client is not logged-in.");
//                            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                        }
//                        break;
//                    }
//                    case USER: {
////                        if (adminCommands.contains(command)) {
////                            chain.doFilter(req, res);
////                        } else {
////                            LOGGER.debug("Unauthorized access to resource. Client is not logged-in.");
////                            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
////                        }
////                        break;
//                    }
//                    case COUCH: {
////
//                    }
//                    default: throw new UnsupportedEncodingException();
//                }
//            } else {
//                LOGGER.debug("Unauthorized access to resource. Client is not logged-in.");
//                res.sendRedirect("/");
//            }
//        }
//    }
//}
//
//
