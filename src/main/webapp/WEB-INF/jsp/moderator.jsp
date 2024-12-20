<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Moderator</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h3><a href="${pageContext.request.contextPath}/moderator-news"><fmt:message key="page.moderation.listNews" /></a></h3>
<h3><a href="${pageContext.request.contextPath}/comments"><fmt:message key="page.moderation.listComments" /></a></h3>
<h3><a href="${pageContext.request.contextPath}/users"><fmt:message key="page.moderation.listUsers" /></a></h3>
<h3><fmt:message key="page.moderation.listCategory" />:</h3>
<ul>
    <c:forEach var="category" items="${requestScope.categories}">
        <li>
            <a href="${pageContext.request.contextPath}/categories/news?categoryId=${category.categoryId}">${category.category}</a>
        </li>
    </c:forEach>
</ul><br>
<h3><fmt:message key="page.moderation.listStatus" />:</h3>
<ul>
    <c:forEach var="status" items="${requestScope.statuses}">
        <li>
            <a href="${pageContext.request.contextPath}/statuses/news?status=${status.status}">${status.status}</a>
        </li>
    </c:forEach>
</ul><br>
<h3><fmt:message key="page.moderation.listOfComments" />:</h3>
<ul>
    <c:forEach var="status" items="${requestScope.statuses}">
        <li>
            <a href="${pageContext.request.contextPath}/statuses/comments?status=${status.status}">${status.status}</a>
        </li>
    </c:forEach>
</ul><br>
<%@ include file="footer.jsp" %>
</body>
</html>