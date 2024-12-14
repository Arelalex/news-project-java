<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit News</title>
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

<%--<img src="${pageContext.request.contextPath}/images/users/5.jpg" alt="User image">--%>
<form action="${pageContext.request.contextPath}/edit-news" method="post" enctype="multipart/form-data">
    <input type="hidden" name="newsId" value="${news.newsId}">
    <label for="titleId"><strong>Title:</strong>
        <input type="text" name="title" id="titleId" required style="width: 500px; height: 21px;">
    </label><br><br>
    <label for="descriptionId"><strong>Description:</strong>
        <textarea name="description" id="descriptionId" required style="width: 500px; height: 100px;"
                  maxlength="256"></textarea>
    </label><br><br>
    <label for="contentId"><strong>Content:</strong>
        <textarea name="content" id="contentId" required style="width: 500px; height: 300px;"></textarea>
    </label><br><br>
    <label for="categoryId"><strong>Category:</strong>
        <select name="category" id="categoryId" required>
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category.category}">${category.category}</option>
            </c:forEach>
        </select>
    </label><br><br>
    <label for="imageId"><strong>Image:</strong>
        <input type="file" name="image" id="imageId">
    </label><br><br>
    <button type="submit">Send</button>
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