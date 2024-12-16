<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h2><fmt:message key="page.categories.categories" />:</h2>
<ul>
    <c:forEach var="category" items="${requestScope.categories}">
        <li>
            <a href="${pageContext.request.contextPath}/categories/news?categoryId=${category.categoryId}">${category.category}</a>
        </li>
    </c:forEach>
</ul>
<%@ include file="footer.jsp"%>
</body>
</html>
