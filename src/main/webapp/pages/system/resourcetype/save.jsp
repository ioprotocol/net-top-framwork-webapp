<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../include/include.jsp" %>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="<%=context%>/system/index">首页</a></li>
        <li><a href="#">资源类型</a></li>
    </ul>
</div>
<div class="formbody">
    <form:form action="/system/resourcetype/save" commandName="resourceType" method="post">
        <div class="formtitle"><span>基本信息<form:hidden path="id" /></span></div>
        <ul class="forminfo">
            <li><label>名称</label><form:input path="name" cssClass="dfinput" id="resourceType.name" name="resourceType.name" /><font color="red"><form:errors path="name" /></font></li>
            <li><label>显示顺序</label><form:input path="order" cssClass="dfinput" id="resourceType.order" name="resourceType.order" /></li>
            <li><label>图标</label><form:input path="icon" cssClass="dfinput" id="resourceType.icon" name="resourceType.icon" /></li>
            <li><label>&nbsp;</label><input type="submit" class="btn" /></li>
        </ul>
    </form:form>
</div>

