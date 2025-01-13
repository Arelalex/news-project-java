<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Create Comment</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/create-comment" method="post" enctype="multipart/form-data">
    <label for="contentId"><strong><fmt:message key="page.createComments.content" />: </strong>
        <textarea name="content" id="contentId" required style="width: 500px; height: 100px;"></textarea>
    </label><br><br>
    <label for="imageId"><strong><fmt:message key="page.createComments.image" />: </strong>
        <input type="file" name="image" id="imageId">
    </label><br><br>
    <button type="submit"><fmt:message key="page.createComments.button.send" /></button>
</form>
<div>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <p>${error.message}</p>
                <br>
            </c:forEach>
        </div>
    </c:if>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
