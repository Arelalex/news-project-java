package db.servlet;

import db.dto.CommentDto;
import db.dto.EditCommentDto;
import db.dto.PortalUserDto;
import db.enums.JspPage;
import db.exception.ValidationException;
import db.service.impl.CommentServiceImpl;
import db.service.impl.EditCommentServiceImpl;
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

import static db.util.UrlPath.EDIT_COMMENT;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(EDIT_COMMENT)
public class EditCommentServlet extends HttpServlet {

    private final EditCommentServiceImpl editCommentService = EditCommentServiceImpl.getInstance();
    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var commentIdParam = req.getParameter("commentId");
        var commentId = Long.valueOf(commentIdParam);

        req.setAttribute("comment", commentService.findById(commentId));
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.EDIT_COMMENT_JSP))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            if (!req.getRequestURI().equals("/login")) {
                resp.sendRedirect(JspHelper.getPathJsp(JspPage.LOGIN_JSP));
                return;
            }
        }

        PortalUserDto user = (PortalUserDto) session.getAttribute("user");

        var newsIdParam = req.getParameter("newsId");
        var newsId = Long.valueOf(newsIdParam);

        var commentIdParam = req.getParameter("commentId");
        var commentId = Long.valueOf(commentIdParam);

        req.setAttribute("news", commentService.findAllByFilter(new CommentDto(newsId, commentId)));

        System.out.println("commentId: " + req.getParameter("commentId"));
        System.out.println("newsId: " + req.getParameter("newsId"));

        var commentDto = EditCommentDto.builder()
                .commentId(req.getParameter("commentId"))
                .content(req.getParameter("content"))
                .attachment(req.getPart("attachment"))
                .newsId(String.valueOf(newsId))
                .userId(String.valueOf(user.getUserId()))
                .build();

        try {
            editCommentService.update(commentDto);
            System.out.printf("Что-то не так в сервлете с обновлением");

            resp.sendRedirect(UrlPath.AUTHOR_COMMENTS);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}

