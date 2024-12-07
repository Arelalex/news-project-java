<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Информация о пользователе:</h2>
<div>
    <p>${user.firstName} ${user.lastName}</p>
    <p>email: ${user.email}</p>
    <p>Никнейм: ${user.nickname}</p>
    <p>Роль: ${user.role}</p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
