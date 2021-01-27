<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<%--    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.js"></script>--%>
<%--    <script src="https://cdn.bootcdn.net/ajax/libs/jquery.form/4.3.0/jquery.form.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/jquery/http_cdn.bootcdn.net_ajax_libs_jquery_1.9.1_jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery/http_cdn.bootcdn.net_ajax_libs_jquery.form_4.3.0_jquery.form.js"></script>
</head>
<body>
<%--JSP实现--%>
<%--<%--%>
<%--    String title = request.getParameter("mytitle");--%>
<%--%>--%>
<%--<p ><%=title%></p>--%>
<%--<%if(title.equals("新增用户")){%>--%>
<%--<script>--%>
<%--    document.myform.action="http://localhost:8080/springmvc/openaccount";--%>
<%--</script>--%>
<%--<%}else{%>--%>
<%--<script>--%>
<%--    document.myform.action="http://localhost:8080/springmvc/closeaccount";--%>
<%--</script>--%>
<%--<%}%>--%>

<%--等效JS实现--%>
<input type="hidden" id="data" value="<%=request.getParameter("mytitle")%>"/>
<script>
    let title = $("#data").val();
    if (title=="增加")
    document.myformx.action="http://localhost:8080/springmvc/adduser";
    else
    document.myformx.action="http://localhost:8080/springmvc/updateuser";

    document.getElementById("pt").innerHTML = title;
</script>
<p id="pt">title</p>
<form name="myformx" id="myformx" action="">
    Name: <input type="text" name="name" ><br/>
    Age:&ensp;&nbsp; <input type="number" name="age"><br/><br/>
    <input type="submit" value="提交">
</form>
<script>
$(function () {
    $("#myformx").on('submit', function(e) {
        e.preventDefault();
        $(this).ajaxSubmit({
            clearForm:true,
            success:function(data){
                $("#result, window.parent.parent.document").html(data)
            }
        })
    });
});
</script>
</body>
</html>