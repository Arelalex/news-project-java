<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Create News</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%--<img src="${pageContext.request.contextPath}/images/users/5.jpg" alt="User image">--%>
<form action="${pageContext.request.contextPath}/create-news" method="post" enctype="multipart/form-data">
    <label for="titleId"><strong><fmt:message key="page.createNews.title" />:</strong>
        <input type="text" name="title" id="titleId" required style="width: 500px; height: 21px;">
    </label><br><br>
    <label for="descriptionId"><strong><fmt:message key="page.createNews.description" />:</strong>
        <textarea name="description" id="descriptionId" required style="width: 500px; height: 100px;" maxlength="256"></textarea>
    </label><br><br>
    <label for="contentId"><strong><fmt:message key="page.createNews.content" />:</strong>
        <textarea name="content" id="contentId" required style="width: 500px; height: 300px;"></textarea>
    </label><br><br>
    <label for="categoryId"><strong><fmt:message key="page.createNews.category" />:</strong>
        <select name="category" id="categoryId" required>
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category.category}">${category.category}</option>
            </c:forEach>
        </select>
    </label><br><br>
    <label for="imageId"><strong><fmt:message key="page.createNews.image" />:</strong>
        <input type="file" name="image" id="imageId" required>
    </label><br><br>
    <button type="submit"><fmt:message key="page.createNews.button.send" /></button>
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
