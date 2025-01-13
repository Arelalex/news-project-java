package db.servlet;

import db.dto.CreateNewsDto;
import db.dto.PortalUserDto;
import db.entity.CategoryEntity;
import db.enums.JspPage;
import db.exception.ValidationException;
import db.service.impl.CategoryServiceImpl;
import db.service.impl.CreateNewsServiceImpl;
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

import static db.util.UrlPath.CREATE_NEWS;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(CREATE_NEWS)
public class CreateNewsServlet extends HttpServlet {

    private final CreateNewsServiceImpl createNewsService = CreateNewsServiceImpl.getInstance();
    private final CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryService.findAll());
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.CREATE_NEWS_JSP))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(JspHelper.getPathJsp(JspPage.LOGIN_JSP));
            return;
        }

        PortalUserDto user = (PortalUserDto) session.getAttribute("user"); // Достаём текущего пользователя из сессии
        var categoryName = req.getParameter("category");

        Integer categoryId = categoryService.findByName(categoryName)
                .map(CategoryEntity::getCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена: " + categoryName));

        var newsDto = CreateNewsDto.builder()
                .title(req.getParameter("title"))
                .description(req.getParameter("description"))
                .content(req.getParameter("content"))
                .image(req.getPart("image"))
                .userId(String.valueOf(user.getUserId()))
                .categoryId(String.valueOf(categoryId))
                .build();

        try {
            createNewsService.create(newsDto);
            System.out.printf("Что-то не так в сервлете с созданием новости");

            resp.sendRedirect(UrlPath.AUTHOR_NEWS);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}

