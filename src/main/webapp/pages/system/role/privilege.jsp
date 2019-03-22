<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
    <%@ include file="../../include/include.jsp" %>
</header>
<body>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l"><a class="btn btn-primary radius" data-title="保存" onclick="save();" href="javascript:void(0);"><i class="Hui-iconfont">&#xe632;</i>&nbsp;&nbsp;保存</a></span>
    </div>
    <div class="mt-20">
    <table class="table table-border table-bordered table-bg mt-20">
        <thead>
        <tr>
            <th><input type="checkbox" id="totalAll" onclick="enableAll()"/></th>
            <th>资源名称</th>
            <th>资源访问地址</th>
        </tr>
        </thead>
        <tbody ms-controller="privilege">
        <tr ms-for="el in @ps">
            <td><input type="checkbox" ms-attr="value:@el.number" ms-duplex-checked="@el.checked"/></td>
            <td>{{@el.name}}</td>
            <td>{{@el.url}}</td>
        </tr>
        </tbody>
    </table>
    </div>
</div>
<script type="text/javascript">
    var vm = avalon.define({
        $id: "privilege",
        ps:{}
    });

    ajaxPost("<%=context%>/system/role/getpopedom?roleNumber=${number}", null, function(msg) {
        if(msg != null && msg.length > 0) {
            var ps = eval("(" + msg + ")");
            vm.ps = ps;
        }
    });

    function enableAll() {
        var b = document.getElementById("totalAll").checked;
        var len = vm.ps.length;
        for(var i = 0; i < len; i++) {
            vm.ps[i].checked = b;
        }
    }

    function save() {
        var parStr = "roleNumber=${number}&json=" + JSON.stringify(vm.ps.$model)
        ajaxPost("<%=context%>/system/role/savepopedom", parStr, function(msg) {
            if(msg == "0") {
                layer.msg("保存成功",{icon:1,time:1000});
            } else {
                layer.msg("保存失败",{icon:2,time:1000});
            }
        });
    }
</script>
</body>
</html>
