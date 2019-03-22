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
        <li><a href="#">系统备份</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div>
            <div class="tools">
                <ul class="toolbar1">
                    <li class="click"><a href="<%=context%>/system/mainten/dbbackup"><span><img src="<%=context%>/pageslayout/images/t01.png"/></span>备份</a></li>
                    <li><a href="<%=context%>/system/mainten/toupload"><span><img src="<%=context%>/pageslayout/images/t05.png"/></span>上传</a></li>
                    <li><a href="<%=context%>/system/mainten/clearAll"><span><img src="<%=context%>/pageslayout/images/t03.png"/></span>清空</a></li>
                </ul>
            </div>
            <table class="tablelist" border="1px">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>大小</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="entity" items="${entities}">
                    <tr>
                        <td>${entity.name}</td>
                        <td>${entity.size}</td>
                        <td>
                            <a href="<%=context%>/system/mainten/dbrestore?name=${entity.name}" class="tablelink">还原</a>
                            <a href="<%=context%>/system/mainten/down?name=${entity.name}" class="tablelink">下载</a>
                            <a href="<%=context%>/system/mainten/del?name=${entity.name}" class="tablelink"> 删除</a>
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
