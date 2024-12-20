package db.servlet;

import db.dto.CommentDto;
import db.enums.JspPage;
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

@WebServlet(UrlPath.MODERATOR_COMMENTS)
public class CommentsUserServlet extends HttpServlet {

    private final CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userIdParam = req.getParameter("userId");
        if (userIdParam != null && !userIdParam.isBlank()) {
            try {
                var userId = Integer.valueOf(userIdParam);

                CommentDto filter = CommentDto.builder()
                        .userId(userId)
                        .build();

                req.setAttribute("comments", commentService.findAllByFilter(filter));
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.MODERATOR_COMMENTS_JSP))
                    .forward(req, resp);
        }
    }


}

