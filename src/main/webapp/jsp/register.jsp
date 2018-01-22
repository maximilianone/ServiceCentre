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
        <jsp:include page="/jsp/js.jsp"/>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <br><div style="padding:20px;margin-top:30px;">
        <center><h1><fmt:message key="registration.title" bundle="${locale}"/></h1></center>
        	<div align="center">
        		<center><form method="post" action="serviceCentre">
        			<fieldset  style="width:20%;">
        			    <legend><fmt:message key="registration.legend" bundle="${locale}"/></legend>
        				<div align="center">
        				<fmt:message key="registration.firstname" bundle="${locale}"/><br>
        				<input type="text" name="firstName" pattern="[A-ZА-Яа-яa-zІЇії]{2,20}" class="form-control" id="fName" required>
        				<br><fmt:message key="registration.lastname" bundle="${locale}"/><br>
        				<input type="text" name="lastName" pattern="[A-ZА-Яа-яa-zІЇії]{2,30}" class="form-control" required>
        				<br><fmt:message key="registration.email" bundle="${locale}"/><br>
        				<input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,5}$" class="form-control" id="email" name="email" required>
        				<br><fmt:message key="registration.phone" bundle="${locale}"/><br>
        				<input type="text" id="phone"  name="phone" class="form-control" required>
        				<br>
        				<fmt:message key="registration.login" bundle="${locale}"/><br>
        				<input type="text" name="login" pattern="[A-Za-z0-9]{4,20}" class="form-control" required>
        				<br>
        				<fmt:message key="registration.password" bundle="${locale}"/><br>
        				<input type="password" id="password" name="password" pattern="[A-Za-z0-9]{4,30}" class="form-control" required>
        				<br>
        				<fmt:message key="registration.repeat.password" bundle="${locale}"/><br>
        				<input type="password" id="password_confirm" name="password_confirm" pattern="[A-Za-z0-9]{4,30}" class="form-control" required>
        				<br>
        				<br>
        				<input type="hidden" name="command" value ="registration">
        				<div align="center">
        				<button class="btn btn-success" id="btnSubmit" type="submit">
        				<fmt:message key="user.register" bundle="${locale}"/></button>
        				</div>
        				<br>
        				</div>
        			  </fieldset>
        			</form></center>
        		</div>
        	</div>
    </body>
</html>