<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../include/include.jsp"%>
    <link href="<%=context%>/pageslayout/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
    <div class="loginBox">
        <form ms-controller="loginForm" id="loginForm" name="loginForm" class="form form-horizontal">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input ms-duplex="@account" name="account" class="input-text size-M" placeholder="账户" onclick="JavaScript:this.value=''" cssStyle="width:280px"/>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input type="password" ms-duplex="@password" name="password" class="input-text size-M" placeholder="密码" onclick="JavaScript:this.value=''" cssStyle="width:280px"/>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input ms-duplex="@authcode" name="authcode" class="input-text size-M" placeholder="验证码" onblur="if(this.value==''){this.value='验证码'}" onclick="if(this.value=='验证码'){this.value='';}" value="验证码" style="width:150px;" />
                    <img src="<%=context%>/system/user/safecode" id="kaptchaImage" width="80" height="36"><a href="#" onclick="refreshSafeCode()">看不清</a>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="" type="submit" class="btn btn-success radius size-M" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="" type="reset" class="btn btn-default radius size-M" value="&nbsp;重&nbsp;&nbsp;&nbsp;&nbsp;置&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright <spring:message code="copy.write"/></div>
<script>
    $(function(){
        $('#kaptchaImage').click(function () {//生成验证码
            $(this).hide().attr('src', '<%=context%>/system/user/safecode?' + Math.floor(Math.random()*100) ).fadeIn(); })
    });

    var vm = avalon.define({
        $id: "loginForm",
        account:"",
        password: "",
        authcode:""
    });

    function refreshSafeCode() {
        $('#kaptchaImage').hide().attr('src', '<%=context%>/system/user/safecode?' + Math.floor(Math.random()*100) ).fadeIn();
    }

    $("#loginForm").validate({
        rules:{
            account:{
                required:true,
                minlength:6,
                maxlength:16
            },
            password:{
                required:true,
                minlength:6,
                maxlength:16
            },
            authcode:{
                required:true,
                minlength:4,
                maxlength:4
            }
        },
        onkeyup:false,
        focusCleanup:false,
        success:"valid",
        submitHandler:function(form){
            ajaxPost("<%=context%>/system/user/auth", "json=" + JSON.stringify(vm.$model), function (msg) {
                if(msg == "0") {
                    window.location.href="<%=context%>/static/system/index"
                } else if(msg == "1") {
                    layer.msg("验证码有误",{icon:2,time:1000});
                    refreshSafeCode();
                } else if(msg == "2") {
                    layer.msg("帐号或者密码错误",{icon:2,time:1000});
                    refreshSafeCode();
                }
            });
        }
    });
</script>
</body>
</html>
