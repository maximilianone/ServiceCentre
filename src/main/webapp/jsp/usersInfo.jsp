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
        <c:if test="${not empty sessionScope.userID}">
        <c:if test = "${sessionScope.role == 'ADMIN' || sessionScope.role == 'MAIN'}">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 text-center">
                    <h2><fmt:message key="search.title.users" bundle="${locale}"/></h2>
         </div></div></div>
         <div style="padding:20px;margin-top:30px;height:100px;" align="center">
        <form class="navbar-form" role="search" method="post" action="serviceCentre">
            <select name="searchParam" class="form-control">
                         <option disabled><fmt:message key="search.parameter" bundle="${locale}"/></option>
                         <option value="login"><fmt:message key="search.user.login" bundle="${locale}"/></option>
           </select>
            <div class="input-group add-on">
              <fmt:message key="search" bundle="${locale}" var="search"/>
              <input class="form-control" placeholder="${search}" name="searchVar" type="text">
              <input type="hidden" name="command" value="searchUsers">
              <div class="input-group-btn">
                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
              </div>
            </div>
          </form>
          </div>
          <c:if test="${empty sessionScope.usersFound}">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 text-center">
                        <b><fmt:message key="search.no.result" bundle="${locale}"/></b>
             </div></div></div>
          </c:if>
          <c:if test="${not empty sessionScope.usersFound}">
            <div style="padding:20px;margin-top:30px;">
                <h2><fmt:message key="search.title.users" bundle="${locale}"/></h2>
                <div>
                <br>
                    <center>
            <table class="table">
                <tr>
                    <th><fmt:message key="search.user.login" bundle="${locale}"/> </th>
                    <th><fmt:message key="search.user.firstname" bundle="${locale}"/> </th>
                    <th> <fmt:message key="search.user.lastname" bundle="${locale}"/> </th>
                    <th> <fmt:message key="search.user.email" bundle="${locale}"/> </th>
                    <th> <fmt:message key="search.user.phone" bundle="${locale}"/> </th>
                    <th> <fmt:message key="search.user.role" bundle="${locale}"/> </th>
                </tr>

                <c:forEach items="${sessionScope.usersFound}" var="objectList">
                    <tr>
                        <td><c:out value="${objectList.getLogin()}"/>
                        <td><c:out value="${objectList.getFirstName()}"/>
                        <td><c:out value="${objectList.getLastName()}"/>
                        <td><c:out value="${objectList.getEmail()}" /></td>
                        <td><c:out value="${objectList.getPhone()}" /></td>
                        <td><c:out value="${objectList.getRole()}" /></td>
                        <c:if test = "${sessionScope.role == 'MAIN' && objectList.getRole() == 'CLIENT'}">
                            <td><form method="post" action="serviceCentre">
                                <input type="hidden" name="oldRole" value="${objectList.getRole()}"/>
                                <input type="hidden" name="newRole" value="admin"/>
                                <input type="hidden" name="updatedUserID" value="${objectList.getId()}"/>
                                <input type="hidden" name="command" value="changeUserRole"/>
                                <button class="btn btn-success" id="confirmBtn" type="submit">
                            <fmt:message key="user.make.admin" bundle="${locale}"/></button></form></td>
                            <td><form method="post" action="serviceCentre">
                                <input type="hidden" name="updatedUserID" value="${objectList.getId()}"/>
                                <input type="hidden" name="oldRole" value="${objectList.getRole()}"/>
                                <input type="hidden" name="newRole" value="master"/>
                                <input type="hidden" name="command" value="changeUserRole"/>
                                <button class="btn btn-success" id="confirmBtn" type="submit">
                            <fmt:message key="user.make.master" bundle="${locale}"/></button></form></td>
                        </c:if>
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