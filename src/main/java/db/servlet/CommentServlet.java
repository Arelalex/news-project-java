package db.servlet;

import db.dto.CommentDto;
import db.enums.JspPage;
import db.enums.Statuses;
import db.service.impl.CommentServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.COMMENTS)
public class CommentServlet extends HttpServlet {

    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDto filter = CommentDto.builder()
                .statusId(Statuses.ON_MODERATION.getId())
                .build();

        req.setAttribute("comments", commentService.findAllByFilter(filter));

        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.COMMENTS_JSP))
                .forward(req, resp);
    }
}

