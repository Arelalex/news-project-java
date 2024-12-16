package db.filter;

import db.dto.PortalUserDto;
import db.enums.Roles;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static db.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> GUEST_PUBLIC_PATH = Set.of(
            LOGIN,
            REGISTRATION,
            IMAGES,
            NEWS,
            LOCALE,
            NEWS_DETAILS,
            USER_NEWS,
            CATEGORIES + NEWS);
    private static final Set<String> USER_PUBLIC_PATH = Set.of(
            LOGIN,
            REGISTRATION,
            IMAGES,
            NEWS,
            LOCALE,
            NEWS_DETAILS,
            CATEGORIES + NEWS,
            LOGOUT,
            USER_NEWS,
            CREATE_NEWS,
            AUTHOR_NEWS,
            EDIT_NEWS);
    private static final Set<String> MODERATOR_PUBLIC_PATH = Set.of(
            LOGIN,
            REGISTRATION,
            IMAGES,
            NEWS,
            LOCALE,
            NEWS_DETAILS,
            USERS,
            USERS_DETAILS,
            CATEGORIES,
            COMMENTS,
            ROLES,
            STATUSES,
            USER_NEWS,
            LOGOUT,
            MODERATOR,
            MODERATOR_NEWS_DETAILS,
            UPDATE_STATUS_NEWS,
            STATUSES + MODERATOR_NEWS_DETAILS);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        var user = (PortalUserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");

        if (isPublicPath(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (isUserPublicPath(uri) && isUserLoggedIn(servletRequest) && user.getRole() == Roles.USER) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (isModeratorPublicPath(uri) && isModeratorLoggedIn(servletRequest) && user.getRole() == Roles.MODERATOR) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            reject(servletRequest, servletResponse);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = (PortalUserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isModeratorLoggedIn(ServletRequest servletRequest) {
        var user = (PortalUserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null && user.getRole() == Roles.MODERATOR;
    }

    private boolean isPublicPath(String uri) {
        return GUEST_PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isUserPublicPath(String uri) {
        return USER_PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isModeratorPublicPath(String uri) {
        return MODERATOR_PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }

    private void reject(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
        ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : LOGIN);
    }

}
