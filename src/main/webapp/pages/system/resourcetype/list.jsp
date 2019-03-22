<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../include/include.jsp" %>
<script type="text/javascript">
    $(document).ready(function (e) {
        $(".select1").uedSelect({
            width: 345
        });
        $(".select2").uedSelect({
            width: 167
        });
        $(".select3").uedSelect({
            width: 100
        });
    });
</script>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="<%=context%>/system/index">首页</a></li>
        <li><a href="#">资源类型</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div>
            <div class="tools">
                <ul class="toolbar1">
                    <li class="click"><a href="<%=context%>/system/resourcetype/getSave?"><span><img src="<%=context%>/pageslayout/images/t01.png" /></span>添加</a></li>
                </ul>
            </div>
            <table class="tablelist" border="1px">
                <thead>
                <tr>
                    <th>编号<i class="sort"><img src="<%=context%>/pageslayout/images/px.gif"/></i></th>
                    <th>名称</th>
                    <th>顺序</th>
                    <th>图标</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="entity" items="${entities}">
                    <tr>
                        <td>${entity.id}</td>
                        <td>${entity.name}</td>
                        <td>${entity.order}</td>
                        <td><img src="<%=context%>/pageslayout/images/${entity.icon}" /></td>
                        <td>
                            <a href="<%=context%>/system/resourcetype/getSave?id=${entity.id}" class="tablelink">编辑</a>
                            <a href="<%=context%>/system/resourcetype/del?id=${entity.id}" class="tablelink"> 删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

<%@ include file="../../include/page.jsp" %>
