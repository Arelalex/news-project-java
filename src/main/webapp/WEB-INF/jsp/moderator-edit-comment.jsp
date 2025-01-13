<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Comment Details</title>
</head>
<body>
<%@ include file="header.jsp" %>
<p><a href="${pageContext.request.contextPath}/moderator/news/details?newsId=${comment.news.newsId}">
    ${comment.news.title}</a></p>
<h3><fmt:message key="page.moderationEditComment.comments" />:</h3>
<div>
    <c:if test="${not empty comment.attachment}">
        <img src="${pageContext.request.contextPath}/images/${comment.attachment}" alt="Comment image" style="max-width: 10%; height: auto;">
    </c:if>
    <p><strong><fmt:message key="page.moderationEditComment.content" />: </strong> ${comment.content}</p>
    <p><strong><fmt:message key="page.moderationEditComment.createdAt" />: </strong> ${comment.formattedCreatedAt}</p>
    <p><strong><fmt:message key="page.moderationEditComment.updatedAt" />: </strong> ${comment.formattedUpdatedAt}</p>
    <p><strong><fmt:message key="page.moderationEditComment.author" />: </strong> ${comment.user.nickname}</p>
    <p><strong><fmt:message key="page.moderationEditComment.status" />: </strong> ${comment.status}</p>
    <br>
</div>
<div>
    <form action="${pageContext.request.contextPath}/moderator/edit-comment" method="post">
        <input type="hidden" name="commentId" value="${comment.commentId}">
        <label for="statusId"><strong><fmt:message key="page.moderationEditComment.newStatusComment" />: </strong></label>
        <select name="status" id="statusId" required>
            <c:forEach var="status" items="${requestScope.statuses}">
                <option value="${status.status}">${status.status}</option>
            </c:forEach>
        </select>
        <br><br>
        <label for="commentReasonId"><strong><fmt:message key="page.moderationEditComment.reasonRejComment" />: </strong>
            <input type="text" name="reasonRej" id="commentReasonId" value="${comment.reasonRej}">
        </label><br>
        <br>
        <button type="submit"><fmt:message key="page.moderationEditComment.button.changeComment" /></button>
        <br><br>
    </form>
    </p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
