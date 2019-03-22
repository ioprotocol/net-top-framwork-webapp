<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../include/include.jsp"%>
</head>
<body>
<article class="page-container">
    <form id="saveForm" name="saveForm" class="form form-horizontal col-xs-8 col-sm-8" id="form-admin-add" ms-controller="saveForm">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">
                <span class="c-red">*</span>原始密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" name="oldPwd" class="input-text" placeholder="原始密码" ms-duplex="@oldPwd">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" id="newPwd" name="newPwd" class="input-text" autocomplete="off" placeholder="新密码" ms-duplex="@newPwd">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码确认：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" name="newPwdCfm" class="input-text" autocomplete="off" placeholder="新密码" ms-duplex="@newPwdCfm">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe615;</i>修改</button>
            </div>
        </div>
    </form>
</article>
<script type="text/javascript">
    var vm = avalon.define({
        $id: "saveForm",
        oldPwd:"",
        newPwd:"",
        newPwdCfm:""
    });

    $("#saveForm").validate({
        rules:{
            oldPwd:{
                required:true,
                minlength:6
            },
            newPwd:{
                required:true,
                minlength:6
            },
            newPwdCfm:{
                required:true,
                minlength:6,
                equalTo:"#newPwd"
            }
        },
        focusInvalid:false,
        success:"valid",
        submitHandler:function(form){
            ajaxPost("<%=context%>/system/account/editpwd", JSON.parse(JSON.stringify(vm.$model)), function (msg) {
                if(msg == 0) {
                    layer.msg("修改成功",{icon:1,time:1000});
                } else {
                    layer.msg("修改失败",{icon:2,time:1000});
                }
            });
        }
    });

    function closeWindow() {
        layer_close();
    }
</script>
</body>
</html>
