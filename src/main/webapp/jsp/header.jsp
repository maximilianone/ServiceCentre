<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.session_locale}"/>

<fmt:setBundle basename="locale_info" var="locale"/>

    <head>
    <jsp:include page="/jsp/css.jsp"/>
    <script src="/ServiceCentre/js/lang.js"></script>
    </head>
    <body>
       <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
         <div class="container-fluid">
           <div class="navbar-header">
             <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
             </button>
             <a class="navbar-brand" href=""><fmt:message key="title.project.name" bundle="${locale}"/></a>
           </div>
           <div id="navbar" class="collapse navbar-collapse">
             <ul class="nav navbar-nav">
               <c:if test="${fn:endsWith(pageContext.request.requestURI, '/index.jsp')}">
                    <li class="active"><a href="/ServiceCentre/index.jsp"><fmt:message key="title.home" bundle="${locale}"/></a></li>
               </c:if>
               <c:if test="${not fn:endsWith(pageContext.request.requestURI, '/index.jsp')}">
                    <li><a href="/ServiceCentre/index.jsp"><fmt:message key="title.home" bundle="${locale}"/></a></li>
               </c:if>
               <c:if test="${fn:endsWith(pageContext.request.requestURI, '/addOrder.jsp')}">
                   <li class="active"><a href="/ServiceCentre/jsp/addOrder.jsp"><fmt:message key="title.order.add" bundle="${locale}"/></a></li>
               </c:if>
               <c:if test="${not fn:endsWith(pageContext.request.requestURI, '/addOrder.jsp')}">
                    <li><a href="/ServiceCentre/jsp/addOrder.jsp"><fmt:message key="title.order.add" bundle="${locale}"/></a></li>
               </c:if>
               <c:if test="${fn:endsWith(pageContext.request.requestURI, '/contact.jsp')}">
                     <li class="active"><a href="/ServiceCentre/jsp/contact.jsp"><fmt:message key="title.contacts" bundle="${locale}"/></a></li>
               </c:if>
               <c:if test="${not fn:endsWith(pageContext.request.requestURI, '/contact.jsp')}">
                      <li><a href="/ServiceCentre/jsp/contact.jsp"><fmt:message key="title.contacts" bundle="${locale}"/></a></li>
               </c:if>
               <c:if test="${fn:endsWith(pageContext.request.requestURI, '/comment.jsp')}">
                      <li class="active"><a href="serviceCentre?command=showComments"><fmt:message key="title.comments" bundle="${locale}"/></a></li>
               </c:if>
                <c:if test="${not fn:endsWith(pageContext.request.requestURI, '/comment.jsp')}">
                       <li><a href="serviceCentre?command=showComments"><fmt:message key="title.comments" bundle="${locale}"/></a></li>
                </c:if>
             </ul>
             <ul class="nav navbar-nav navbar-right">

                    <c:if test="${sessionScope.role =='ADMIN' || sessionScope.role =='MAIN'}">
                        <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="admin.options" bundle="${locale}"/></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="serviceCentre?command=showAllUsers"><fmt:message key="admin.show.all.users" bundle="${locale}"/></a></li>
                        	    <li><a href="serviceCentre?command=showAllOrders&admin=false"><fmt:message key="admin.show.all.orders" bundle="${locale}"/></a></li>
                        	    <li><a href="serviceCentre?command=showAllOrders&admin=true"><fmt:message key="admin.show.administrated.orders" bundle="${locale}"/></a></li>
                            </ul>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.role =='MASTER'}">
                        <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="master.options" bundle="${locale}"/></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="serviceCentre?command=showMasterOrders&master=all"><fmt:message key="master.show.all.orders" bundle="${locale}"/></a></li>
                        	    <li><a href="serviceCentre?command=showMasterOrders&master=current"><fmt:message key="master.show.executed.orders" bundle="${locale}"/></a></li>
                            </ul>
                        </li>
                    </c:if>

                  <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                    <c:if test="${sessionScope.session_locale == 'en_US' || empty sessionScope.session_locale}">
                        ENG
                    </c:if>
                    <c:if test="${sessionScope.session_locale == 'uk_UA'}">
                        UKR
                    </c:if>
                  </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a id="navUkr" href="serviceCentre?command=changeLocale&session_locale=uk_UA" class="language">
                                <img id="imgNavUkr" src="/ServiceCentre/image/ukr.png" height="15" width = "20">  
                                <span id="lanNavUkr">Українська</span></a></li>
                        	<li><a id="navEng" href="serviceCentre?command=changeLocale&session_locale=en_US" class="language">
                        	    <img id="imgNavEng" src="/ServiceCentre/image/eng.png" height="15" width = "20">  
                        	<span id="lanNavEng">English</span></a></li>
                        </ul>
                  </li>

                <c:if test="${!empty sessionScope.userID}">
                    <li>
                           <a href="serviceCentre?command=showPersonalPage"><c:out value="${sessionScope.login}"/>
                           <fmt:message key="user.profile" bundle="${locale}"/></a>
                    </li>
                    <li>
                    <a href="serviceCentre?command=logout"><fmt:message key="user.logout" bundle="${locale}"/></a>
                     </li>
                 </c:if>

                <c:if test="${empty sessionScope.userID}">
                    <c:if test="${fn:endsWith(pageContext.request.requestURI, '/register.jsp')}">
                        <li class="active"><a href="/ServiceCentre/jsp/register.jsp"><fmt:message key="user.register" bundle="${locale}"/></a></li>
                    </c:if>
                    <c:if test="${not fn:endsWith(pageContext.request.requestURI, '/register.jsp')}">
                        <li><a href="/ServiceCentre/jsp/register.jsp"><fmt:message key="user.register" bundle="${locale}"/></a></li>
                    </c:if>
                   <li class="dropdown">
                     <a href="http://phpoll.com/login" class="dropdown-toggle" data-toggle="dropdown">
                        <fmt:message key="user.login" bundle="${locale}"/><span class="caret"></span></a>
                     <ul class="dropdown-menu dropdown-lr" role="menu">
                       <div class="col-lg-12">
                         <div class="text-center">
                           <h3><b><fmt:message key="user.login" bundle="${locale}"/></b></h3></div>
                         <form id="ajax-login-form" action="serviceCentre" method="post" role="form" autocomplete="off">
                           <div class="form-group">
                             <label for="username"><fmt:message key="authorization.username" bundle="${locale}"/></label>
                             <fmt:message key="authorization.username.placeholder" bundle="${locale}" var="usernameLocale"/>
                             <input type="text" name="login" pattern="[A-Za-z0-9]{4,}" tabindex="1" class="form-control" placeholder="${usernameLocale}" autocomplete="off">
                           </div>

                           <div class="form-group">
                             <label for="password"><fmt:message key="authorization.password" bundle="${locale}"/></label>
                             <fmt:message key="authorization.password.placeholder" bundle="${locale}" var="passwordLocale"/>
                             <input type="password" name="password" pattern="[A-Za-z0-9]{4,}" tabindex="2" class="form-control" placeholder="${passwordLocale}" autocomplete="off">
                           </div>

                           <input type="hidden" class="hide" name="command" value="login">

                           <div class="form-group">
                             <div class="row">
                               <div class="col-xs-6 pull-right">
                                <fmt:message key="user.login" bundle="${locale}" var="submitLocale"/>
                                 <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-success" value="${submitLocale}">
                               </div>
                             </div>
                           </div>

                         </form>
                       </div>
                     </ul>
                   </li>
              </c:if>
             </ul>
           </div>
         </div>
       </nav>
       <script src="/ServiceCentre/js/bootstrap.min.js"></script>
       <script src="/ServiceCentre/js/jquery-1.10.2.min.js"></script>
    </body>