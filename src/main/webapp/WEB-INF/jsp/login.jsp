<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@ include file="header.jsp"%>
<p><a href="${pageContext.request.contextPath}/news"><fmt:message key="page.myNews.link" /></a></p>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="emailId"><strong><fmt:message key="page.login.email" />: </strong>
        <input type="text" name="email" id="emailId" value="${param.email}" required>
    </label><br><br>
    <label for="passwordId"><strong><fmt:message key="page.login.password" />: </strong>
        <input type="password" name="password" id="passwordId" required>
    </label><br><br>
    <button type="submit"><fmt:message key="page.login.submit.button" /></button>
    <p><a href="${pageContext.request.contextPath}/registration">
        <button type="button"><fmt:message key="page.login.register.button" /></button>
    </a></p>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span><fmt:message key="page.login.error" /></span>
        </div>
    </c:if>
</form>
</body>
</html>
