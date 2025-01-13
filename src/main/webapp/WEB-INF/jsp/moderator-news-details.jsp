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
<a href="${pageContext.request.contextPath}/moderator"><fmt:message key="page.comments.link" /></a>
<h2>${news.title}</h2>
<div>
    <c:if test="${not empty news.image}">
        <img src="${pageContext.request.contextPath}/images/${news.image}" alt="News image"
             style="max-width: 10%; height: auto;">
    </c:if>
    <p>${news.description}</p>
    <blockquote>${news.content}</blockquote>
    <p><strong><fmt:message key="page.moderationNewsDetails.createdAt" />: </strong> ${news.formattedCreatedAt}</p>
    <p><strong><fmt:message key="page.moderationNewsDetails.updatedAt" />: </strong> ${news.formattedUpdatedAt}</p>
    <p><strong><fmt:message key="page.moderationNewsDetails.author" />: </strong> <a
            href="${pageContext.request.contextPath}/user/news?userId=${news.userId}">${news.user.nickname}</a></p>
    <p><strong><fmt:message key="page.moderationNewsDetails.category" />: </strong> <a
            href="${pageContext.request.contextPath}/categories/news?categoryId=${news.category.categoryId}">${news.category.category}</a>
    </p>
    <p><strong><fmt:message key="page.moderationNewsDetails.status" />: </strong> ${news.status}</p>
    <br>
</div>
<div>
    <form action="${pageContext.request.contextPath}/moderator/update-status-news" method="post">
        <input type="hidden" name="newsId" value="${news.newsId}">
        <label for="statusId"><strong><fmt:message key="page.moderationNewsDetails.newStatus" />: </strong></label>
        <select name="status" id="statusId" required>
            <c:forEach var="status" items="${requestScope.statuses}">
                <option value="${status.status}">${status.status}</option>
            </c:forEach>
        </select>
        <br><br>
        <label for="reasonId"><strong><fmt:message key="page.moderationNewsDetails.reasonRej" />: </strong>
            <input type="text" name="reasonRej" id="reasonId" value="${news.reasonRej}">
        </label><br>
        <br>
        <button type="submit"><fmt:message key="page.moderationNewsDetails.button.change" /></button>
        <br><br>
    </form>
</div>
<div>
    <h3><fmt:message key="page.moderationNewsDetails.comments" />:</h3>
    <p>
        <c:forEach var="comment" items="${requestScope.comments}">
    <hr style="border: none; border-top: 2px dashed rgba(128, 128, 128, 0.5);">
    <span>${comment.user.nickname}</span>
    <span> ${comment.formattedCreatedAt}</span>
    <span> - <fmt:message key="page.moderationNewsDetails.commentStatus" />: ${comment.status}</span>
    <c:if test="${comment.status == 'ON_MODERATION'}">
        <span><a href="${pageContext.request.contextPath}/moderator/edit-comment?commentId=${comment.commentId}">
                <button type="button"><fmt:message key="page.moderationNewsDetails.edit" /></button>
            </a></span>
    </c:if>
    <p>${comment.content}</p>
    <br>
    </c:forEach>
    </p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
