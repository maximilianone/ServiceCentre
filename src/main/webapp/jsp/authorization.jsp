<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="locale_info" var="locale"/>

<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <c:if test="${empty sessionScope.user_login}">
    		<div style="float: right;margin-top:50px;padding:0px 10px;">
    			<a href="#login"><fmt:message key="user.login" bundle="${locale}"/></a>
    			<a  href="register.jsp"><fmt:message key="user.register" bundle="${locale}"/></a>
    		</div>
    	</c:if>

    	<c:if test="${!empty sessionScope.user_login}">
    		<form method="post" action="serviceCentre">
    			<div style="float: right;margin-top:50px;padding:0px 10px;">
    				<input type="hidden" name="command" value="logout">
    				<button type="submit" formaction="personalCabinet">
    				    <a>${sessionScope.user_login} <fmt:message key="user.profile" bundle="${locale}"/></a>
    				</button>
    				<input type="submit"><fmt:message key="user.logout" bundle="${locale}"/></input>
    			</div>
    		</form>
    	</c:if>

    	<form method="post" action="serviceCentre">
    		<div id="login" class="loginDialog">
    			<div>
    				<a href="#close" title="Close" class="close">X</a>
    				<table style="border:#111; border-radius: 4px;">
    					<tr>
    						<td height="2px"><h2><fmt:message key="authorization.username" bundle="${locale}"/></h2></td>
    						<td style="width:50%;"><input type="text" pattern="[A-Za-z0-9]{4,}" name="login" required></td>
    					</tr>
    					<tr>
    						<td height="2px"><h2><fmt:message key="authorization.password" bundle="${locale}"/></h2></td>
    						<td><input type="password" pattern="[A-Za-z0-9]{4,}" name="password" required ></td>
    					</tr>
    				</table>
    				<input type="hidden" name="command" value="login">
    				<center><button class="button" type="submit">
    				<fmt:message key="user.login" bundle="${locale}"/>
    				</button></center>
    			</div>
    		</div>
    	</form>

    </body>
</html>