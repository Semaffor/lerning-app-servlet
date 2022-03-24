package by.bsuir.app.tag;

import by.bsuir.app.entity.enums.Role;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.*;

public class NavbarMenuTag extends BodyTagSupport {
    private final static String MAIN_PAGE = "<a href=\"/controller?command=showMain\">%s</a>";
    private final static String LOG_IN = "<a href=\"/\">%s</a>";
    private final static String MY_COURSES = " <a href=\"/controller?command=showMyCourses\">%s</a>";
    private final static String MANAGE_COURSES = "<a href=\"/controller?command=manageCourses\">%s</a>";
    private final static String MANAGE_USERS = "<a href=\"/controller?command=manageUsers\">%s</a>";
    private final static String MANAGE_COURSE = "<a href=\"/controller?command=manageCourse\">%s</a>";
    private final static String CHECK_TASKS = "<a href=\"/controller?command=checkTasks\">%s</a>";

    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {
        String menuPage = (String) pageContext.getAttribute("menuPage");
        String logIn = (String) pageContext.getAttribute("logIn");
        String userCourses = (String) pageContext.getAttribute("userCourses");
        String manageCourses = (String) pageContext.getAttribute("manageCourses");
        String manageUsers = (String) pageContext.getAttribute("manageUsers");
        String manageCourse = (String) pageContext.getAttribute("manageCourse");
        String review = (String) pageContext.getAttribute("review");

        JspWriter writer = pageContext.getOut();
        StringBuilder sb = new StringBuilder();
        Map<String, String> menuItems = new LinkedHashMap<>();
        menuItems.put(MAIN_PAGE, menuPage);
        menuItems.put(LOG_IN, logIn);

        try {
            List<String> menu = null;
            if (role != null) {
                menuItems.remove(LOG_IN);
                switch (role) {
                    case USER:
                        menuItems.put(MY_COURSES, userCourses);
                        break;
                    case ADMIN:
                        menuItems.put(MANAGE_COURSES, manageCourses);
                        menuItems.put(MANAGE_USERS, manageUsers);
                        break;
                    case COUCH:
                        menuItems.put(MANAGE_COURSE, manageCourse);
                        menuItems.put(CHECK_TASKS, review);
                        break;
                }
            }
            menu = generateMenu(menuItems);
            for (String menuItem : menu) {
                sb.append(menuItem);
            }
            writer.write("<ul>" + sb + "</ul>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }


    private List<String> generateMenu(Map<String, String> menuItems) {
        List<String> menu = new ArrayList<>();
        for (String menuItem : menuItems.keySet()) {
            String menuWithLocalizationValue = String.format(menuItem, menuItems.get(menuItem));
            menu.add(wrapInUnorderedList(menuWithLocalizationValue));
        }
        return menu;
    }

    private String wrapInUnorderedList(String href) {
        return "<li>" + href + "</li>";
    }
}