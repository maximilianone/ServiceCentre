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
        <jsp:include page="/jsp/header.jsp"/>
        <div style="padding:20px;margin-top:30px;width:100%;">
        <table border="0" width = "100%"><tr><td width="70%"  valign="top">
            <table border="0">
            <c:forEach items="${sessionScope.comments}" var="objectList">
                <tr>
                    <td><b>User login: &nbsp</b> <c:out value="${objectList.getLogin()}" /></td>
                </tr>
                <tr>
                    <td><b>Comment: &nbsp</b> <c:out value="${objectList.getContent()}" /></td>
                 </tr>
                <c:if test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'MAIN_ADMIN'}">
                    <tr>
                    <td>
                        <form method="post" action="serviceCentre">
                        <input type="hidden" name="command" value="banComment">
                        <input type="hidden" name="commentID" value="${objectList.getId()}">
                        <button class="btn btn-success" type="submit">
                        <fmt:message key="comment.ban" bundle="${locale}"/></button>
                    </form>
                    </td>
                    </tr>
                </c:if>
                <tr><td><br></td></tr>
            </c:forEach>
            </table>
        </td>
        <c:if test="${!empty sessionScope.userID}">
        <td width="30%"  valign="top"align="<td align=right">
                <form method="post" action="serviceCentre">
                    <fieldset  style="width:100%;">
                        <legend><fmt:message key="comment.title" bundle="${locale}"/></legend>
                        <div align="center">
                        <fmt:message key="comment.orderId" bundle="${locale}"/><br>
                        <input type="text" name="orderID" pattern="[0-9]{1,20}" class="form-control" required>
                        <br><fmt:message key="comment.content" bundle="${locale}"/><br>
                        <textarea name="commentContent" rows="10" cols="45" class="form-control" required></textarea>
                        <br>
                        <input type="hidden" name="command" value ="addComment">
                        <input type="hidden" name="userID" value = "${sessionScope.userID}" >
                         <div align="center">
                                <button class="btn btn-success" id="btnSubmit" type="submit">
                                <fmt:message key="comment.title" bundle="${locale}"/></button>
                         </div>
                         <br>
                    </fieldset>
                </form>

         </td>
        </c:if>
        </tr>
        </table>
        </div>
    </body>
</html>