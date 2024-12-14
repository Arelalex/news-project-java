<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Comments</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Комментарии:</h2>
<ul>
    <c:forEach var="comments" items="${requestScope.comments}">
        <li>${comments.content} - ${comments.formattedCreatedAt} -
            <a href="${pageContext.request.contextPath}/news/details?newsId=${comments.news.newsId}">
                    ${comments.news.title}</a></li>
    </c:forEach>
</ul>
<%@ include file="footer.jsp" %>
</body>
</html>
