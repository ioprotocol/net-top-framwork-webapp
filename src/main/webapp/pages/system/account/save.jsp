<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%@ include file="../../include/include.jsp"%>
</head>
<body>
<article class="page-container">
    <form id="saveForm" name="saveForm" class="form form-horizontal col-xs-8 col-sm-8" id="form-admin-add" ms-controller="saveForm">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>帐号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="account" class="input-text" placeholder="帐号" ms-duplex="@account">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">初始密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" placeholder="初始密码默认为123456" readonly>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="name" class="input-text" placeholder="姓名" ms-duplex="@name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">性别：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input type="radio" ms-duplex="@sex" id="sex-1" value="男">
                    <label for="sex-1">男</label>
                </div>
                <div class="radio-box">
                    <input type="radio" ms-duplex="@sex" id="sex-2" value="女">
                    <label for="sex-2">女</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">状态：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input type="radio" ms-duplex="@isClose" id="open" value="0">
                    <label for="open">打开</label>
                </div>
                <div class="radio-box">
                    <input type="radio" ms-duplex="@isClose" id="close" value="1">
                    <label for="close">关闭</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="mobile" class="input-text" placeholder="手机" ms-duplex="@mobile">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="email" class="input-text" placeholder="@" ms-duplex="@email">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">角色：</label>
            <div class="formControls col-xs-8 col-sm-9"><span class="select-box" style="width:150px;">
                <select class="select" style="width: 120px;" ms-duplex="@roleNumber">
                    <option ms-for="(number, roleName) in @roles" ms-attr="{value: @number}">{{roleName}}</option>
                </select>
			</span></div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">地址：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea class="textarea"  placeholder="详细地址信息" dragonfly="true" ms-duplex="@address" ></textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true" onKeyUp="textarealength(this,100)"   ms-duplex="@remark" ></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe615;</i>提交</button>
                <button class="btn btn-warning" onclick="closeWindow()"><i class="Hui-iconfont">&#xe6a6;</i>关闭</button>
            </div>
        </div>
    </form>
</article>
<script type="text/javascript">
    var vm = avalon.define({
        $id: "saveForm",
        id:"",
        account:"",
        roleNumber:2,
        roles:{},
        name:"",
        sex:"男",
        isClose:"0",
        mobile:"",
        email:"",
        address : "",
        remark:""
    });

    $("#saveForm").validate({
        rules:{
            account:{
                required:true,
                minlength:6,
                maxlength:16
            },
            name:{
                required:true
            },
            mobile:{
                required:true
            },
            email:{
                required:true,
                email:true
            }
        },
        focusInvalid:false,
        success:"valid",
        submitHandler:function(form){
            ajaxPost("<%=context%>/system/account/save", "json=" + JSON.stringify(vm.$model), function (msg) {
                if(msg == 0) {
                    parent.reload();
                    layer_close();
                } else {
                    layer.msg("提交失败",{icon:2,time:1000});
                }
            });
        }
    });

    function closeWindow() {
        layer_close();
    }

    function load() {
        ajaxPost("<%=context%>/system/account/get", "id=${id}", function (msg) {
            if(msg != null && msg.length > 0) {
                var account = eval("(" + msg + ")");
                var newData = avalon.mix(true, {}, vm.$model, account)
                for (var i in newData) {
                    if (vm.hasOwnProperty(i) && i !== "hasOwnProperty"){//安全更新数据
                        vm[i] = newData[i]
                    }
                }
            }
        });
    }

    ajaxPost("<%=context%>/system/role/listoption", null, function(msg) {
        var roles = eval("(" + msg + ")");
        vm["roles"] = roles;
    });

    load();
</script>
</body>
</html>
