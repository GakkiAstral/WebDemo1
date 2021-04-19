<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>无标题文档</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
    <link href="../css/select.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">用户管理</a></li>
        <li><a href="#">查询用户</a></li>
    </ul>
</div>
<div class="rightinfo">
    <form action="../userManager.do" method="post">
        <input type="hidden" name="flag" value="findUser"/>
        <ul class="prosearch">
            <li>
                <label>查询：</label>
                <i>用户名</i>
                <a>
                    <input name="username" type="text" class="scinput" />
                </a>
            </li>
            <li>
                <label>性别：</label>
                <input name="usersex" type="radio" value="1" checked="checked" />&nbsp;男&nbsp;&nbsp;
                <input name="usersex" type="radio" value="0" />&nbsp;女
            </li>
            <li>
                <label>手机号：</label>
                <a>
                    <input name="phonenumber" type="text" class="scinput"/>
                </a>
            </li>
            <li>
                <label>QQ号：</label>
                <a>
                    <input name="qqnumber" type="text" class="scinput" />
                </a>
            </li>
            <a>
                <input type="submit" class="sure" value="查询"/>
            </a>
        </ul>
    </form>
</div>
</body>
</html>
