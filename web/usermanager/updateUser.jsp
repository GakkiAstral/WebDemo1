<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">用户管理</a></li>
        <li><a href="#">修改用户</a></li>
    </ul>
</div>
<div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <form action="userManager.do" method="post">
        <input type="hidden" name="flag" value="modifyUser">
        <input type="hidden" name="userid" value="${user.userid}">
        <ul class="forminfo">
            <li><label>用户名</label><input name="username" type="text" class="dfinput" value="${user.username}"/></li>
            <li>
                <label>性别</label><cite>
                <c:choose>
                <c:when test="${user.usersex == 1}">
                <input name="usersex" type="radio" value="1" checked="checked"/>男&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="usersex" type="radio" value="0"/>女
                </c:when>
                <c:otherwise>
                <input name="usersex" type="radio" value="1"/>男&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="usersex" type="radio" value="0" checked="checked"/>女
                </c:otherwise>
                </c:choose>
            </li>
            <li><label>手机号</label><input name="phonenumber" type="text" class="dfinput" value="${user.phonenumber}"/>
            </li>
            <li><label>QQ 号</label><input name="qqnumber" type="text" class="dfinput" value="${user.qqnumber}"/></li>
            <li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
        </ul>
    </form>
</div>
</body>
</html>
