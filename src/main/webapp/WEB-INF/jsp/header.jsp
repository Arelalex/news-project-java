<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang != null
                            ? sessionScope.lang
                            : (param.lang != null ? param.lang : 'en_US')}"/>
<fmt:setBundle basename="translations" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Новостной портал</title>
    <style>
        #header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }

        .left-buttons {
            display: flex;
            gap: 10px;
        }

        .right-buttons {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }

        button {
            padding: 5px 10px;
            font-size: 12px;
            cursor: pointer;
        }

        form {
            margin: 0;
        }

        .user-email {
            position: absolute;
            top: 10px;
            right: 10px;
            font-size: 14px;
            color: #333;
        }
    </style>
</head>
<body>
<h1><fmt:message key="page.news.header" /></h1>
<c:if test="${not empty sessionScope.user}">
    <div class="user-email">
        <p>${sessionScope.user.email}</p>
    </div>
</c:if>
<div id="header">
    <!-- Кнопки справа -->
    <div class="right-buttons">
        <c:if test="${empty sessionScope.user}">
            <div id="loginId">
                <a href="${pageContext.request.contextPath}/login">
                    <button type="button"><fmt:message key="page.news.button.login" /></button>
                </a>
            </div>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <div id="logout">
                <form action="/logout" method="post">
                    <button type="submit"><fmt:message key="page.news.button.logout" /></button>
                </form>
            </div>
        </c:if>
    </div>

    <!-- Кнопки слева -->
    <div class="left-buttons">
        <c:if test="${not empty sessionScope.user && sessionScope.user.role == 'MODERATOR'}">
            <div id="moderatorId">
                <a href="${pageContext.request.contextPath}/moderator">
                    <button type="button"><fmt:message key="page.news.button.moderator" /></button>
                </a>
            </div>
            <div id="userNews">
                <a href="${pageContext.request.contextPath}/news">
                    <button type="button"><fmt:message key="page.news.button.users" /></button>
                </a>
            </div>
        </c:if>
        <c:if test="${not empty sessionScope.user && sessionScope.user.role == 'USER'}">
            <div id="create">
                <a href="${pageContext.request.contextPath}/create-news">
                    <button type="button"><fmt:message key="page.news.button.createNews" /></button>
                </a>
            </div>
            <div id="myNews">
                <a href="${pageContext.request.contextPath}/author-news">
                    <button type="button"><fmt:message key="page.news.button.myNews" /></button>
                </a>
            </div>
        </c:if>
        <div id="locale">
            <form action="${pageContext.request.contextPath}/locale" method="post">
                <button type="submit" name="lang" value="ru_RU">RU</button>
                <button type="submit" name="lang" value="en_US">EN</button>
            </form>
        </div>
    </div>
</div>
<hr>
</body>
</html>
