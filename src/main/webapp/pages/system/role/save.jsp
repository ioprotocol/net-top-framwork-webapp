<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../include/include.jsp"%>
</head>
<body>
<article class="page-container">
    <form id="saveForm" name="saveForm" class="form form-horizontal col-xs-8 col-sm-8" id="form-admin-add" ms-controller="saveForm">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="name" class="input-text" placeholder="角色名称" ms-duplex="@name">
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
        number:"",
        name:"",
        remark:""
    });

    $("#saveForm").validate({
        rules:{
            name:{
                required:true
            }
        },
        focusInvalid:false,
        success:"valid",
        submitHandler:function(form){
            ajaxPost("<%=context%>/system/role/save", "json=" + JSON.stringify(vm.$model), function (msg) {
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
        ajaxPost("<%=context%>/system/role/get", {"number":"${number}"}, function (msg) {
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

    load();
</script>
</body>
</html>
