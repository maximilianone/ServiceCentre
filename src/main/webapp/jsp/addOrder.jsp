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
        <jsp:include page="/jsp/testJS.jsp"/>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <c:if test="${!empty sessionScope.userID}">
            <div class="container">
                <div align="center">
                    <h2><fmt:message key="order.add" bundle="${locale}"/></h2>
                    <form method="post" action="serviceCentre">
                        <fieldset style="width:50%;">
    			            <legend><fmt:message key="order.info.title" bundle="${locale}"/></legend>
    			            <fmt:message key="order.info.product.type" bundle="${locale}"/><br>
    			            <select name="productType" class="form-control">
                                <option value="productType" disabled><fmt:message key="order.info.select.product.type" bundle="${locale}"/></option>
                                <option value="TV"><fmt:message key="order.info.select.tv" bundle="${locale}"/></option>
                                <option value="Telephone"><fmt:message key="order.info.select.telephone" bundle="${locale}"/></option>
                                <option value="Laptop"><fmt:message key="order.info.select.laptop" bundle="${locale}"/></option>
                                <option value="Tablet"><fmt:message key="order.info.select.tablet" bundle="${locale}"/></option>
                            </select>
    			            <br>
    			            <fmt:message key="order.info.product.name" bundle="${locale}"/><br>
    			            <input type="text" name="productName" pattern="[A-ZА-яа-яa-z0-9ІЇії ]{2,40}" class="form-control" required>
    			            <br>
    			            <fmt:message key="order.info.problem" bundle="${locale}"/><br>
                            <textarea name="problemDescription" id="test" pattern="[a-zA-Zа-яА-Я0-9ІЇїі ,;'-]{1,100}" rows="10" cols="45" class="form-control" required></textarea>
                            <br>
    			            <br>
    			            <input type="hidden" name="command" value="addOrder">
    			            <div align="center">
    			            <button class="btn btn-success" id="btnSubmit" type="submit"><fmt:message key="order.add" bundle="${locale}"/></button>
    		                </div>
    		            </fieldset>
                    </form>
                </div>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.userID}">
         <div class="container">
             <div class="row">
                 <div class="col-xs-12 text-center">
                      <h2><fmt:message key="order.add.requirement" bundle="${locale}"/></h2></centre>
                 </div>
             </div>
         </div>
        </c:if>
    </body>
</html>