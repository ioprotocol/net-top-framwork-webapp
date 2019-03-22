<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../include/include.jsp" %>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="<%=context%>/system/index">首页</a></li>
        <li><a href="#">备份文件上传</a></li>
    </ul>
</div>
<div class="formbody">
    <form action="<%=context%>/system/mainten/upload" method="post" enctype="multipart/form-data">
        <div class="formtitle"><span>基本信息</span></div>
        <ul class="forminfo">
            <li><label>名称</label><input id="file" name="file" type="file" /></li>
            <li><label>&nbsp;</label><input type="submit" class="btn"/></li>
        </ul>
    </form>
</div>

