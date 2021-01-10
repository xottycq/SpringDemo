<%@ page import="com.example.demospringmvc.pojo.UserList" %>
<%@ page import="com.example.demospringmvc.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="Spring MVC Demo">
    <title>Spring Controller Demo</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"/>
    <script type="text/javascript">
        document.cookie="username=JohnDoe;age=10"
    </script>
</head>
<body>
<h1>Spring Controller Demo</h1>
<br/>
<h3>RequestMapping属性</h3>
<button id="requestmap">RequestMapping</button>
<div>
    <h3>View To Controller传参</h3>

<div>

    <a href="http://localhost:8080/springmvc/controller/paratype?name=zhangsan&age=20">1:默认数据类型</a>
</div>
<div>

    <a href="http://localhost:8080/springmvc/controller/requestParam1?name=张三&age=14" >2.1:简单数据类型---ModelMap传值---返回String</a>
    <a href="http://localhost:8080/springmvc/controller/requestParam2?name=李四&age=18" style="margin-left:50px">2.2:简单数据类型---HttpServletRequest传值---返回Void</a>
    <a href="http://localhost:8080/springmvc/controller/map?name=王五&age=12" style="margin-left:50px">2.3:简单数据类型---HashMap传值---返回String</a>
</div>
<div>
    <a href="http://localhost:8080/springmvc/controller/zhangsan/10">3.1专用注解：PathVariable</a>
    <a href="http://localhost:8080/springmvc/controller/cookie" style="margin-left:50px">3.2专用注解：CookieValue</a>
    <a href="http://localhost:8080/springmvc/controller/header" style="margin-left:50px">3.3专用注解：RequestHeader</a>
    <button id="head" style="margin-left:50px">Customer Header</button>
</div>
<div>
    <a href="http://localhost:8080/springmvc/controller/pojo1?name=赵六&age=24" >4.1：POJO对象</a>
    <a href="http://localhost:8080/springmvc/controller/pojo2?user.name=王五&user.age=30&password=123456"  style="margin-left:50px">4.2：POJO嵌套</a>
</div>
    <div>
        <a href="http://localhost:8080/springmvc/controller/array1?names=zs&names=ls&names=ww">5.1: 数组--url同名参数</a>
        <button id="arr2" style="margin-left:50px">5.2: 数组--post @requestParam接收</button>
        <button id="arr3" style="margin-left:50px">5.3: 数组--post httpServletRequest接收</button>
        <button id="arr4" style="margin-left:50px">5.4: 数组--post @ResponseBody接收</button>
    </div>
    <div>
        <a href="http://localhost:8080/springmvc/controller/list1?users[0].name=zs&users[1].name=ls&users[2].name=ww&users[0].age=20">6.1: 集合--url参数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
        <button id="list2"  style="margin-left:50px">6.2: 集合---@RequestBody List< User ></button>
        <button id="list3"  style="margin-left:28px">6.3: 集合---@RequestBody UserList</button>
    </div>
</div>
<div>
    <h3> Controller To View 传参</h3>
    <div>
        <h4>一、常用</h4>
        <a href="http://localhost:8080/springmvc/controller/mv" >1:return ModelAndView</a>
        <a href="http://localhost:8080/springmvc/controller/pojo2?user.name=王五&user.age=30&password=123456"  style="margin-left:50px">2:return Map</a>
        <a href="http://localhost:8080/springmvc/controller/hello?para=World" style="margin-left:50px">3:return String</a>
        <a href="http://localhost:8080/springmvc/controller/requestParam2?name=李四&age=18" style="margin-left:50px">3:return Void</a>
    </div>

<div>
    <h5> 二、ModelAttribute</h5>
    <a href="http://localhost:8080/springmvc/controller/hello?para=World">ModelAttribute1</a>
    <a href="http://localhost:8080/springmvc/controller/hello?para=China">ModelAttribute2</a>
    <a href="http://localhost:8080/springmvc/controller/user1?name=zhangsan">ModelAttribute3</a>
    <a href="http://localhost:8080/springmvc/controller/user2?name=lisi&age=20">ModelAttribute4</a>
    <a href="http://localhost:8080/springmvc/controller/user3?name=zhaoliu&age=30">ModelAttribute5</a>
    <a href="http://localhost:8080/springmvc/controller/hellospring">ModelAttribute6</a>
</div>
<div>
    <h5> 三、SessionAttribute</h5>
    <a href="http://localhost:8080/springmvc/controller/getUser1">SessionAttribute1</a>
    <a href="http://localhost:8080/springmvc/controller/getUser2">SessionAttribute2</a>
    <a href="http://localhost:8080/springmvc/controller/getUser3">SessionAttribute3</a>
</div>

    <div>
        <h5> 四、Json</h5>
        <button id="requestbody1">1.Json--RequestBody1</button>
        <button id="requestbody2">2.Json--RequestBody2</button>
    </div>
</div>
<div id="headresult">
</div>

<script type="text/javascript">
    document.getElementById("head").onclick= function(){
    var xmlhttp = new XMLHttpRequest();
      xmlhttp.onreadystatechange = function(){
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
        document.getElementById("headresult").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "http://localhost:8080/springmvc/controller/header", true);
    xmlhttp.setRequestHeader('Content-Type', 'application/json')
    xmlhttp.setRequestHeader("token","header-token-value");
    xmlhttp.setRequestHeader("customer","my_header-value");
    xmlhttp.send();
   };
</script>
<script>
function myasync(myurl,str){
		$.ajax({
		//请求方式
            type : "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url :myurl,
            //数据，json字符串
            data : JSON.stringify(str),
            //请求成功
            success : function(result) {
            alert("success")
                $("#headresult").html(result)
            },
            //请求失败，包含具体的错误信息
            error : function(e){
              alert("error")
                console.log(e.status);
                console.log(e.responseText);
            }
            });
}

$(document).ready(function(){
    var json1 = {"name":"zhangsan","age":20};
    var json2 = {"name":"lisi","age":30};
	$("#requestbody1").click(function(){
        myasync("http://localhost:8080/springmvc/controller/post1",json1);
	});
		$("#requestbody2").click(function(){
        myasync("http://localhost:8080/springmvc/controller/post2",json2);
	});

    $("#requestmap").click(function(){
        $.ajax({
		//请求方式
            type : "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url :"http://localhost:8080/springmvc/controller/requestmap?action=insert",
            //数据，json字符串
            data : JSON.stringify({name:"zhangsan",age:20}),
            headers:{
                Accept: "application/json; charset=utf-8",
                Host:"localhost:8080"
                },
            //请求成功
            success : function(result) {
                $("#headresult").html(result)
            },
            //请求失败，包含具体的错误信息
            error : function(e){
                alert("error")
                console.log(e.status);
                console.log(e.responseText);
            }

            });
    });
});
</script>
<script>
    var userNames=["张三","李四","王五"];
    function f(num){
        $.ajax({
            //请求方式
            type : "POST",
            //请求地址
            url :"http://localhost:8080/springmvc/controller/array"+num,
            //数据，json字符串
            data :{ names:userNames},
            //请求成功
            success : function(result) {
                $("#headresult").html(result);
            },
            //请求失败，包含具体的错误信息
            error : function(e){
                alert("error")
                console.log(e.status);
                console.log(e.responseText);
            }

        });
    }
    $("#arr2").click(function(){
        f(2)
    });
    $("#arr3").click(function(){
        f(3)
    });

    var dataArr=[{"name":"zhangsan","age":10},{"name":"lisi","age":12}];
    var params = JSON.stringify(dataArr);
    $("#arr4").click(function() {
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/springmvc/controller/array4",
            data: params,
            success: function (data, status) {
                alert("数据:"+ data + "状态: " + status);
            },
            dataType: 'json',
            contentType: 'application/json'
        });
    });

    var users = new Array();
    users.push({name: "张三",age: 22});
    users.push({name: "李四",age: 12});
    var userList={};
    userList.users=users;
    $("#list2").click(function() {
    listfun(2);
    });

    $("#list3").click(function() {
        listfun(3);
    });


   function listfun(num) {
       var datas;
       if(num==2)
           datas=users;
       else
           datas=userList;
       $.ajax({
           type: "POST",
           url: "http://localhost:8080/springmvc/controller/list" + num,
           data: JSON.stringify(datas),  //将对象序列化成JSON字符串
           dataType: "json",
           contentType: 'application/json;charset=utf-8', //设置请求头信息
           success: function (data, status) {
               alert("数据:" + data + "状态: " + status);
           },
           //请求失败，包含具体的错误信息
           error: function (e) {
               alert("error")
               console.log(e.status);
               console.log(e.responseText);
           }
       });
   }

</script>
</body>
</html>
