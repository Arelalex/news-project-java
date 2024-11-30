package db.servlet;

import db.dao.impl.PortalUserDaoImpl;
import db.entity.PortalUserEntity;
import db.service.impl.PortalUserServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PortalUser", urlPatterns = "/users")
public class PortalUserServlet extends HttpServlet {

    private final PortalUserServiceImpl portalUserService = PortalUserServiceImpl.getInstance();
    private PortalUserDaoImpl portalUserDao = PortalUserDaoImpl.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("text/html");

        try (var writer = resp.getWriter()) {
            if (id != null) {
                int userId = Integer.parseInt(id);
                PortalUserEntity portalUser = portalUserDao.findById(userId).orElse(null);
                if (portalUser != null) {
                    writer.write("<h1>User Details</h1>");
                    writer.write("<p>ID: " + portalUser.getId() + "</p>");
                    writer.write("<p>Name: " + portalUser.getFirstName() + "</p>");
                    writer.write("<p>Email: " + portalUser.getLastName() + "</p>");
                } else {
                    writer.write("<h1>User not found</h1>");
                }
            } else {
                List<PortalUserEntity> users = portalUserDao.findAll();
                writer.write("<h1>All Users</h1>");
                writer.write("<ul>");
                for (PortalUserEntity user : users) {
                    writer.write("<li>" + user.getFirstName() + " " + user.getLastName() + "</li>");
                }
                writer.write("</ul>");
            }
        } catch (NumberFormatException e) {
            resp.getWriter().write("<h1>Invalid ID format</h1>");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}




