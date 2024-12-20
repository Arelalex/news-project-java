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
    <c:if test="${not empty news.image}">
    <img src="${pageContext.request.contextPath}/images/${news.image}" alt="News image"
         style="max-width: 10%; height: auto;">
    </c:if>
    <blockquote><a href="${pageContext.request.contextPath}/news/details?newsId=${news.newsId}">
            ${news.title}</a>
    </blockquote>
    <p>${news.description}</p>
    <p><span><fmt:message key="page.news.createdAt" />: </span> ${news.formattedCreatedAt}
        <span><fmt:message key="page.news.updatedAt" />: </span> ${news.formattedUpdatedAt}</p>
    <br>
    <hr style="border: none; border-top: 2px dashed rgba(128, 128, 128, 0.5);">
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>