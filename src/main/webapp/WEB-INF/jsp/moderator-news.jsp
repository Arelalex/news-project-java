<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Moderator</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2><fmt:message key="page.moderation.title" /></h2>
<c:forEach var="news" items="${requestScope.news}">
    <li><a href="${pageContext.request.contextPath}/moderator/news/details?newsId=${news.newsId}">
            ${news.title}</a>
    </li>
    <c:if test="${not empty news.image}">
        <img src="${pageContext.request.contextPath}/images/${news.image}" alt="News image"
             style="max-width: 10%; height: auto;">
    </c:if>
    <p>${news.description}</p>
    <p><span><fmt:message key="page.moderation.createdAt" />: </span> ${news.formattedCreatedAt}
        <span>| <fmt:message key="page.moderation.updatedAt" />: </span> ${news.formattedUpdatedAt}
        <span>| <fmt:message key="page.moderation.status" />: </span> ${news.status}
        <span>| <fmt:message key="page.moderation.category" />: </span> ${news.category.category}
        <span>| <fmt:message key="page.moderation.author" />: </span> ${news.user.nickname}
    </p>
    <br>
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>