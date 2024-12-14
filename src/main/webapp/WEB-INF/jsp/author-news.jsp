<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User news</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Мои новости:</h2>
<c:forEach var="news" items="${requestScope.news}">
    <br>
    <li><a href="${pageContext.request.contextPath}/news/details?newsId=${news.newsId}">
            ${news.title}</a>
    </li>
    <p>${news.description}</p>
    <p><span>Дата создания:</span> ${news.formattedCreatedAt}
        <span>Дата обновления:</span> ${news.formattedUpdatedAt}
        <span><Strong>Статус:</Strong> ${news.status}</span></p>
    <c:if test="${news.status == 'REJECTED'}">
        <p><span><Strong>Причина отклонения:</Strong> ${news.reasonRej}
        <a href="${pageContext.request.contextPath}/edit-news?newsId=${news.newsId}">
            <button type="button">Edit</button>
        </a>
    </c:if>
    </span></p>
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>