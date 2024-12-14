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
<h2>Новости для модерации:</h2>
<c:forEach var="news" items="${requestScope.news}">
    <li><a href="${pageContext.request.contextPath}/moderator/news/details?newsId=${news.newsId}">
            ${news.title}</a>
    </li>
    <p>${news.description}</p>
    <p><span>Дата создания:</span> ${news.formattedCreatedAt}
        <span>| Дата обновления:</span> ${news.formattedUpdatedAt}
        <span>| Статус:</span> ${news.status}
        <span>| Категория:</span> ${news.category.category}
        <span>| Автор:</span> ${news.user.nickname}
    </p>
    <br>
</c:forEach>
<h3>Список категорий:</h3>
<ul>
    <c:forEach var="category" items="${requestScope.categories}">
        <li>
            <a href="${pageContext.request.contextPath}/categories/news?categoryId=${category.categoryId}">${category.category}</a>
        </li>
    </c:forEach>
</ul><br>
<h3>Список статусов:</h3>
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