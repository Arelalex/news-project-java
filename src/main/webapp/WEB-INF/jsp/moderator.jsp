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
<h2><fmt:message key="page.moderation.title" /></h2>
<c:forEach var="news" items="${requestScope.news}">
    <li><a href="${pageContext.request.contextPath}/moderator/news/details?newsId=${news.newsId}">
            ${news.title}</a>
    </li>
    <p>${news.description}</p>
    <p><span><fmt:message key="page.moderation.createdAt" />:</span> ${news.formattedCreatedAt}
        <span>| <fmt:message key="page.moderation.updatedAt" />:</span> ${news.formattedUpdatedAt}
        <span>| <fmt:message key="page.moderation.status" />:</span> ${news.status}
        <span>| <fmt:message key="page.moderation.category" />:</span> ${news.category.category}
        <span>| <fmt:message key="page.moderation.author" />:</span> ${news.user.nickname}
    </p>
    <br>
</c:forEach>
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
<%@ include file="footer.jsp" %>
</body>
</html>