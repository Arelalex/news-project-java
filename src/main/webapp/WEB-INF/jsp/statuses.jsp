<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h2>Статусы новостей:</h2>
<ul>
    <c:forEach var="statuses" items="${requestScope.statuses}">
        <li>${statuses.status}</li>
        </c:forEach>
</ul>
<%@ include file="footer.jsp"%>
</body>
</html>