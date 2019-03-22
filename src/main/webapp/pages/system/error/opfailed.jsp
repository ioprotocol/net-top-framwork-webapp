<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../include/include.jsp"%>
<body style="background:#edf6fa;">
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="<%=context%>/system/index">首页</a></li>
        <li><a href="#">600错误提示</a></li>
    </ul>
</div>
<div class="error">
    <h2>非常遗憾，您没有该项操作权限</h2>
    <p>看到这个提示，就自认倒霉吧!</p>
    <div class="reindex"><a href="<%=context%>/system/index" target="rightFrame">返回首页</a></div>
</div>
</body>
