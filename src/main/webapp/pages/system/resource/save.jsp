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
    <form:form action="/system/resource/save" commandName="resourceForm" method="post">
        <div class="formtitle"><span>基本信息</span></div>
        <ul class="forminfo">
            <li><label>编码</label><form:input path="number" cssClass="dfinput" id="resourceForm.number" name="resourceForm.number"/><font color="red"><form:errors path="number"/></font></li>
            <li><label>名称</label><form:input path="name" cssClass="dfinput" id="resourceForm.name" name="resourceForm.name"/></li>
            <li><label>地址</label><form:input path="url" cssClass="dfinput" id="resourceForm.url" name="resourceForm.url"/></li>
            <li><label>是否显示</label>
                <cite>
                    <form:radiobutton path="isShow" name="resourceForm.isShow" id="resourceForm.isShow" value="1" />是
                    <form:radiobutton path="isShow" name="resourceForm.isShow" id="resourceForm.isShow" value="0" />否
                </cite>
            </li>
            <li><label>显示顺序</label><form:input path="order" cssClass="dfinput" id="resourceForm.order" name="resourceForm.order"/></li>
            <li><label>所属类型</label>
                <div class="vocation">
                    <form:select path="resourceTypeId" cssClass="select1" >
                        <form:options items="${types}" itemLabel="name" itemValue="id"/>
                    </form:select>
                </div>
            </li>
            <li><label>&nbsp;</label><input type="submit" class="btn"/></li>
        </ul>
    </form:form>
</div>

