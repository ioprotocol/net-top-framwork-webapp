<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
<%@ include file="../../include/include.jsp" %>
</header>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i>主页<span class="c-gray en">&gt;</span> 用户管理 <span class="c-gray en">&gt;</span>帐号管理<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c" ms-controller="queryForm">
        <span class="select-box inline">
            <select class="select" style="width: 120px;" ms-duplex="@roleNumber">
                <option value="">全部角色</option>
                <option ms-for="(number,name) in @roles" ms-attr="{value: @number}">{{name}}</option>
            </select>
		</span>
        <input type="text" ms-duplex="@account"placeholder="帐号" style="width:120px" class="input-text">
        <input type="text" ms-duplex="@mobile" placeholder="电话" style="width:120px" class="input-text">
        <input type="text" ms-duplex="@email" placeholder="邮箱" style="width:120px" class="input-text">
        <button class="btn btn-success" onclick="reload()"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
        <a class="btn btn-primary radius" data-title="添加帐号" onclick="getSave();" href="javascript:void(0);"><i class="Hui-iconfont">&#xe600;</i>添加帐号</a></span>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="80">ID</th>
                <th>帐号</th>
                <th>角色</th>
                <th>姓名</th>
                <th>性别</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script type="text/javascript">
    var vm = avalon.define({
        $id: "queryForm",
        account:"",
        roleNumber:"",
        roles:{},
        mobile:"",
        email:""
    });

    ajaxPost("<%=context%>/system/role/listoption", null, function(msg) {
        var roles = eval("(" + msg + ")");
        vm["roles"] = roles;
    });
    vm.roleNumber = "";

    var table = $('.table-sort').DataTable({
        "processing": true,
        "serverSide": true,
        "searching" : false,
        "ordering" : false,
        "ajax": {
            "url" : "<%=context%>/system/account/list",
            "data" : function (d) {
                d.roleNumber = vm.roleNumber;
                d.account = vm.account;
                d.mobile = vm.mobile;
                d.email = vm.email;
            }
        },
        "columns": [
            { "data": "id" },
            { "data": "account" },
            { "data": "role.name" },
            { "data": "name" },
            { "data": "sex" },
            { "data": "mobile" },
            { "data": "email" },
            { "data": "isClose" }
        ],
        "columnDefs": [
            { "render": function(data, type, row) {
                var options = "<a title='密码重置' class='ml-5' style='text-decoration:none' href='javascript:void(0);' onclick='resetpwd(" + row['id'] + ")'><i class='Hui-iconfont'>&#xe63f;</i></a>";
                options += "&nbsp;&nbsp;&nbsp;&nbsp;<a title='编辑' class='ml-5' style='text-decoration:none' href='javascript:void(0);' onclick='getSave(" + row['id'] + ")'><i class='Hui-iconfont'>&#xe6df;</i></a>";
                options += "&nbsp;&nbsp;&nbsp;&nbsp;<a title='删除' class='ml-5' style='text-decoration:none' href='javascript:void(0);' onclick='del(" + row['id'] + ")'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
                return options;
            }, "targets": 8},
            { "render": function(data, type, row) {
                if(data == 0) {
                    var op = "&nbsp;&nbsp;&nbsp;&nbsp;<a title='停用' class='ml-5' style='text-decoration:none' href='javascript:void(0);' onclick='changeAccountStatus(" + row['id'] + ")'><i class='Hui-iconfont'><font color='red'>&#xe60e;</font></i></a>";
                    return "<span class='label label-success radius'>启用</span>" + op;
                } else {
                    var op = "&nbsp;&nbsp;&nbsp;&nbsp;<a title='启用' class='ml-5' style='text-decoration:none' href='javascript:void(0);' onclick='changeAccountStatus(" + row['id'] + ")'><i class='Hui-iconfont'><font color='green'>&#xe605;</font></i></a>";
                    return "<span class='label label-warning radius'>停用</span>" + op;
                }
            }, "targets": 7}
        ]
    });

    function getSave(id) {
        var index = layer.open({
            type: 2,
            title: "帐号编辑",
            content: "<%=context%>/static/system/account/save?id=" +id
        });
        layer.full(index);
    }

    function resetpwd(id) {
        layer.confirm('确认要重置密码吗？',function(index){
            ajaxPost("<%=context%>/system/account/resetpwd", {"id":id},
                    function (msg) {
                        if(msg == 0) {
                            layer.msg("重置成功",{icon:1,time:1000});
                            reload();
                        } else {
                            layer.msg("重置失败",{icon:2,time:1000});
                        }
                    }
            );
        });
    }

    function del(id) {
        layer.confirm('确认要删除吗？',function(index){
            ajaxPost("<%=context%>/system/account/del",
                {"id":id},
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

    function changeAccountStatus(id) {
        ajaxPost("<%=context%>/system/account/changeAccountStatus", "id="+id, function(msg) {
            if(msg == -1) {
                layer.msg('超级管理员不准禁用',{icon: 2,time:800});
            } else if(msg == 0) {
                layer.msg('修改成功',{icon: 1,time:800});
                reload();
            } else {
                layer.msg('修改错误',{icon: 2,time:800});
            }
        });
    }

</script>
</body>
</html>
