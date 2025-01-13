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
<a href="${pageContext.request.contextPath}/news"><fmt:message key="page.myNews.link" /></a>
<h2><fmt:message key="page.myNews.title" />: </h2>
<c:forEach var="news" items="${requestScope.news}">
    <br>
    <li><a href="${pageContext.request.contextPath}/news/details?newsId=${news.newsId}">
            ${news.title}</a>
    </li>
    <c:if test="${not empty news.image}">
        <img src="${pageContext.request.contextPath}/images/${news.image}" alt="News image"
             style="max-width: 10%; height: auto;">
    </c:if>
    <p>${news.description}</p>
    <p><span><fmt:message key="page.myNews.createdAt" />: </span> ${news.formattedCreatedAt}
        <span><fmt:message key="page.myNews.updatedAt" />: </span> ${news.formattedUpdatedAt}
        <span><Strong><fmt:message key="page.myNews.status" />: </Strong> ${news.status}</span></p>
    <c:if test="${news.status == 'REJECTED'}">
        <p><span><Strong><fmt:message key="page.myNews.reasonRej" />: </Strong> ${news.reasonRej}
        <a href="${pageContext.request.contextPath}/edit-news?newsId=${news.newsId}">
            <button type="button"><fmt:message key="page.myNews.edit" /></button>
        </a>
    </c:if>
    </span></p>
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>