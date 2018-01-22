<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.session_locale}"/>

<fmt:setBundle basename="locale_info" var="locale"/>

<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="title.project.name" bundle="${locale}"/></title>
        <jsp:include page="/jsp/js.jsp"/>
        <jsp:include page="/jsp/css.jsp"/>
    </head>

    <body>
        <jsp:include page="/jsp/header.jsp"/>
    </form>

</html>