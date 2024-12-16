package db.servlet;

import db.dto.NewsDto;
import db.enums.JspPage;
import db.service.impl.NewsServiceImpl;
import db.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toMap;

@WebServlet("/content")
public class TestContentServlet extends HttpServlet {

    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsDto> newsDtos = newsService.findAll();
        req.setAttribute("news", newsDtos);
        req.getSession().setAttribute("newsMap", newsDtos.stream()
                .collect(toMap(NewsDto::getTitle, NewsDto::getDescription)));

        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.COMMENTS_JSP))
                .forward(req, resp);
    }
}
