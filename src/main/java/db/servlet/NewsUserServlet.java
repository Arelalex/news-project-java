package db.servlet;

import db.dto.NewsDto;
import db.dto.PortalUserDto;
import db.enums.JspPage;
import db.enums.Roles;
import db.enums.Statuses;
import db.service.NewsService;
import db.service.impl.NewsServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.USER_NEWS)
public class NewsUserServlet extends HttpServlet {

    private final NewsService newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userIdParam = req.getParameter("userId");
        if (userIdParam != null && !userIdParam.isBlank()) {
            try {
                var userId = Integer.valueOf(userIdParam);

                NewsDto filter = NewsDto.builder()
                        .userId(userId)
                        .statusId(Statuses.APPROVED.getId())
                        .build();

                req.setAttribute("news", newsService.findAllByFilter(filter));
                var user = (PortalUserDto) req.getSession().getAttribute("user");

                if (user != null && user.getRole() == Roles.MODERATOR) {
                    req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.MODERATOR_NEWS_JSP)).forward(req, resp);
                } else {
                    req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.NEWS_JSP)).forward(req, resp);
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

