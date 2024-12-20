<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User comments</title>
</head>
<body>
<%@ include file="header.jsp" %>
<a href="${pageContext.request.contextPath}/news"><fmt:message key="page.myNews.link"/></a>
<h2><fmt:message key="page.myComments.title" />:</h2>
<c:forEach var="comment" items="${requestScope.comments}">
    <li><a href="${pageContext.request.contextPath}/news/details?newsId=${comment.news.newsId}">
            ${comment.news.title}</a>
    </li>
    <c:if test="${not empty comment.attachment}">
        <img src="${pageContext.request.contextPath}/images/${comment.attachment}" alt="Comment attachment"
             style="max-width: 10%; height: auto;">
    </c:if>
    <p><span><fmt:message key="page.myComments.author" />: </span> ${comment.user.nickname}
        <span><fmt:message key="page.myComments.createdAt" />: </span> ${comment.formattedCreatedAt}
        <span><fmt:message key="page.myComments.updatedAt" />: </span> ${comment.formattedUpdatedAt}
        <span><Strong><fmt:message key="page.myComments.status" />: </Strong> ${comment.status}</span></p>
    <p>${comment.content}</p>
    <c:if test="${comment.status == 'REJECTED'}">
        <p>
            <strong><fmt:message key="page.myComments.reason" />: </strong> ${comment.reasonRej}
            <a href="${pageContext.request.contextPath}/edit-comment?commentId=${comment.commentId}&newsId=${comment.news.newsId}">
                <button type="button"><fmt:message key="page.myComments.edit" /></button>
            </a>
        </p>
    </c:if>
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>