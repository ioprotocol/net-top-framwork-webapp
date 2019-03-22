<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
    <%@ include file="../include/include.jsp"%>
</header>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top" ms-controller="accountInfo">
        <div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/system/index"><spring:message code="system.title"/></a>
            <nav class="nav navbar-nav">
            </nav>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">{{name}}/{{role.name}}<i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:void(0);" onclick="personNalInfo();">个人信息</a></li>
                            <li><a href="javascript:void(0);" onclick="editPwd();">修改密码</a></li>
                            <li><a href="javascript:void(0);" onclick="logout();">退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:void(0);" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:void(0);" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                            <li><a href="javascript:void(0);" data-val="blue" title="蓝色">蓝色</a></li>
                            <li><a href="javascript:void(0);" data-val="green" title="绿色">绿色</a></li>
                            <li><a href="javascript:void(0);" data-val="red" title="红色">红色</a></li>
                            <li><a href="javascript:void(0);" data-val="yellow" title="黄色">黄色</a></li>
                            <li><a href="javascript:void(0);" data-val="orange" title="绿色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <input runat="server" id="divScrollValue" type="hidden" value="" />
    <div class="menu_dropdown bk_2" id="menucontainer">
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:void(0);"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:void(0);"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="<%=context%>/static/app/default/index"></iframe>
        </div>
    </div>
</section>
<script type="text/javascript">
    var accountInfo = avalon.define({
        $id: "accountInfo",
        account:"",
        role:{},
        name:""
    });

    ajaxPost("<%=context%>/system/account/getCurrentLogin", null, function (msg) {
        fillModel(msg, accountInfo);
    });

    ajaxPost("<%=context%>/system/index", null, function (msg) {
        var container = document.getElementById("menucontainer");
        var menus = eval("(" + msg + ")");
        var html = "";
        for(var i = 0; i < menus.length; i++) {
            var menu = menus[i];
            html += "<dl id=\"menu-" + menu.id + " \">";
            html += "<dt><i class=\"Hui-iconfont\">" + menu.icon + "</i>" + menu.name + "<i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i></dt>";
            html += "<dd><ul>";
            var items = menu.items;
            for(var j = 0; j < items.length; j++) {
                var item = items[j];
                html += "<li><a _href=\"<%=context%>" + item.url + "\" data-title=\"" + item.name + "\" href=\"javascript:void(0)\">" + item.name + "</a></li>";
            }
            html += "</dd></ul>";
            html += "</dl>";
        }

        container.innerHTML = html;
    });

    function personNalInfo() {
        layer_show("个人信息", "/static/system/account/view", 700,600);
    }
    function editPwd() {
        layer_show("修改密码", "/static/system/account/pwd", 700,300);
    }
    function logout() {
        ajaxPost("/system/user/logout", null, function (msg) {
            if(msg == "0") {
                window.location.href="/static/system/user/login";
            } else {
                layer.msg("登出失败",{icon:2,time:1000});
            }
        });
    }
</script>
</body>
</html>
