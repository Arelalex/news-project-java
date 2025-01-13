<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h2><fmt:message key="page.users.title" />:</h2>
<ul>
    <c:forEach var="user" items="${requestScope.users}">
        <li>
            <span style="margin-right: 10px;"><a href="${pageContext.request.contextPath}/users?userId=${user.userId}">
                    ${user.firstName} ${user.lastName}</a></span>
            <span>${user.role}</span>
        </li>
    </c:forEach>
</ul>
<%@ include file="footer.jsp"%>
</body>
</html>
