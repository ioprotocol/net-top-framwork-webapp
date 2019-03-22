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
        <li><a href="#">系统资源</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div>
            <div class="tools">
                <form class="seachform" style="float: left;" id="searchForm" name="searchForm" action="/system/resource/list" method="post">
                    <ul>
                        <li><label>资源类型</label>
                            <div class="vocation">
                                <select class="select3" id="pageParamResourceTypeId" name="pageParamResourceTypeId">
                                    <option value=""></option>
                                    <c:forEach var="type" items="${types}" >
                                        <option value="${type.id}" <c:if test="${type.id==pageParamResourceTypeId}">selected</c:if> >${type.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </li>
                        <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="查询"/></li>
                    </ul>
                </form>
                <ul class="toolbar1">
                    <li class="click"><a href="/system/resource/getSave"><span><img src="<%=context%>/pageslayout/images/t01.png"/></span>添加</a></li>
                </ul></div>
            <table class="tablelist" border="1px">
                <thead>
                <tr>
                    <th>资源编码</th>
                    <th>资源类型</th>
                    <th>资源名称</th>
                    <th>访问地址</th>
                    <th>是否显示</th>
                    <th>显示顺序</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="entity" items="${entities}">
                    <tr>
                        <td>${entity.number}</td>
                        <td>${entity.resourceType.name}</td>
                        <td>${entity.name}</td>
                        <td>${entity.url}</td>
                        <td>
                            <c:if test="${entity.isShow==1}">是</c:if>
                            <c:if test="${entity.isShow==0}">否</c:if>
                        </td>
                        <td>${entity.order}</td>
                        <td>
                            <a href="<%=context%>/system/resource/getSave?number=${entity.number}" class="tablelink">编辑</a>
                            <a href="<%=context%>/system/resource/del?pageParamResourceTypeId=${pageParamResourceTypeId}&number=${entity.number}" class="tablelink"> 删除</a>
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
