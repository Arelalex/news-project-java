package db.servlet;

import db.dto.NewsDto;
import db.enums.JspPage;
import db.service.impl.NewsServiceImpl;
import db.service.impl.PortalUserServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.USERS)
public class PortalUserServlet extends HttpServlet {

    private final PortalUserServiceImpl portalUserService = PortalUserServiceImpl.getInstance();
    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userIdParam = req.getParameter("userId");

        if (userIdParam != null && !userIdParam.isBlank()) {
            try {
                var userId = Integer.valueOf(userIdParam);
                req.setAttribute("user", portalUserService.findById(userId));
                req.setAttribute("news", newsService.findAllByFilter(new NewsDto(userId, null, null)));

                req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.USERS_DETAILS_JSP))
                        .forward(req, resp);
                return;
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
        req.setAttribute("users", portalUserService.findAll());
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.USERS_JSP))
                .forward(req, resp);
    }
}


