package db.servlet;

import db.enums.JspPage;
import db.service.impl.CategoryServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.CATEGORIES)
public class CategoryServlet extends HttpServlet {

    private final CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryService.findAll());
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.CATEGORIES_JSP))
                .forward(req, resp);
    }
}




