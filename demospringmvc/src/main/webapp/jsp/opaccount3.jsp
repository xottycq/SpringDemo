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
<%if(title.equals("转账1")){%>
<script>
    document.myform.action="http://localhost:8080/springmvc/transfer1";
</script>
<%}else{%>
<script>
    document.myform.action="http://localhost:8080/springmvc/transfer2";
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
<script>
// function preprocess(){
//
// var  inname = document.getElementById("inname").value;
// var  outname = document.getElementById("outname").value;
//     var  money = document.getElementById("money").value;
//     alert(inname+"----"+outname+"===="+money)
// if(inname ==  null || inname == ''||outname ==  null || outname == ''){
//       alert("用户名不能为空");
//       return false;
// }
//     // var s=JSON.stringify({"outuser":{"name":outname,"age":40},"inuser":{"name":inname,"age":40},"money":money});
//
//     $("#outname").val("xxx");
//     $("#inname").val("yyy");
//     alert( document.getElementById("inname").value)
//     alert( document.getElementById("outname").value)
// return true;
// }
</script>
<form id="myform" name="myform"  action="" method="post">
    转出用户姓名: <input id="outname" type="text" name="outname" ><br>
    转入用户姓名:<input id="inname" type="text" name="inname" ><br>
    金额:&ensp;&nbsp; <input id="money" type="number" name="money"><br>
    <input type="submit" value="提交">
</form>
<script>
$(function () {
    $('#myform').on('submit', function(e) {
        e.preventDefault(); // prevent native submit
        $(this).ajaxSubmit({
            method:"POST",
            data: { "key1":'value1', "key2": 'value2' },
            clearForm:true,
            success:function(data,status){
                $("#result, window.parent.parent.document").html(data)
            },
        })
    });
});
</script>
</body>
</html>