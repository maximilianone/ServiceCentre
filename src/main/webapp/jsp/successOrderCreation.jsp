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
        <jsp:include page="/jsp/header.jsp"/>
        <div class="container">
             <div class="row">
                  <div class="col-xs-12 text-center">
                       <h2><fmt:message key="order.add.status.success" bundle="${locale}"/></h2>
        </div></div></div>
    </body>
</html>