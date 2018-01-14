<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="locale_info" var="locale"/>

<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="title.home" bundle="${locale}"/></title>
        <jsp:include page="/jsp/css.jsp"/>
    </head>
    <body>
    <jsp:include page="/jsp/navigationBar.jsp"/>
    <jsp:include page="/jsp/authorization.jsp"/>
    <c:choose>
        <c:when test="${empty error}">
            <centre><h2><fmt:message key="error.message" bundle="${locale}"/></h2></centre>
        </c:when>
        <c:otherwise>
            <centre><h2><c:out value="${error}" /></h2></centre>
        </c:otherwise>
    </c:choose>
    </body>
</html>