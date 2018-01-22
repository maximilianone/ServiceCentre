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
        <div class="container">
            <div class="row">
                <div class="col-xs-12 text-center">
                    <h2><fmt:message key="order.details.title" bundle="${locale}"/></h2>
         </div></div></div>
        <table class="table">
                    <tr>
                        <td><fmt:message key="search.user.login" bundle="${locale}"/></td>
                        <td><c:out value="${requestScope.order.getLogin()}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="search.order.id" bundle="${locale}"/></td>
                        <td><c:out value="${requestScope.order.getOrderID()}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="user.page.product.name" bundle="${locale}"/></td>
                        <td><c:out value="${requestScope.order.getProductName()}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="user.page.product.type" bundle="${locale}"/></td>
                        <td><c:out value="${requestScope.order.getProductType()}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="user.page.order.problem" bundle="${locale}"/></td>
                        <td><c:out value="${requestScope.order.getProblemDescription()}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="user.page.order.date" bundle="${locale}"/></td>
                        <td><c:out value="${requestScope.order.getDateOfPlacement()}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="user.page.order.status" bundle="${locale}"/></td>
                        <td><c:out value="${requestScope.order.getStatus()}"/></td>
                    </tr>
                    <c:if test="${requestScope.order.getStatus()=='REJECTED'}">
                        <tr>
                            <td><fmt:message key="user.page.order.rejection" bundle="${locale}"/></td>
                            <td><c:out value="${requestScope.order.getRejectionReason()}"/></td>
                        </tr>
                     </c:if>
                     <c:if test="${requestScope.order.getStatus()!='REJECTED' && requestScope.order.getStatus()!='NEW'}">
                        <tr>
                            <td><fmt:message key="user.page.order.price" bundle="${locale}"/></td>
                            <td><c:out value="${requestScope.order.getPrice()}"/></td>
                        </tr>
                    </c:if>
                    <c:if test="${requestScope.order.getStatus()!='NEW'}">
                        <tr>
                            <td><fmt:message key="order.login.manager" bundle="${locale}"/></td>
                            <td><c:out value="${requestScope.order.getManagerLogin()}"/></td>
                        </tr>
                    </c:if>
                    <c:if test="${requestScope.order.getStatus()=='PERFORMED' || requestScope.order.getStatus()=='FULFILLED'|| requestScope.order.getStatus()=='CLOSED'}">
                        <tr>
                            <td><fmt:message key="order.login.master" bundle="${locale}"/></td>
                            <td><c:out value="${requestScope.order.getLogin()}"/></td>
                        </tr>
                    </c:if>
            </table>
            <c:if test="${requestScope.order.getStatus()=='NEW'}">

            <form method="post" action="serviceCentre">
                <br><div style="padding:20px;margin-top:30px;height:200px;">
                <center><br>
                <fieldset>
                <legend><fmt:message key="order.reject" bundle="${locale}"/></legend>
                    <table>
                        <tr>
                            <label><td align="right" valign="top"><fmt:message key="order.reject.reason" bundle="${locale}"/></td>
                                <td><textarea name="rejectionReason" rows="5" cols="45" class="form-control" required></textarea></td></label>
                        </tr>
                        <input type="hidden" name="orderID" value="${requestScope.order.getOrderID()}">
                        <input type="hidden" name="userID" value="${sessionScope.userID}">
                        <input type="hidden" name="status" value="rejected">
                        <input type="hidden" name="command" value="processNewOrder">
                    </table>
                    <br><button class="btn btn-success" type="submit"><fmt:message key="order.reject" bundle="${locale}"/></button><BR><BR></center>
            </fieldset>
            </div>
            </form>

            <form method="post" action="serviceCentre">
                <br><div style="padding:20px;margin-top:30px;height:200px;">
                <center><br>
                <fieldset>
                <legend><fmt:message key="order.accept" bundle="${locale}"/></legend>
                    <table>
                        <tr>
                            <label><td align="right"><fmt:message key="order.accept.price" bundle="${locale}"/></td>
                                <td><input type="number" step="0.01" min="0" name="price" class="form-control" required></td></label>
                        </tr>
                        <input type="hidden" name="orderID" value="${requestScope.order.getOrderID()}">
                        <input type="hidden" name="userID" value="${sessionScope.userID}">
                        <input type="hidden" name="status" value="accepted">
                        <input type="hidden" name="command" value="processNewOrder">
                    </table>
                    <br><button class="btn btn-success" type="submit"><fmt:message key="order.accept" bundle="${locale}"/></button><BR><BR></center>
                </fieldset>
                </div>
            </form>
        </c:if>
        <c:if test="${sessionScope.userID == requestScope.order.getManagerID() || sessionScope.role == 'MAIN'}">
        <div align="center">
            <table border="0">
            <tr>
            <c:if test="${requestScope.order.getStatus()!='NEW' && requestScope.order.getStatus()!='REJECTED' && requestScope.order.getStatus()!='CLOSED'}">
            <td>
                <form method="post" action="serviceCentre" >
                    <input type="hidden" name="orderID" value="${requestScope.order.getOrderID()}"/>
                    <input type="hidden" name="oldStatus" value="${requestScope.order.getStatus()}"/>
                    <input type="hidden" name="command" value="changeOrderStatusAsAdmin">
                    <input type="hidden" name="status" value="closed">
                    <br><button id="confirmBtn" class="btn btn-success" type="submit"><fmt:message key="order.cancel" bundle="${locale}"/></button>
                </form>
            </td>
            </c:if>
            <c:if test="${requestScope.order.getStatus()=='AGREED'}">
            <td>
                <form method="post" action="serviceCentre" >
                    <input type="hidden" name="orderID" value="${requestScope.order.getOrderID()}"/>
                    <input type="hidden" name="oldStatus" value="${requestScope.order.getStatus()}"/>
                    <input type="hidden" name="command" value="changeOrderStatusAsAdmin">
                    <input type="hidden" name="status" value="waiting_for_master">
                    <br><button id="confirmBtn" class="btn btn-success" type="submit"><fmt:message key="order.paid" bundle="${locale}"/></button>
                </form>
            </td>
            </c:if>
            <c:if test="${requestScope.order.getStatus()=='PERFORMED'}">
            <td>
                <form method="post" action="serviceCentre" >
                    <input type="hidden" name="orderID" value="${requestScope.order.getOrderID()}"/>
                    <input type="hidden" name="oldStatus" value="${requestScope.order.getStatus()}"/>
                    <input type="hidden" name="command" value="changeOrderStatusAsAdmin">
                    <input type="hidden" name="status" value="fulfilled">
                    <br><button id="confirmBtn" class="btn btn-success" type="submit"><fmt:message key="order.fulfilled" bundle="${locale}"/></button>
                </form>
            </td>
            </c:if>

            <c:if test="${requestScope.order.getStatus()=='RESERVED_BY_MASTER'}">
            <td>
                <form method="post" action="serviceCentre" >
                    <input type="hidden" name="orderID" value="${requestScope.order.getOrderID()}"/>
                    <input type="hidden" name="oldStatus" value="${requestScope.order.getStatus()}"/>
                    <input type="hidden" name="command" value="changeOrderStatusAsAdmin">
                    <input type="hidden" name="status" value="performed">
                    <br><button id="confirmBtn" class="btn btn-success" type="submit"><fmt:message key="order.performed" bundle="${locale}"/></button>
                </form>
            </td>
            </c:if>
            </tr>
            </table>
        </div>
        </c:if>
    </body>
</html>