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
        <c:if test="${!empty sessionScope.userID}">
            <form method="post" action="serviceCentre">
                <br><div style="padding:20px;margin-top:30px;height:300px;">
                <div class="container">
                             <div class="row">
                                 <div class="col-xs-12 text-center">
                <h1><fmt:message key="user.page.welcome" bundle="${locale}"/>&nbsp<c:out value="${sessionScope.login}"/></h1>
                </div></div></div>
                <br><div>
                <center><br>
                    <table>
                        <tr>
                            <label><td align="right"><fmt:message key="registration.firstname" bundle="${locale}"/></td>
                             <td><input type="text" id="current_user" name="firstName" pattern="[A-Za-zА-Яа-яІЇії]{2,20}" value="${sessionScope.firstName}" class="form-control" required></td> </label>
                        </tr>
                        <tr>
                            <label><td align="right"><fmt:message key="registration.lastname" bundle="${locale}"/></td>
                            <td><input type="text" id="current_user" name="lastName" pattern="[A-Za-zА-Яа-яІЇії]{2,20}" value="${sessionScope.lastName}" class="form-control" required></td> </label>
                            </tr>
                        <tr>
                            <label><td align="right"><fmt:message key="registration.phone" bundle="${locale}"/></td>
                            <td><input type="text" id="phone" name="phone" value="${sessionScope.phone}" class="form-control" required></td> </label>
                        </tr>
                        <tr>
                            <label><td align="right"><fmt:message key="registration.email" bundle="${locale}"/></td>
                            <td><input type="text" name="email" value="${sessionScope.email}" class="form-control" readonly></td> </label>
                        </tr>
                    </table>
                    <input type="hidden" name="userID" value="${sessionScope.userID}">
                    <input type="hidden" name="command" value="changePersonalInfo">
                    <br><button class="btn btn-success" type="submit"><fmt:message key="user.page.change" bundle="${locale}"/></button><BR><BR></center>
            </div>
            </div>
            </form>
            <form method="post" action="serviceCentre">
                <br><div style="padding:20px;margin-top:30px;height:200px;">
                <center><br>
                <fieldset>
                <legend><fmt:message key="user.page.change.password" bundle="${locale}"/></legend>
                    <table>
                        <tr>
                            <label><td align="right"><fmt:message key="user.page.password.old" bundle="${locale}"/></td>
                                <td><input type="password" name="oldPassword" pattern="[A-Za-z0-9]{4,30}" class="form-control" required></td></label>
                        </tr>
                        <tr>
                            <label><td align="right"><fmt:message key="user.page.password.new" bundle="${locale}"/></td>
                                <td><input type="password" id="password" name="password" pattern="[A-Za-z0-9]{4,30}" class="form-control" required></td></label>
                        </tr>
                        <tr>
                            <label><td align="right"><fmt:message key="user.page.password.repeat" bundle="${locale}"/></td>
                                <td><input type="password" id="password_confirm" name="repeat_password" pattern="[A-Za-z0-9]{4,30}" class="form-control" required></td></label>
                        </tr>
                        <input type="hidden" name="login" value="${sessionScope.login}">
                        <input type="hidden" name="userID" value="${sessionScope.userID}">
                        <input type="hidden" name="command" value="changePassword">
                    </table>
                    <br><button class="btn btn-success" id="btnSubmit" type="submit"><fmt:message key="user.page.change.password" bundle="${locale}"/></button><BR><BR></center>
            </fieldset>
            </div>
            </form>

        <c:if test="${!empty sessionScope.userOrders}">
            <div style="padding:20px;margin-top:30px;">
                <h2><fmt:message key="user.page.orders" bundle="${locale}"/></h2>
            <table class="table">
                <tr>
                    <th><fmt:message key="user.page.order.id" bundle="${locale}"/> </th>
                    <th> <fmt:message key="user.page.product.name" bundle="${locale}"/> </th>
                    <th> <fmt:message key="user.page.product.type" bundle="${locale}"/> </th>
                    <th> <fmt:message key="user.page.order.problem" bundle="${locale}"/> </th>
                    <th> <fmt:message key="user.page.order.date" bundle="${locale}"/> </th>
                    <th> <fmt:message key="user.page.order.status" bundle="${locale}"/> </th>
                    <th> <fmt:message key="user.page.order.price" bundle="${locale}"/> </th>
                    <th> <fmt:message key="user.page.order.rejection" bundle="${locale}"/> </th>
                </tr>

                <c:forEach items="${sessionScope.userOrders}" var="objectList">
                    <tr>
                        <td><c:out value="${objectList.getOrderID()}"/>
                        <td><c:out value="${objectList.getProductName()}"/>
                        <td><c:out value="${objectList.getProductType()}" /></td>
                        <td><c:out value="${objectList.getProblemDescription()}" /></td>
                        <td><c:out value="${objectList.getDateOfPlacement()}" /></td>
                        <td><c:out value="${objectList.getStatus()}" /></td>
                        <td><c:if test="${objectList.getStatus()!='NEW' && objectList.getStatus()!='REJECTED'}">
                            <c:out value="${objectList.getPrice()}" /></c:if></td>
                        <td><c:if test="${objectList.getStatus()=='REJECTED'}">
                            <c:out value="${objectList.getRejectionReason()}" /></c:if></td>
                        <c:if test="${objectList.getStatus()=='ACCEPTED'}">
                            <td><form method="post" action="serviceCentre">
                                <input type="hidden" name="orderID" value="${objectList.getOrderID()}"/>
                                <input type="hidden" name="oldStatus" value="${objectList.getStatus()}"/>
                                <input type="hidden" name="command" value="changeOrderStatus"/>
                                <input type="hidden" name="status" value="agreed"/>
                                <input type="hidden" name="userID" value="${sessionScope.userID}"/>
                                <button class="btn btn-success" id="btnSubmit" type="submit">
                                <fmt:message key="user.page.agree" bundle="${locale}"/></button></form></td>
                            <td><form method="post" action="serviceCentre">
                                <input type="hidden" name="orderID" value="${objectList.getOrderID()}"/>
                                <input type="hidden" name="oldStatus" value="${objectList.getStatus()}"/>
                                <input type="hidden" name="command" value="changeOrderStatus"/>
                                <input type="hidden" name="status" value="closed"/>
                                 <input type="hidden" name="userID" value="${sessionScope.userID}"/>
                                <button class="btn btn-success" id="btnSubmit" type="submit">
                                <fmt:message key="user.page.decline" bundle="${locale}"/></button></form></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            </div>
            </c:if>
            </c:if>
    </body>
</html>