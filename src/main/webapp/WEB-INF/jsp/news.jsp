<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>News</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2><fmt:message key="page.news.title" />:</h2>
<c:forEach var="news" items="${requestScope.news}">
    <li><a href="${pageContext.request.contextPath}/news/details?newsId=${news.newsId}">
            ${news.title}</a>
    </li>
    <p>${news.description}</p>
    <p><span><fmt:message key="page.news.createdAt" />:</span> ${news.formattedCreatedAt}
        <span><fmt:message key="page.news.updatedAt" />:</span> ${news.formattedUpdatedAt}</p>
    <br>
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>