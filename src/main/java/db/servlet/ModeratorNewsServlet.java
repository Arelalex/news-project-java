package db.servlet;

import db.dto.NewsDto;
import db.enums.JspPage;
import db.enums.Statuses;
import db.service.impl.*;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.MODERATOR_NEWS)
public class ModeratorNewsServlet extends HttpServlet {

    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();
    private final StatusServiceImpl statusService = StatusServiceImpl.getInstance();
    private final CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();
    private final PortalUserServiceImpl userService = PortalUserServiceImpl.getInstance();
    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NewsDto filter = NewsDto.builder()
                .statusId(Statuses.ON_MODERATION.getId())
                .build();

        req.setAttribute("news", newsService.findAllByFilter(filter));
        req.setAttribute("categories", categoryService.findAll());
        req.setAttribute("statuses", statusService.findAll());
        req.setAttribute("users", userService.findAll());
        req.setAttribute("comments", commentService.findAll());


        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.MODERATOR_NEWS_JSP))// исправить
                .forward(req, resp);
    }
}


