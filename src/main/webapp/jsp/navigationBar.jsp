<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="locale_info" var="locale"/>

<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <ul>
        	<li><a href="/ServiceCentre/index.jsp"><fmt:message key="title.home" bundle="${locale}"/></a></li>
        	<li><a href="/ServiceCentre/jsp/addOrder.jsp"><fmt:message key="title.order.add" bundle="${locale}"/></a></li>
        	<li><a href="contact.jsp"><fmt:message key="title.contacts" bundle="${locale}"/></a></li>
        </ul>
    </body>
</html>