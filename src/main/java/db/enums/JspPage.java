package db.enums;

public enum JspPage {

    CATEGORIES_JSP("categories"),
    COMMENTS_JSP("comments"),
    FOOTER_JSP("footer"),
    HEADER_JSP("header"),
    LOGIN_JSP("login"),
    NEWS_JSP("news"),
    NEWS_DETAILS_JSP("news-details"),
    REGISTRATION_JSP("registration"),
    ROLES_JSP("roles"),
    STATUSES_JSP("statuses"),
    USERS_JSP("users"),
    USERS_DETAILS_JSP("users-details"),
    CREATE_NEWS_JSP("create-news"),
    MODERATOR_JSP("moderator"),
    MODERATOR_NEWS_DETAILS_JSP("moderator-news-details"),
    UPDATE_STATUS_NEWS_JSP("update-status-news"),
    AUTHOR_NEWS_JSP("author-news"),
    EDIT_NEWS_JSP("edit-news");

    private final String path;

    JspPage(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
