<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%--<img src="${pageContext.request.contextPath}/images/users/5.jpg" alt="User image">--%>
<form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
    <label for="firstNameId"><strong>Имя:</strong>
        <input type="text" name="firstName" id="firstNameId" required>
    </label><br><br>
    <label for="lastNameId"><strong>Фамилия:</strong>
        <input type="text" name="lastName" id="lastNameId" required>
    </label><br><br>
    <label for="nicknameId"><strong>Никнейм:</strong>
        <input type="text" name="nickname" id="nicknameId" required>
    </label><br><br>
    <label for="emailId"><strong>Email:</strong>
        <input type="text" name="email" id="emailId" required>
    </label><br><br>
    <label for="passwordId"><strong>Пароль:</strong>
        <input type="password" name="password" id="passwordId" required>
    </label><br><br>
    <label for="roleId"><strong>Роль:</strong>
        <select name="role" id="roleId">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role.name()}">${role.name()}</option>
            </c:forEach>
        </select>
    </label><br><br>
    <label for="imageId"><strong>Аватарка:</strong>
        <input type="file" name="image" id="imageId">
    </label><br><br>
    <button type="submit">Send</button>
</form>
<div>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
                <br>
            </c:forEach>
        </div>
    </c:if>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
