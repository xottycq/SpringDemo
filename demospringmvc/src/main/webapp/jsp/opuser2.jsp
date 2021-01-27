<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
    var title = $("#data").val();
    if (title=="删除")
    document.myform.action="http://localhost:8080/springmvc/deleteuser";
    else
    document.myform.action="http://localhost:8080/springmvc/queryuser";

    document.getElementById("pt").innerHTML = title;
</script>
<p id="pt">title</p>


<form name="myform" id="myform" action="">
    Name: <input type="text" name="name" ><br/><br/>
    <input type="submit" value="提交">
</form>
<script>
$(function () {
    $('#myform').on('submit', function(e) {
        e.preventDefault();
        $(this).ajaxSubmit({
            clearForm:true,
            success:function(data){
                // alert("hahahaha----"+data)
                $("#result, window.parent.parent.document").html(data)
            },
            error:function(data){alert("error----"+data)}
        })
    });
});
</script>
</body>
</html>