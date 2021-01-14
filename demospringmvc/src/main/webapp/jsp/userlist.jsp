<%@ page import="com.example.demospringmvc.pojo.User" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring Controller Demo<</title>
</head>
<body>
<h1>Spring Controller Demo</h1>
<table id="tb" border="6">
    <caption>User</caption>
    <tr>
        <th>Name</th>
        <th>Age</th>
    </tr>
    <%--JSP直接输出--%>
<%--    <%--%>
<%--        String flag = (String) request.getAttribute("flag");--%>
<%--        if (flag.equals("array")) {--%>
<%--            String[] users1 = (String[]) request.getAttribute("users");--%>
<%--            for (String user : users1) {--%>

<%--    %>--%>
<%--    <tr>--%>
<%--        <td><%=user%>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--    <%--%>
<%--        }--%>
<%--    } else {--%>
<%--        List<User> users2 = (List<User>) request.getAttribute("users");--%>
<%--        for (User user : users2) {--%>
<%--    %>--%>
<%--    <tr>--%>
<%--        <td><%=user.getName()%>--%>
<%--        </td>--%>
<%--        <td><%=user.getAge()%>--%>
<%--        </td>--%>
<%--    </tr>--%>

<%--    <%--%>
<%--            }--%>
<%--        }--%>
<%--    %>--%>
    <%--JSP标签--%>
    <c:forEach items="${users}" var="item">
        <c:if test="${flag=='array'}">
            <tr>
                <td>${item}</td>
            </tr>
        </c:if>
        <c:if test="${flag=='list'}">
            <tr>
                <td>${item.name}</td>
                <td>${item.age}</td>
            </tr>
        </c:if>
    </c:forEach>

</table>
</body>
</html>