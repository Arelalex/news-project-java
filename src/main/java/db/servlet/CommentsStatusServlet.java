package db.servlet;

import db.enums.JspPage;
import db.enums.Statuses;
import db.service.CommentService;
import db.service.impl.CommentServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.STATUSES + UrlPath.COMMENTS)
public class CommentsStatusServlet extends HttpServlet {

    private final CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var statusName = req.getParameter("status");
        if (statusName != null && !statusName.isBlank()) {
            try {
                Statuses status = Statuses.valueOf(statusName);
                req.setAttribute("comments", commentService.findByStatusId(status.getId()));

            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        } else {
            req.setAttribute("comments", commentService.findAll());
        }
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.MODERATOR_COMMENTS_JSP))
                .forward(req, resp);
    }
}

