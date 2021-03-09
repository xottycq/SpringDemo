<%@ page import="com.example.demospringbootwar.domain.User" %>
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
</body>
</html>