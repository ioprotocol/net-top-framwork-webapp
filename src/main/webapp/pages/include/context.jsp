<%
    String context="http://";
    if(request.isSecure()){
        context="https://";
    }
    context += request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
