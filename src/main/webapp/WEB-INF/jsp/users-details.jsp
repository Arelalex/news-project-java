<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User Details</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2><fmt:message key="page.usersDetails.title" />:</h2>
<div>
    <c:if test="${not empty user.image}">
        <img src="${pageContext.request.contextPath}/images/${user.image}" alt="User image"
             style="max-width: 10%; height: auto;">
    </c:if>
    <p>${user.firstName} ${user.lastName}</p>
    <p><fmt:message key="page.usersDetails.email" />: ${user.email}</p>
    <p><fmt:message key="page.usersDetails.nick" />: ${user.nickname}</p>
    <p><fmt:message key="page.usersDetails.role" />: ${user.role}</p>
</div>
<div>
    <h3><a href="${pageContext.request.contextPath}/moderator-comments?userId=${user.userId}"><fmt:message key="page.moderation.listComments" /></a></h3>
</div>
<div>
    <h3><fmt:message key="page.usersDetails.news" />:</h3>
    <p>
        <c:forEach var="news" items="${requestScope.news}">
    <a href="${pageContext.request.contextPath}/moderator/news/details?newsId=${news.newsId}">${news.title}</a></p>
    </c:forEach>
    </p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
