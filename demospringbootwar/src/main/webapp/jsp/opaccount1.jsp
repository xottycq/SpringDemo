<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SpringBoot Web Demo</title>
<%--    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.js"></script>--%>
<%--    <script src="https://cdn.bootcdn.net/ajax/libs/jquery.form/4.3.0/jquery.form.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/jquery/http_cdn.bootcdn.net_ajax_libs_jquery_1.9.1_jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery/http_cdn.bootcdn.net_ajax_libs_jquery.form_4.3.0_jquery.form.js"></script>
</head>
<body>
<div>
<%--JSP实现--%>
<%
    String title = request.getParameter("mytitle");
%>
<h3><%=title%></h3>
<%if(title.equals("开户")){%>
<script>
    document.myform.action="http://localhost:8080/openaccount";
</script>
<%}else{%>
<script>
    document.myform.action="http://localhost:8080/closeaccount";
</script>
<%}%>

<%--等效JS实现--%>
<%--<input type="hidden" id="data" value="<%=request.getParameter("mytitle")%>"/>--%>
<%--<script>--%>
<%--    var title = $("#data").val();--%>
<%--    alert(title)--%>
<%--    if (title=="开户")--%>
<%--    document.myform.action="http://localhost:8080/springmvc/openaccount";--%>
<%--    else--%>
<%--    document.myform.action="http://localhost:8080/springmvc/closeaccount";--%>
<%--    document.getElementById("pt").innerHTML = title;--%>
<%--</script>--%>
<%--<p id="pt">title</p>--%>


<form name="myform" id="myform" action="">
    Name: <input type="text" name="name" onfocus="clearresult(this.id)"><br/>
    Age:&ensp;&nbsp; <input type="number" name="age" onfocus="clearresult(this.id)"><br/>
    <br/>
    <input type="submit" value="提交">
</form>
</div>
<script>
    function clearresult(id){
        $("#result, window.parent.parent.document").html("");
    }
$(function () {
    // $('#myform').on('submit', function(e) {
    $('#myform').submit(function () {
        // e.preventDefault();   // prevent native submit
        $('#myform').ajaxSubmit({
            clearForm:true,
            async:true,
            success:function(data){
                $("#result, window.parent.parent.document").html(data)
                $("#detail, window.parent.parent.document").html("")
            },
            error: function (e) {
                alert(e)
            }
        })
        return false;
    });
});
</script>
</body>
</html>