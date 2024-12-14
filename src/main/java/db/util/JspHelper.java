package db.util;

import db.enums.JspPage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    public static String getPathJsp(JspPage jspPage) {
        return String.format(JSP_FORMAT, jspPage.getPath());
    }

    public static String getPathString(String jspName) {
        return String.format(JSP_FORMAT, jspName);
    }
}
