<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="locale_info" var="locale"/>

<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="title.home" bundle="${locale}"/></title>
        <jsp:include page="/jsp/css.jsp"/>
        <jsp:include page="/jsp/registrationJS.jsp"/>
    </head>
    <body>
        <jsp:include page="/jsp/navigationBar.jsp"/>
        <jsp:include page="/jsp/authorization.jsp"/>
        <br><div style="padding:20px;margin-top:30px;">
        <center><h1><fmt:message key="registration.title" bundle="${locale}"/></h1></center>
        	<div>
        		<center><form method="post" action="serviceCentre">
        			<fieldset>
        			    <legend><fmt:message key="registration.legend" bundle="${locale}"/></legend>
        				<div>
        				 <fmt:message key="registration.firstname" bundle="${locale}"/><br>
        				 <input type="text" name="firstName" pattern="[A-ZА-яа-яa-z]{2,20}" required>
        				 <br>
        				 <fmt:message key="registration.lastname" bundle="${locale}"/><br>
        				<input type="text" name="lastName" pattern="[A-ZА-яа-яa-z]{2,30}" required>
        				<br>
        				<fmt:message key="registration.email" bundle="${locale}"/><br>
        				<input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" id="email" name="email" required>
        				<br>
        				<fmt:message key="registration.phone" bundle="${locale}"/><br>
        				<input type="text" id="phone"  name="phone"  required>
        				<br>
        				<fmt:message key="registration.login" bundle="${locale}"/>:<br>
        				<input type="text" name="login" pattern="[A-ZА-яа-яa-z0-9]{4,20}" required>
        				<br>
        				<fmt:message key="registration.password" bundle="${locale}"/><br>
        				<input type="password" id="password" name="password" pattern="[A-ZА-яа-яa-z0-9]{4,30}" required>
        				<br>
        				<fmt:message key="registration.repeat.password" bundle="${locale}"/><br>
        				<input type="password" id="password_confirm" name="password_confirm" pattern="[A-ZА-яа-яa-z0-9]{4,30}" required>
        				<br>
        				<br>
        				<input type="hidden" name="command" value ="registration"">
        				<center><button class="button" id="btnSubmit" type="submit">
        				<fmt:message key="user.register" bundle="${locale}"/></button></center>
        				<br>
        				</div>
        			  </fieldset>
        			</form></center>
        		</div>
        	</div>
    </body>
</html>