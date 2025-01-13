package db.servlet;

import db.dto.CreateCommentDto;
import db.dto.PortalUserDto;
import db.enums.JspPage;
import db.exception.ValidationException;
import db.service.impl.CreateCommentServiceImpl;
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

import static db.util.UrlPath.CREATE_COMMENT;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(CREATE_COMMENT)
public class CreateCommentsServlet extends HttpServlet {

    private final CreateCommentServiceImpl createCommentService = CreateCommentServiceImpl.getInstance();
    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            if (!req.getRequestURI().equals("/login")) {
                resp.sendRedirect(JspHelper.getPathJsp(JspPage.LOGIN_JSP));
                return;
            }
        }
        PortalUserDto user = (PortalUserDto) session.getAttribute("user"); // Достаём текущего пользователя из сессии

        var newsIdParam = req.getParameter("newsId");
        var newsId = Long.valueOf(newsIdParam);
        req.setAttribute("news", newsService.findById(newsId));

        var commentDto = CreateCommentDto.builder()
                .content(req.getParameter("content"))
                .attachment(req.getPart("attachment"))
                .newsId(String.valueOf(newsId))
                .userId(String.valueOf(user.getUserId()))
                .build();
        try {
            createCommentService.create(commentDto);
            System.out.printf("Что-то не так в сервлете с созданием комментария");

            resp.sendRedirect(UrlPath.NEWS_DETAILS + "?newsId=" + newsId);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }

}

