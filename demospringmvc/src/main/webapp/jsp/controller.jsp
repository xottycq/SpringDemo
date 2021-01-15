<%@ page import="com.example.demospringmvc.pojo.UserList" %>
<%@ page import="com.example.demospringmvc.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="Spring MVC Demo">
    <title>Spring Controller Demo</title>
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"/>
    <script type="text/javascript">
        document.cookie = "uname=JohnDoe"
        document.cookie = "age=20"
    </script>
    <script>
        // 判断是否填写上传人并已选择上传文件
        function check() {
            var name = document.getElementById("name").value;
            var file = document.getElementById("file").value;
            if (name == "") {
                alert("填写上传人");
                return false;
            }
            if (file.length == 0 || file == "") {
                alert("请选择上传文件");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<div  class="leftbox left">
<h4>RequestMapping属性</h4>
<button id="requestmap">RequestMapping</button>
<div>
    <h4>View To Controller传参</h4>
    <div>
        <a href="http://localhost:8080/springmvc/controller/paratype?name=zhangsan&age=20">1:默认数据类型</a>
    </div>
    <div >
        <a href="http://localhost:8080/springmvc/controller/requestParam1?name=张三&age=14">2.1:简单数据类型---ModelMap传值</a>
        <a href="http://localhost:8080/springmvc/controller/requestParam2?name=李四&age=18" class="marginleft">2.2:简单数据类型---HttpServletRequest传值</a>
        <a href="http://localhost:8080/springmvc/controller/map?name=王五&age=12" class="marginleft">2.3:简单数据类型---HashMap传值</a>
    </div>
    <div>
        <a href="http://localhost:8080/springmvc/controller/zhangsan/10">3.1专用注解：PathVariable</a>
        <a href="http://localhost:8080/springmvc/controller/cookie" class="marginleft">3.2专用注解：CookieValue</a>
        <a href="http://localhost:8080/springmvc/controller/header" class="marginleft">3.3专用注解：RequestHeader</a>
        <button id="head" class="marginleft">Customer Header</button>
    </div>
    <div>
        <a href="http://localhost:8080/springmvc/controller/pojo1?name=赵六&age=24">4.1：POJO对象</a>
        <a href="http://localhost:8080/springmvc/controller/pojo2?user.name=王五&user.age=30&password=123456"
           class="marginleft">4.2：POJO嵌套</a>
    </div>
    <div>
        <a href="http://localhost:8080/springmvc/controller/array1?names=zs&names=ls&names=ww">5.1: 数组--url同名参数</a>
        <button id="arr2" class="marginleft">5.2: 数组--post @requestParam接收</button>
        <button id="arr3" class="marginleft">5.3: 数组--post httpServletRequest接收</button>
        <button id="arr4" class="marginleft">5.4: 数组--post @ResponseBody接收</button>
    </div>
    <div>
        <a href="http://localhost:8080/springmvc/controller/list1?users[0].name=zs&users[1].name=ls&users[2].name=ww&users[0].age=20">6.1:
            集合--url参数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
        <button id="list2" class="marginleft">6.2: 集合---@RequestBody List< User ></button>
        <button id="list3" style="margin-left:24px">6.3: 集合---@RequestBody UserList</button>
    </div>
</div>
<div>
    <h4> Controller To View 传参</h4>
    <div>
        <h5>一、常用</h5>
        <a href="http://localhost:8080/springmvc/controller/mv">1:return ModelAndView</a>
        <a href="http://localhost:8080/springmvc/controller/map?name=王五&age=30"
           class="marginleft">2:return Map</a>
        <a href="http://localhost:8080/springmvc/controller/hello?para=World" class="marginleft">3:return
            String</a>
        <a href="http://localhost:8080/springmvc/controller/requestParam2?name=李四&age=18" class="marginleft">3:return
            Void</a>
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
        <a href="http://localhost:8080/springmvc/controller/getUser2" class="marginleft">SessionAttribute2</a>
        <a href="http://localhost:8080/springmvc/controller/getUser3" class="marginleft">SessionAttribute3</a>
    </div>
    <div>
        <h5> 四、Json</h5>
        <button id="requestbody1">1.Json--RequestBody1</button>
        <button id="requestbody2" class="marginleft">2.Json--RequestBody2</button>
    </div>
    <h5> 五、upload/download</h5>
    <div>

        <div class="box left">
        <form action="controller/fileUpload" method="post" enctype="multipart/form-data">
            上传人：<input type="text" name="name"/><br/>
            请选择文件：<input type="file" name="uploadfile" multiple="multiple"/><br/>
            <input type="submit" value="上传"/>
        </form>
        </div>
        <div class="box right">
        <a href="${pageContext.request.contextPath }/controller/download?filename=001.jpg">
            文件下载
        </a>
        <br/>
        <a href="${pageContext.request.contextPath }/controller/downloadcn?filename=壁纸.jpg">
            中文名称文件下载
        </a>
        </div>
    </div>
</div>
</div>
<div class="rightbox right" >
<h2 align="center">Spring Controller Demo Result</h2>
<div id="headresult"/>
</div>
<script type="text/javascript">
    document.getElementById("head").onclick = function () {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("headresult").innerHTML = xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET", "http://localhost:8080/springmvc/controller/header", true);
        xmlhttp.setRequestHeader('Content-Type', 'application/json')
        xmlhttp.setRequestHeader("token", "header-token-value");
        xmlhttp.setRequestHeader("customer", "my_header-value");
        xmlhttp.send();
    };
</script>
<script>
    function myasync(myurl, str) {
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: myurl,
            //数据，json字符串
            data: JSON.stringify(str),
            //请求成功
            success: function (result) {
                // alert("success")
                $("#headresult").html(result)
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                alert("error")
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    $(document).ready(function () {
        var json1 = {"name": "zhangsan", "age": 20};
        var json2 = {"name": "lisi", "age": 30};
        $("#requestbody1").click(function () {
            myasync("http://localhost:8080/springmvc/controller/post1", json1);
        });
        $("#requestbody2").click(function () {
            myasync("http://localhost:8080/springmvc/controller/post2", json2);
        });

        $("#requestmap").click(function () {
            $.ajax({
                //请求方式
                type: "POST",
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url: "http://localhost:8080/springmvc/controller/requestmap?action=insert",
                //数据，json字符串
                data: JSON.stringify({name: "zhangsan", age: 20}),
                headers: {
                    Accept: "application/json; charset=utf-8",
                    Host: "localhost:8080"
                },
                //请求成功
                success: function (result) {
                    $("#headresult").html(result)
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    alert("error")
                    console.log(e.status);
                    console.log(e.responseText);
                }

            });
        });
    });
</script>
<script>
    var userNames = ["张三", "李四", "王五"];

    function f(num) {
        $.ajax({
            //请求方式
            type: "POST",
            //请求地址
            url: "http://localhost:8080/springmvc/controller/array" + num,
            //数据，json字符串
            data: {names: userNames},
            //请求成功
            success: function (result) {
                $("#headresult").html(result);
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                alert("error")
                console.log(e.status);
                console.log(e.responseText);
            }

        });
    }

    $("#arr2").click(function () {
        f(2)
    });
    $("#arr3").click(function () {
        f(3)
    });

    var dataArr = [{"name": "zhangsan", "age": 10}, {"name": "lisi", "age": 12}];
    var params = JSON.stringify(dataArr);
    $("#arr4").click(function () {
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/springmvc/controller/array4",
            data: params,
            success: function (data, status) {
                // alert("数据:" + data + "状态: " + status);
                var tr = "";
                $.each(data, function (index, item) {
                    //等效js替代 data.forEach(function(item){
                    tr += '<tr>';
                    tr += '<td>' + item.name + '</td>' + '<td>' + item.age + '</td>';
                    tr += '</tr>';
                });
                $("#headresult").append('<table border="6">' + tr + '</table>');
            },
            dataType: 'json',
            contentType: 'application/json'
        });
    });

    var users = new Array();
    var userList = {};
    userList.users = users;
    $("#list2").click(function () {
        users.splice(0, users.length);
        users.push({name: "张三", age: 22});
        users.push({name: "李四", age: 12});
        listfun(2);
    });

    $("#list3").click(function () {
        users.push({name: "王五", age: 20});
        users.push({name: "赵六", age: 10});
        listfun(3);
    });


    function listfun(num) {
        var datas;
        if (num == 2)
            datas = users;
        else
            datas = userList;
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/springmvc/controller/list" + num,
            data: JSON.stringify(datas),  //将对象序列化成JSON字符串
            dataType: "json",
            contentType: 'application/json;charset=utf-8', //设置请求头信息
            success: function (data, status) {

                // alert("数据:" + JSON.stringify(data) + "状态: " + status);
                str_tab = '<table border="6">';
                if (num == 2) {

                    for (var k in data) {//遍历Json 对象的每个key/value对,k为key
                        str_tab += '<tr>'
                        str_tab += '<td>' + data[k].name + '</td>'
                        str_tab += '<td>' + data[k].age + '</td>'
                        console.log(data[k].name);
                        console.log(k);
                        str_tab += '</tr>'
                    }
                } else {
                    var datas = data.users;
                    for (var i in datas) {//遍历packJson 数组时，i为索引
                        str_tab += '<tr>'
                        str_tab += '<td>' + datas[i].name + '</td>'
                        str_tab += '<td>' + datas[i].age + '</td>'
                        console.log(i);
                        str_tab += '</tr>'
                    }
                }
                str_tab += '</table>'
                $("#headresult").html(str_tab);
                //document.write(str_tab)
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
