<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>News Details</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>${news.title}</h2>
<div>
    <img src="${pageContext.request.contextPath}/images/${news.image}" alt="News image"
         style="max-width: 10%; height: auto;">
    <p>${news.description}</p>
    <blockquote>${news.content}</blockquote>
    <p><strong>Дата создания:</strong> ${news.formattedCreatedAt}</p>
    <p><strong>Дата обновления:</strong> ${news.formattedUpdatedAt}</p>
    <p><strong>Автор:</strong> <a
            href="${pageContext.request.contextPath}/user/news?userId=${news.userId}">${news.user.nickname}</a></p>
    <p><strong>Категория:</strong> <a
            href="${pageContext.request.contextPath}/categories/news?categoryId=${news.category.categoryId}">${news.category.category}</a>
    </p>
    <p><strong>Статус:</strong> ${news.status}</p>
    <br>
</div>
<div>
    <form action="${pageContext.request.contextPath}/moderator/update-status-news" method="post">
        <input type="hidden" name="newsId" value="${news.newsId}">
        <label for="statusId"><strong>Status:</strong></label>
        <select name="status" id="statusId" required>
            <c:forEach var="status" items="${requestScope.statuses}">
                <option value="${status.status}">${status.status}</option>
            </c:forEach>
        </select>
        <br><br>
        <label for="reasonId"><strong>Reason for rejection:</strong>
            <input type="text" name="reasonRej" id="reasonId" value="${news.reasonRej}">
        </label><br>
        <br>
        <button type="submit">Change Status</button>
        <br><br>
    </form>
</div>
<div>
    <h3>Комментарии:</h3>
    <p>
        <c:forEach var="comments" items="${requestScope.comments}">
        <span>${comments.user.nickname}</span><span> ${comments.formattedCreatedAt}</span>
    <p>${comments.content}</p>
    <br>
    </c:forEach>
    </p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
