package db.servlet;

import db.dto.CommentDto;
import db.dto.EditNewsDto;
import db.dto.PortalUserDto;
import db.entity.CategoryEntity;
import db.enums.JspPage;
import db.exception.ValidationException;
import db.service.impl.CategoryServiceImpl;
import db.service.impl.CommentServiceImpl;
import db.service.impl.EditNewsServiceImpl;
import db.service.impl.NewsServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static db.util.UrlPath.EDIT_NEWS;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(EDIT_NEWS)
public class EditNewsServlet extends HttpServlet {

    private final EditNewsServiceImpl editNewsService = EditNewsServiceImpl.getInstance();
    private final CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();
    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();
    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var newsIdParam = req.getParameter("newsId");
        var newsId = Long.valueOf(newsIdParam);
        req.setAttribute("news", newsService.findById(newsId));
        req.setAttribute("comments", commentService.findAllByFilter(new CommentDto(newsId)));
        req.setAttribute("categories", categoryService.findAll());
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.EDIT_NEWS_JSP))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(JspHelper.getPathJsp(JspPage.LOGIN_JSP));
            return;
        }

        PortalUserDto user = (PortalUserDto) session.getAttribute("user");

        var newsId = req.getParameter("newsId");
        System.out.println("newsId получилось достать = " + newsId);

        var categoryName = req.getParameter("category");
        System.out.println("Категория из запроса: " + categoryName);
        Integer categoryId = categoryService.findByName(categoryName)
                .map(CategoryEntity::getCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена: " + categoryName));

        var newsDto = EditNewsDto.builder()
                .newsId(req.getParameter("newsId"))
                .title(req.getParameter("title"))
                .description(req.getParameter("description"))
                .content(req.getParameter("content"))
                .image(req.getPart("image"))
                .userId(String.valueOf(user.getUserId()))
                .categoryId(String.valueOf(categoryId))
                .build();

        try {
            editNewsService.update(newsDto);
            System.out.printf("Что-то не так в сервлете с обновлением");

            resp.sendRedirect(UrlPath.AUTHOR_NEWS);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}

