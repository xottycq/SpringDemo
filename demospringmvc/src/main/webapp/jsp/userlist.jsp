<%@ page import="com.example.demospringmvc.pojo.User" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring Controller Demo<</title>
</head>
<body>
<h1>Spring Controller Demo</h1>
<table border="6">
    <caption>User</caption>
    <tr>
        <td>Name</td>
        <td>Age</td>
    </tr>
    <%
        List<User> users = new ArrayList<User>();
        for (User user : users) {
    %>
    <tr>
        <td><%=user.getName() %>
        </td>
        <td><%=user.getAge() %>></td>
    </tr>
    <%
        }
    %>

<%--    <c:forEach items="${userlist}" var="item" >--%>
<%--        <tr>--%>
<%--            <td>${item.name}</td>--%>
<%--            <td>${item.age}</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>

</table>
</body>
</html>