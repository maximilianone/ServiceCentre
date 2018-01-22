<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.session_locale}"/>

<fmt:setBundle basename="locale_info" var="locale"/>

<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="title.project.name" bundle="${locale}"/></title>
        <jsp:include page="/jsp/css.jsp"/>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <div class="container">
             <div class="row">
                  <div class="col-xs-12 text-center">
                        <c:choose>
                            <c:when test="${empty error}">
                                <centre><h2><fmt:message key="error.message" bundle="${locale}"/></h2></centre>
                            </c:when>
                            <c:otherwise>
                                <centre><h2><c:out value="${error}" /></h2></centre>
                            </c:otherwise>
                        </c:choose>
         </div></div></div>
    </body>
</html>