<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Comments</title>
</head>
<body>
<%@ include file="header.jsp" %>
<a href="${pageContext.request.contextPath}/moderator"><fmt:message key="page.comments.link" /></a>
<h2><fmt:message key="page.comments.comments" />: </h2>
<ul>
    <c:forEach var="comments" items="${requestScope.comments}">
        <br><li><a href="${pageContext.request.contextPath}/moderator/news/details?newsId=${comments.news.newsId}">
                ${comments.news.title}</a></li><br>
        <c:if test="${not empty news.image}">
            <img src="${pageContext.request.contextPath}/images/${comments.attachment}" alt="Comment image"
                 style="max-width: 10%; height: auto;">
        </c:if>
        <p>${comments.content}</p>
        <span>${comments.formattedCreatedAt} - ${comments.status} - ${comments.user.nickname}</span>
        <c:if test="${comments.status == 'ON_MODERATION'}">
        <a href="${pageContext.request.contextPath}/moderator/edit-comment?commentId=${comments.commentId}">
                <button type="button"><fmt:message key="page.comments.edit" /></button>
            </a>
        </c:if>
        <br>
    </c:forEach>
</ul>
<%@ include file="footer.jsp" %>
</body>
</html>
