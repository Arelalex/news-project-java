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
    <c:if test="${not empty news.image}">
        <c:if test="${not empty news.image}">
            <img src="${pageContext.request.contextPath}/images/${news.image}" alt="News image"
                 style="max-width: 10%; height: auto;">
        </c:if>
    </c:if>
    <p><strong><fmt:message key="page.editNews.createdAt" />: </strong> ${news.formattedCreatedAt}</p>
    <p><strong><fmt:message key="page.editNews.updatedAt" />: </strong> ${news.formattedUpdatedAt}</p>
    <p><strong>Автор:</strong>
        <a href="${pageContext.request.contextPath}/user/news?userId=${news.userId}">${news.user.nickname}</a></p>
    <p><strong>Категория:</strong>
        <a href="${pageContext.request.contextPath}/categories/news?categoryId=${news.category.categoryId}">${news.category.category}</a>
    </p>
    <p><strong><fmt:message key="page.editNews.status" />: </strong> ${news.status}</p>
    <br>
</div>
<form action="${pageContext.request.contextPath}/edit-news" method="post" enctype="multipart/form-data">
    <input type="hidden" name="newsId" value="${news.newsId}">
    <label for="titleId"><strong><fmt:message key="page.editNews.title" />: </strong>
        <input type="text" name="title" id="titleId" value="${news.title}" required style="width: 500px; height: 21px;">
    </label><br><br>
    <label for="descriptionId"><strong><fmt:message key="page.editNews.description" />: </strong>
        <textarea name="description" id="descriptionId" required style="width: 500px; height: 100px;"
                  maxlength="256">${news.description}</textarea>
    </label><br><br>
    <label for="contentId"><strong><fmt:message key="page.editNews.content" />: </strong>
        <textarea name="content" id="contentId" required style="width: 500px; height: 300px;">${news.content}</textarea>
    </label><br><br>
    <label for="categoryId"><strong><fmt:message key="page.editNews.category" />: </strong>
        <select name="category" id="categoryId" required>
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category.category}"
                        <c:if test="${category.category == news.category.category}">selected</c:if>>
                        ${category.category}
               </option>
            </c:forEach>
        </select>
    </label><br><br>
    <label for="imageId"><strong><fmt:message key="page.editNews.image" />: </strong>
        <input type="file" name="image" id="imageId" required>
    </label><br><br>
    <button type="submit"><fmt:message key="page.editNews.button.send" /></button>
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
