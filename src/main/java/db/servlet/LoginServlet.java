package db.servlet;

import db.dto.PortalUserDto;
import db.enums.JspPage;
import db.service.impl.CreatePortalUserServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet(UrlPath.LOGIN)
public class LoginServlet extends HttpServlet {

    private final CreatePortalUserServiceImpl createPortalUserService = CreatePortalUserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.LOGIN_JSP))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createPortalUserService.login(req.getParameter("email"), req.getParameter("password"))
                .ifPresentOrElse(
                        user -> {
                            try {
                                onLoginSuccess(user, req, resp);
                            } catch (ServletException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        () -> onLoginFail(req, resp)
                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email=" + req.getParameter("email"));
    }

    @SneakyThrows
    private void onLoginSuccess(PortalUserDto user, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("user", user);

        resp.sendRedirect("/news");
    }
}
