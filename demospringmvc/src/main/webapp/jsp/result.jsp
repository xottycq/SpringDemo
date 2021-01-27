<%@ page import="com.example.demospringmvc.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring MVC Demo<</title>
    <script src="${pageContext.request.contextPath}/js/jquery/http_cdn.bootcdn.net_ajax_libs_jquery_1.9.1_jquery.js"></script>
</head>
<body>
<input type="hidden" id="oper" value=${operation}>
<p>${operation}:${result}</p>
<br/>
<div id="queryresult">
</div>
<%
    List<User> ulist= (List<User>)request.getAttribute("users");
    session.setAttribute("users",ulist);
%>
<script>
    $(function(){
        if ($("#oper").val() == "查询用户") {
            $("#queryresult").load("${pageContext.request.contextPath}/jsp/userlist.jsp")
        }
    })
</script>
</body>
</html>