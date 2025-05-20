<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand hidden-sm" href="${contextPath}/">
            <span class="tblog-leaf-logo">&nbsp;</span>
        </a>
        <form class="navbar-form pull-right" action="${contextPath}/search">
            <div class="form-group hidden-xs">
                <div class="input-group">
                    <div class="input-group-addon"><i class="glyphicon glyphicon-search"></i></div>
                    <input type="text" name="keywords" class="form-control" placeholder="">
                </div>
            </div>
        </form>
    </div>
    <div class="navbar-collapse collapse pull-right">
        <ul class="nav navbar-nav">
            <li><a href="${contextPath}/">首頁</a></li>
            <li><a href="${contextPath}/about">關於</a></li>
            <security:authorize access="!isAuthenticated()">
<%--            <c:if test="${empty loginUser}">--%>
                <li><a href="${contextPath}/register">註冊</a></li>
                <li><a href="${contextPath}/login">登錄</a></li>
<%--            </c:if>--%>
            </security:authorize>
<%--            <c:if test="${not empty loginUser}">--%>
                <security:authorize access="isAuthenticated()">
<%--                <c:if test="${loginUser.username=='admin'}">--%>
                    <security:authorize access="hasRole('ADMIN')">
                 <li><a href="${contextPath}/cat/list">分類管理</a></li>

<%--                </c:if>--%>
                <li><a href="${contextPath}/topic/list">文章管理</a></li>
                    </security:authorize>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">${loginUser.username}<b class="caret"></b></a>
                    <ul class="dropdown-menu dropdown-menu-right">
<%--                        <li><a href="${contextPath}/user/info/${loginUser.username}"><i class="glyphicon glyphicon-user"></i> 用户中心</a></li>--%>

    <script>
        console.log("aaa---<security:authentication property="name"/>---" +" <security:authentication property="name"/>" )
    </script>
<%--                         <li>principal.username -- <security:authentication property="principal.username" /> </li>--%>
                         <li>principal.username --  </li>
                         <li><security:authentication property="principal.username" />  </li>

                         <li>security:authentication var="user" property="principal" --- </li>
                         <li><security:authentication var="user" property="principal" /></li>
                         <li> security:authentication property="name" ---</li>
                         <li><security:authentication property="name"/></li>
                        <li><a href="${contextPath}/user/info/${user}"> ><i class="glyphicon glyphicon-user"></i> 用户中心</a></li>
                        <li><a href="${contextPath}/user/changePwd"><i class="glyphicon glyphicon-edit"></i> 修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="${contextPath}/logout"><i class="glyphicon glyphicon-off"></i>退出</a></li>
                    </ul>
                </li>
                </security:authorize>
<%--            </c:if>--%>
        </ul>
    </div>
</div>