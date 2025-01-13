package db.servlet;

import db.entity.StatusesEntity;
import db.enums.JspPage;
import db.exception.ValidationException;
import db.service.impl.CommentServiceImpl;
import db.service.impl.PortalUserServiceImpl;
import db.service.impl.StatusServiceImpl;
import db.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static db.util.UrlPath.UPDATE_STATUS_COMMENTS;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(UPDATE_STATUS_COMMENTS)
public class ModeratorChangeStatusCommentServlet extends HttpServlet {

    private final PortalUserServiceImpl userService = PortalUserServiceImpl.getInstance();
    private final StatusServiceImpl statusService = StatusServiceImpl.getInstance();
    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var commentIdParam = req.getParameter("commentId");
        Long commentId = Long.parseLong(commentIdParam);
        req.setAttribute("comment", commentService.findById(commentId));

        req.setAttribute("users", userService.findAll());

        req.setAttribute("statuses", statusService.findAll());
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.MODERATOR_EDIT_COMMENT_JSP))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var commentIdParam = req.getParameter("commentId");
        Long commentId = Long.parseLong(commentIdParam);

        var statusName = req.getParameter("status");
        Integer statusId = statusService.findByName(statusName)
                .map(StatusesEntity::getStatusId)
                .orElseThrow(() -> new IllegalArgumentException("Статус не найден: " + statusName));
        var reasonRej = req.getParameter("reasonRej");

        try {
            commentService.updateStatus(commentId, statusId, reasonRej);
            resp.sendRedirect(req.getContextPath() + "/moderator/edit-comment?commentId=" + commentId);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}

