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
        <c:if test="${not empty sessionScope.userID}">
        <c:if test = "${sessionScope.role == 'ADMIN' || sessionScope.role == 'MAIN'}">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 text-center">
                    <h2><fmt:message key="user.page.orders" bundle="${locale}"/></h2>
         </div></div></div>
         <div style="padding:20px;margin-top:30px;height:100px;" align="center">
        <form class="navbar-form" role="search" method="post" action="serviceCentre">
        <select name="searchParam" class="form-control">
                         <option disabled><fmt:message key="search.parameter" bundle="${locale}"/></option>
                         <option value="order_id"><fmt:message key="search.order.id" bundle="${locale}"/></option>
                         <option value="order_status"><fmt:message key="search.status" bundle="${locale}"/></option>
                         <option value="login"><fmt:message key="search.user.login" bundle="${locale}"/></option>
           </select>
            <div class="input-group add-on">
              <fmt:message key="search" bundle="${locale}" var="search"/>
              <input class="form-control" placeholder="${search}" name="searchVar" type="text" pattern="[A-Za-z0-9 ]{1,30}">
              <input type="hidden" name="command" value="searchOrders">
              <div class="input-group-btn">
                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
              </div>
            </div>
          </form>
          </div>
          <c:if test="${empty sessionScope.ordersFound}">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 text-center">
                        <b><fmt:message key="search.no.result" bundle="${locale}"/></b>
             </div></div></div>
          </c:if>
          <c:if test="${not empty sessionScope.ordersFound}">
            <div style="padding:20px;margin-top:30px;">
                <h2><fmt:message key="user.page.orders" bundle="${locale}"/></h2>
                <div>
                <br>
                    <center>
            <table class="table">
                <tr>
                    <th><fmt:message key="search.user.login" bundle="${locale}"/> </th>
                    <th><fmt:message key="user.page.order.id" bundle="${locale}"/> </th>
                    <th> <fmt:message key="order.login.manager" bundle="${locale}"/> </th>
                    <th> <fmt:message key="order.login.master" bundle="${locale}"/> </th>
                    <th> <fmt:message key="user.page.order.date" bundle="${locale}"/> </th>
                    <th> <fmt:message key="user.page.order.status" bundle="${locale}"/> </th>
                </tr>

                <c:forEach items="${sessionScope.ordersFound}" var="objectList">
                    <tr>
                        <td><c:out value="${objectList.getLogin()}"/>
                        <td><c:out value="${objectList.getOrderID()}"/>
                        <td><c:out value="${objectList.getManagerLogin()}"/>
                        <td><c:out value="${objectList.getMasterLogin()}" /></td>
                        <td><c:out value="${objectList.getDateOfPlacement()}" /></td>
                        <td><c:out value="${objectList.getStatus()}" /></td>
                        <td><form method="post" action="serviceCentre">
                            <input type="hidden" name="orderID" value="${objectList.getOrderID()}"/>
                            <input type="hidden" name="command" value="showOrder"/>
                            <button class="btn btn-success" id="btnSubmit" type="submit">
                        <fmt:message key="order.details" bundle="${locale}"/></button></form></td>
                    </tr>
                </c:forEach>
            </table>
                    </center>
                <br><br>
            </div>
          </c:if>
          </c:if>
          </c:if>
    </body>
</html>