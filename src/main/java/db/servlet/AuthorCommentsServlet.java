package db.servlet;

import db.dto.CommentDto;
import db.dto.PortalUserDto;
import db.enums.JspPage;
import db.enums.Roles;
import db.enums.Statuses;
import db.service.impl.CommentServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(UrlPath.AUTHOR_COMMENTS)
public class AuthorCommentsServlet extends HttpServlet {

    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            if (!req.getRequestURI().equals("/login")) {
                resp.sendRedirect(JspHelper.getPathJsp(JspPage.LOGIN_JSP));
                return;
            }
        }
        PortalUserDto user = (PortalUserDto) session.getAttribute("user");
        if (user != null && user.getRole() == Roles.USER) {
            Integer userId = user.getUserId();

            CommentDto filter = CommentDto.builder()
                    .userId(userId)
                    .build();

            req.setAttribute("comments", commentService.findAllByFilter(filter));
        } else {
            req.setAttribute("comments", commentService.findAllByFilter(new CommentDto(null, Statuses.ON_MODERATION.getId())));
            req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.MODERATOR_JSP))
                    .forward(req, resp);
        }

        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.AUTHOR_COMMENT_JSP))
                .forward(req, resp);
    }
}
