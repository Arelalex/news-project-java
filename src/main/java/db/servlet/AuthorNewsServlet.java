package db.servlet;

import db.dto.NewsDto;
import db.dto.PortalUserDto;
import db.enums.JspPage;
import db.enums.Roles;
import db.enums.Statuses;
import db.service.impl.NewsServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(UrlPath.AUTHOR_NEWS)
public class AuthorNewsServlet extends HttpServlet {

    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(JspHelper.getPathJsp(JspPage.LOGIN_JSP));
            return;
        }
        PortalUserDto user = (PortalUserDto) session.getAttribute("user");
        if (user != null && user.getRole() == Roles.USER) {
            Integer userId = user.getUserId();
            var filter = new NewsDto(userId, null, null);

            req.setAttribute("news", newsService.findAllByFilter(filter));
        } else {
            req.setAttribute("news", newsService.findAllByFilter(new NewsDto(null, null, Statuses.ON_MODERATION.getId())));
            req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.MODERATOR_JSP))
                    .forward(req, resp);
        }

        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.AUTHOR_NEWS_JSP))
                .forward(req, resp);
    }
}
