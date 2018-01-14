<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="locale_info" var="locale"/>

<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="title.order.add" bundle="${locale}"/></title>
        <jsp:include page="/jsp/css.jsp"/>
    </head>
    <body>
        <jsp:include page="/jsp/navigationBar.jsp"/>
        <jsp:include page="/jsp/authorization.jsp"/>
        <centre><h2><fmt:message key="order.add" bundle="${locale}"/></h2></centre>
        <form method="post" action="serviceCentre">
            <fieldset>
    			<legend><fmt:message key="order.info.title" bundle="${locale}"/></legend>
    			<fmt:message key="order.info.product.type" bundle="${locale}"/><br>
    			<select name="productType">
                    <option value="productType" disabled><fmt:message key="order.info.select.product.type" bundle="${locale}"/></option>
                    <option value="tv"><fmt:message key="order.info.select.tv" bundle="${locale}"/></option>
                    <option value="telephone"><fmt:message key="order.info.select.telephone" bundle="${locale}"/></option>
                    <option value="laptop"><fmt:message key="order.info.select.laptop" bundle="${locale}"/></option>
                    <option value="tablet"><fmt:message key="order.info.select.tablet" bundle="${locale}"/></option>
                </select>
    			<br>
    			<fmt:message key="order.info.product.name" bundle="${locale}"/><br>
    			<input type="text" name="productName" pattern="[A-Za-z0-9]{2,40}" required>
    			<br>
    			<fmt:message key="order.info.problem" bundle="${locale}"/><br>
                <textarea name="problemDescription" rows="10" cols="45" required></textarea>
                <br>
    			<br>
    			<input type="hidden" name="command" value="addOrder">
    			<center><button class="button" id="btnSubmit" type="submit"><fmt:message key="order.add" bundle="${locale}"/></button></center>
    		</fieldset>
        </form>
    </body>
</html>