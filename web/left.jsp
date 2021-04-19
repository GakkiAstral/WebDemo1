<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/2 0002
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>

    <script type="text/javascript">
        $(function(){
            //导航切换
            $(".menuson .header").click(function(){
                var $parent = $(this).parent();
                $(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();

                $parent.addClass("active");
                if(!!$(this).next('.sub-menus').size()){
                    if($parent.hasClass("open")){
                        $parent.removeClass("open").find('.sub-menus').hide();
                    }else{
                        $parent.addClass("open").find('.sub-menus').show();
                    }


                }
            });

            // 三级菜单点击
            $('.sub-menus li').click(function(e) {
                $(".sub-menus li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function(){
                var $ul = $(this).next('ul');
                $('dd').find('.menuson').slideUp();
                if($ul.is(':visible')){
                    $(this).next('.menuson').slideUp();
                }else{
                    $(this).next('.menuson').slideDown();
                }
            });
        })
    </script>

</head>

<body style="background:#f0f9fd;">
<div class="lefttop"><span></span>导航菜单</div>

<dl class="leftmenu">
    <dd>
        <div class="title"><span><img src="images/leftico03.png" /></span>用户管理</div>
        <ul class="menuson">
            <li><cite></cite><a href="usermanager/addUser.jsp" target="rightFrame">添加用户</a><i></i></li>
            <li><cite></cite><a href="usermanager/findUser.jsp" target="rightFrame">查询用户</a><i></i></li>
            <li><cite></cite><a href="logout.do" target="rightFrame">退出登录</a><i></i></li>
        </ul>
    </dd>
</dl>

</body>
</html>
