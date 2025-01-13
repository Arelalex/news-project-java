package db.servlet;

import db.dto.NewsDto;
import db.enums.JspPage;
import db.enums.Statuses;
import db.service.impl.CategoryServiceImpl;
import db.service.impl.NewsServiceImpl;
import db.service.impl.PortalUserServiceImpl;
import db.service.impl.StatusServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.MODERATOR)
public class ModeratorServlet extends HttpServlet {

    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();
    private final StatusServiceImpl statusService = StatusServiceImpl.getInstance();
    private final CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();
    private final PortalUserServiceImpl userService = PortalUserServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("news", newsService.findAllByFilter(new NewsDto(null, null, Statuses.ON_MODERATION.getId())));
        req.setAttribute("categories", categoryService.findAll());
        req.setAttribute("statuses", statusService.findAll());
        req.setAttribute("users", userService.findAll());

        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.MODERATOR_JSP))
                .forward(req, resp);
    }
}

