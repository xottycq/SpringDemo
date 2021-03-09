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
<%
    String title = request.getParameter("mytitle");
%>

<h3><%=title%></h3>
<%if(title.equals("转账1")){%>
<script>
    var myurl="http://localhost:8080/transfer1";
</script>
<%}else{%>
<script>
    myurl="http://localhost:8080/transfer2";
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
    转出用户姓名: <input id="outname" type="text" name="outname" onfocus="clearresult(this.id)"><br>
    转入用户姓名: <input id="inname" type="text" name="inname" onfocus="clearresult(this.id)"><br>
    金额:&ensp;&nbsp; <input id="money" type="number" name="money" onfocus="clearresult(this.id)"><br>
    <br/>
    <input id="mysummit" type="submit" value="提交">
</form>
<script>
    function clearresult(id){
        $("#result, window.parent.parent.document").html("");
    }
$(function () {
    // $('#myform').on('submit', function(e) {
    //     e.preventDefault(); // prevent native submit
    //     $(this).ajaxSubmit({
    //         method:"POST",
    //         data: { "key1":'value1', "key2": 'value2' },
    //         clearForm:true,
    //         success:function(data,status){
    //             $("#result, window.parent.parent.document").html(data)
    //         },
    //     })
    // });

    $('#mysummit').click(function(e){
        e.preventDefault();
        var sdata=JSON.stringify({"outuser":{"name":$("#outname").val(),"age":40},"inuser":{"name":$("#inname").val(),"age":40},"money":parseInt($("#money").val())});

        $.ajax({
            //请求方式
            type: "post",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            dataType: "text",
            //请求地址
            url: myurl,
            data: sdata,    //数据，json字符串
            //请求成功
            success: function (result) {
                // if (this.dataType == "json") result = JSON.stringify(result);
                $("#result, window.parent.parent.document").html(result)
                $("#myform")[0].reset()
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                alert("error")
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    })
});
</script>
</body>
</html>