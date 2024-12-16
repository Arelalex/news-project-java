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
    <p>${user.firstName} ${user.lastName}</p>
    <p><fmt:message key="page.usersDetails.email" />: ${user.email}</p>
    <p><fmt:message key="page.usersDetails.nick" />: ${user.nickname}</p>
    <p><fmt:message key="page.usersDetails.role" />: ${user.role}</p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
