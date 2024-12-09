<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Новости:</h2>
<c:forEach var="news" items="${requestScope.news}">
    <li><a href="${pageContext.request.contextPath}/news/details?newsId=${news.newsId}">
            ${news.title}</a>:
    </li>
    <p>${news.description}</p>
    <p><span>Дата создания:</span> ${news.formattedCreatedAt}
        <span>Дата обновления:</span> ${news.formattedUpdatedAt}</p>
    <br>
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>