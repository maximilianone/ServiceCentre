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
        <br><div style="padding:20px;margin-top:30px;">
        <center><h1>Registration</h1></center>
        	<div>
        		<center><form method="post" action="registration">
        			<fieldset>
        			    <legend>Registration information:</legend>
        				<div style="background-color:#F5FFFA;">
        				 First name:<br>
        				 <input type="text" name="firstname" pattern="[A-Za-z]{2,20}" placeholder="Enter First name" required>
        				 <br>
        				 Last name:<br>
        				<input type="text" name="lastname" pattern="[A-Za-z]{2,30}" placeholder="Enter Last name" required>
        				<br>
        				Email:<br>
        				<input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" id="uemail" name="uemail" placeholder="Enter email" required>
        				<br>
        				Telephon:<br>
        				<input type="text" id="phone"  name="phone"  required>
        				<br>
        				Username:<br>
        				<input type="text" name="uname" pattern="[A-Za-z0-9]{4,20}" placeholder="Enter Username" required>
        				<br>
        				Password:<br>
        				<input type="password" id="password" name="password" pattern="[A-Za-z0-9]{4,30}" placeholder="Enter Password" required>
        				<br>
        				Repeat password:<br>
        				<input type="password" id="password_confirm" name="password_confirm" pattern="[A-Za-z0-9]{4,30}" placeholder="Enter Password" required>
        				<br>
        				<br>
        				<center><button class="button" id="btnSubmit" type="submit">Register</button></center>
        				<br>
        				</div>
        			  </fieldset>
        			</form></center>
        		</div>
        	</div>
    </body>
</html>