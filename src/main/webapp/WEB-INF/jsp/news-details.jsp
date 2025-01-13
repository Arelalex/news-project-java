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
    <c:if test="${not empty news.image}">
        <img src="${pageContext.request.contextPath}/images/${news.image}" alt="News image"
             style="max-width: 10%; height: auto;">
    </c:if>
    <p>${news.description}</p>
    <blockquote>${news.content}</blockquote>
    <p><strong><fmt:message key="page.newsDetails.createdAt" />: </strong> ${news.formattedCreatedAt}</p>
    <p><strong><fmt:message key="page.newsDetails.updatedAt" />: </strong> ${news.formattedUpdatedAt}</p>
    <p><strong><fmt:message key="page.newsDetails.author" />: </strong> <a href="${pageContext.request.contextPath}/user/news?userId=${news.userId}">${news.user.nickname}</a></p>
    <p><strong><fmt:message key="page.newsDetails.category" />: </strong> <a href="${pageContext.request.contextPath}/categories/news?categoryId=${news.category.categoryId}">${news.category.category}</a></p>
    <p><strong><fmt:message key="page.newsDetails.status" />: </strong> ${news.status}</p>
    <br>
    </div>
<div>
<c:if test="${(user.role == 'USER' || user.role == 'MODERATOR') && news.status == 'APPROVED'}">
    <form action="${pageContext.request.contextPath}/create-comment" method="post" enctype="multipart/form-data">
        <input type="hidden" name="newsId" value="${news.newsId}">
        <label for="contentId"><strong><fmt:message key="page.createComments.content" />: </strong>
            <textarea name="content" id="contentId" required style="width: 500px; height: 100px;"></textarea>
        </label><br><br>
        <label for="attachmentId"><strong><fmt:message key="page.createComments.image" />: </strong>
            <input type="file" name="attachment" id="attachmentId">
        </label><br><br>
        <button type="submit"><fmt:message key="page.createComments.button.send" /></button>
    </form>
</c:if>
    <div>
        <h3><fmt:message key="page.newsDetails.comments" />:</h3>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <p>${error.message}</p>
                    <br>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <p>
    <c:forEach var="comment" items="${requestScope.comments}">
        <span>${comment.user.nickname}</span><span> ${comment.formattedCreatedAt}</span>
        <p>${comment.content}</p>
    <br>
    </c:forEach>
    </p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
