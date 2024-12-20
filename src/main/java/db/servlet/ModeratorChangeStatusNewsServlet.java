package db.servlet;

import db.entity.StatusesEntity;
import db.exception.ValidationException;
import db.service.impl.NewsServiceImpl;
import db.service.impl.StatusServiceImpl;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static db.util.UrlPath.UPDATE_STATUS_NEWS;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(UPDATE_STATUS_NEWS)
public class ModeratorChangeStatusNewsServlet extends HttpServlet {

    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();
    private final StatusServiceImpl statusService = StatusServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var newsIdParam = req.getParameter("newsId");
        Long newsId = Long.parseLong(newsIdParam);
        var statusName = req.getParameter("status");
        Integer statusId = statusService.findByName(statusName)
                .map(StatusesEntity::getStatusId)
                .orElseThrow(() -> new IllegalArgumentException("Статус не найден: " + statusName));
        var reasonRej = req.getParameter("reasonRej");

        try {
            newsService.updateStatus(newsId, statusId, reasonRej);
            resp.sendRedirect(UrlPath.MODERATOR_NEWS_DETAILS + "?newsId=" + newsId);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}

