<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>${news.title}</h2>
<div>
    <p>${news.description}</p>
    <blockquote>${news.content}</blockquote>
    <p><strong>Дата создания:</strong> ${news.formattedCreatedAt}</p>
    <p><strong>Дата обновления:</strong> ${news.formattedUpdatedAt}</p>
    <p><strong>Автор:</strong> <a href="${pageContext.request.contextPath}/user/news?userId=${news.userId}">${news.user.firstName} ${news.user.lastName}</a></p>
    <p><strong>Категория:</strong> <a href="${pageContext.request.contextPath}/categories/news?categoryId=${news.category.categoryId}">${news.category.category}</a></p>
    <p><strong>Статус:</strong> ${news.status.status}</p>
    <br>
    </div>
<div>
    <h3>Комментарии:</h3>
    <p>
    <c:forEach var="comments" items="${requestScope.comments}">
        <span>${comments.user.nickname}</span><span> ${comments.createdAt}</span>
        <p>${comments.content}</p>
    <br>
    </c:forEach>
    </p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
