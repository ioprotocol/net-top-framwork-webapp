<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
    <%@ include file="../../include/include.jsp" %>
</header>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i>主页<span class="c-gray en">&gt;</span>用户管理<span class="c-gray en">&gt;</span>角色管理<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
        <a class="btn btn-primary radius" data-title="添加角色" onclick="getSave();" href="javascript:void(0);"><i class="Hui-iconfont">&#xe600;</i>添加角色</a></span>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="80">编码</th>
                <th>角色</th>
                <th>备注</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script type="text/javascript">
    var table = $('.table-sort').DataTable({
        "processing": true,
        "serverSide": true,
        "searching" : false,
        "ordering" : false,
        "ajax": {
            "url" : "<%=context%>/system/role/list"
        },
        "columns": [
            { "data": "number" },
            { "data": "name" },
            { "data": "remark" }
        ],
        "columnDefs": [
            { "render": function(data, type, row) {
                var options = "<a title='权限' class='ml-5' style='text-decoration:none' href='javascript:void(0);' onclick='getPrivilege(" + row['number'] + ")'><i class='Hui-iconfont'>&#xe655;</i></a>";
                options += "&nbsp;&nbsp;&nbsp;&nbsp;<a title='编辑' class='ml-5' style='text-decoration:none' href='javascript:void(0);' onclick='getSave(" + row['number'] + ")'><i class='Hui-iconfont'>&#xe6df;</i></a>";
                options += "&nbsp;&nbsp;&nbsp;&nbsp;<a title='删除' class='ml-5' style='text-decoration:none' href='javascript:void(0);' onclick='del(" + row['number'] + ")'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
                return options;
            }, "targets": 3}
        ]
    });

    function getPrivilege(number) {
        var index = layer.open({
            type: 2,
            title: "权限管理",
            content: "<%=context%>/static/system/role/privilege?number=" + number
        });
        layer.full(index);
    }

    function getSave(number) {
        var index = layer.open({
            type: 2,
            title: "角色编辑",
            content: "<%=context%>/static/system/role/save?number=" + number
        });
        layer.full(index);
    }

    function del(number) {
        layer.confirm('确认要删除吗？所有属于该角色的帐号也将被删除',function(index){
            ajaxPost("<%=context%>/system/role/del", {"number":number},
                    function result(msg) {
                        if(msg == 0) {
                            layer.msg("删除成功",{icon:1,time:1000});
                            reload();
                        } else {
                            layer.msg("删除失败",{icon:2,time:1000});
                        }
                    }
            );
        });
    }

    function reload() {
        table.ajax.reload();
    }
</script>
</body>
</html>
