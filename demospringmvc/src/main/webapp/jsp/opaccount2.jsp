<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery.form/4.3.0/jquery.form.js"></script>
</head>
<body>
<%--JSP实现--%>
<%
    String title = request.getParameter("mytitle");
%>
<p ><%=title%></p>
<%if(title.equals("存款")){%>
<script>
    document.myform.action="http://localhost:8080/springmvc/save";
</script>
<%}else{%>
<script>
    document.myform.action="http://localhost:8080/springmvc/withdraw";
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


<form name="myform" id="myform" action="" method="post">
    <label for="name">Name:&nbsp; </label>
    <input id="name" name="name" type="text">
    <br>

    Money:<input id="money" name="money" type="number"><br>
    <input id="mysummit" type="submit" value="提交">
</form>
<script>
$(function () {
    // $('form').ajaxForm({
    //     data: { "name":'ls', "money": 20 },
    //     success: function () {
    //         alert("xxxresultx")
    //     }
    // })

    $('#myform').on('submit', function(e) {
        e.preventDefault(); // prevent native submit
        $(this).ajaxSubmit({
            clearForm:true,
            success:function(data){
                $("#result, window.parent.parent.document").html(data)
           },
        })
    });
});
</script>
</body>
</html>