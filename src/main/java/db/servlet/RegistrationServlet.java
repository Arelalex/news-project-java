package db.servlet;

import db.dto.CreatePortalUserDto;
import db.enums.Roles;
import db.exception.ValidationException;
import db.service.impl.CreatePortalUserServiceImpl;
import db.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final CreatePortalUserServiceImpl createPortalUserService = CreatePortalUserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Roles.values());
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var portalUserDto = CreatePortalUserDto.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .nickname(req.getParameter("nickname"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(Roles.valueOf(req.getParameter("role")))
                .build();

        portalUserDto.setRole(Roles.valueOf(req.getParameter("role")));

        try{
            createPortalUserService.create(portalUserDto);
            resp.sendRedirect(JspHelper.getPath("login"));
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
