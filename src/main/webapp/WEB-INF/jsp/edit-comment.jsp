<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit Comments</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2><fmt:message key="page.editComment.title"/></h2>
<div>
    <c:if test="${not empty news.image}">
        <img src="${pageContext.request.contextPath}/images/${comment.attachment}" alt="News image"
             style="max-width: 10%; height: auto;">
    </c:if>
    <p><strong><fmt:message key="page.editComment.createdAt" />: </strong> ${comment.formattedCreatedAt}</p>
    <p><strong><fmt:message key="page.editComment.updatedAt" />: </strong> ${comment.formattedUpdatedAt}</p>
    <p><strong><fmt:message key="page.editComment.author" />: </strong> ${comment.user.nickname}</p>
    <p><strong><fmt:message key="page.editComment.status" />: </strong> ${comment.status}</p>
    <br>
</div>
<form action="${pageContext.request.contextPath}/edit-comment" method="post" enctype="multipart/form-data">
    <input type="hidden" name="commentId" value="${comment.commentId}">
    <input type="hidden" name="newsId" value="${comment.news.newsId}">
    <label for="contentId"><strong><fmt:message key="page.editComment.content" />: </strong>
        <textarea name="content" id="contentId" required
                  style="width: 500px; height: 300px;">${comment.content}</textarea>
    </label><br><br>
    <label for="attachmentId"><strong><fmt:message key="page.editComment.attachment" />: </strong>
        <input type="file" name="attachment" id="attachmentId">
    </label><br><br>
    <button type="submit"><fmt:message key="page.editComment.button.send" /></button>
</form>
<div>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <p>${error.message}</p>
                <br>
            </c:forEach>
        </div>
    </c:if>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
