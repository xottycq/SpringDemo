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
    <style>
        * {
            margin-left: 10px;
        }

        #rightpane {
            margin-right: 20px;
        }

        div.indent {
            padding-left: 1.0em
        }
    </style>
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

        //获取表单上传后的返回值
        $(function () {
            $("#iform").load(function () {
                var text = $(this).contents().find("body").text(); //获取到的是json的字符串
                console.log(text);
                $("#headresult").html(text);
            })
        })
    </script>
</head>
<body>

<div id="leftpane" class="leftbox left">
    <h3>RequestMapping属性</h3>
    <div class="indent">
        <button id="requestmap">RequestMapping(E1)</button>
    </div>
    <h3>View To Controller传参</h3>
    <div class="indent">
        <a href="http://localhost:8080/springmvc/controller/paratype?name=zhangsan&age=20" onclick="alert('haha')">1:URI简单参数--HttpServletRequest(E2)</a>
    </div>
    <div class="indent">
        <a href="http://localhost:8080/springmvc/controller/uriParam1?name=张三&age=14">2.1:URI简单参数(E3)</a>
        <a href="http://localhost:8080/springmvc/controller/uriParam2?name=李四&age=18" class="marginleft">2.2:URI简单参数---@RequestParam 基本类型(E4)</a>
        <a href="http://localhost:8080/springmvc/controller/uriParam3?name=李四&age=18" class="marginleft">2.3:URI简单参数---@RequestParam  HashMap(E5)</a>
    </div>
    <div class="indent">
        <a href="http://localhost:8080/springmvc/controller/pojo1?name=王五&age=12" >2.4:URI对象参数(E6)</a>
        <a href="http://localhost:8080/springmvc/controller/pojo1_1?user.name=赵六&user.age=30" class="marginleft">2.5:URI嵌套对象参数(E7)</a>
        <a href="http://localhost:8080/springmvc/controller/zhangsan/10" class="marginleft">2.6:URI路径参数---@PathVariable(E8)</a>
    </div>
    <div class="indent">
        <a href="http://localhost:8080/springmvc/controller/cookie" >3.1Cookie属性---@CookieValue(E9)</a>
        <a href="http://localhost:8080/springmvc/controller/header" class="marginleft">3.2Header属性---@RequestHeader(E10)</a>
        <button id="head" class="marginleft">Customer Header(E10)</button>
    </div>
    <div class="indent">
    <button id="requestbody1">4.1.js对象--属性值(x-www...E11)</button>
    <button id="requestbody2" class="marginleft">4.2.js对象--对象(x-www...E6)</button>
    </div>
    <div class="indent">
        <button id="requestbody0">4.3.json字符串--@RequestBody String(json...E102)</button>
        <button id="requestbody3" class="marginleft">4.4.json字符串--@RequestBody 对象(json...E12)</button>
        <button id="requestbody4" class="marginleft">4.5.json字符串--@RequestBody HashMap(json..E13)</button>
    </div>
    <div class="indent">
        <a href="http://localhost:8080/springmvc/controller/array1?names=zs&names=ls&names=ww">5.1: 数组--url同名参数(E15)&nbsp;</a>
        <button id="arr2" class="marginlefts">5.2: 数组--@requestParam接收(E16)&emsp;&emsp;&emsp;&ensp;</button>
        <button id="arr3" class="marginleft">5.3: 数组--httpServletRequest接收(E17)</button>
        <button id="arr4" class="marginleft">5.4: 数组--@ResponseBody接收(E18)</button>
    </div>
    <div class="indent">
        <a href="http://localhost:8080/springmvc/controller/list1?users[0].name=zs&users[1].name=ls&users[2].name=ww&users[0].age=20">6.1:
            集合--url参数(E19)&emsp;</a>
        <button id="list2" class="marginleft">6.2: 集合---@RequestBody List< User >(E20)</button>
        <button id="list3" class="marginleft">6.3: 集合---@RequestBody UserList(E21)</button>
    </div>
    <h3> Controller To View 传参</h3>
    <div>
        <h4>一、常用</h4>
    </div>
    <div class="indent">

        <a href="http://localhost:8080/springmvc/controller/hello?para=World">1:return String(E22)</a>
        <a href="http://localhost:8080/springmvc/controller/mv" class="marginleft">2:return ModelAndView(E23)</a>
        <a href="http://localhost:8080/springmvc/controller/map?name=王五&age=30"
           class="marginleft">3:return Map(E24)</a>
        <a href="http://localhost:8080/springmvc/controller/uriParam2?name=李四&age=18" class="marginleft">4:return
            Void--前传(E4)</a>
        <a href="http://localhost:8080/springmvc/controller/json4" class="marginleft">4:return
            Void--返回json字符串(E101)</a>
    </div>
    <div>
        <h4> 二、@ResponseBody</h4>
    </div>
    <div class="indent">
        <button id="responsebody1">1.Numeric(E25)</button>
        <button id="responsebody2">2.String--@requestBody(E26)</button>
        <button id="responsebody3">3.Object--@requestBody User(E27)</button>
        <button id="responsebody4">4.Array---User[ ](E18)</button>
        <button id="responsebody5">5.List---List&ltUser&gt(E20)</button>
        <button id="responsebody6">6.UserList(E21)</button>
    </div>
    <div>
        <h4> 三、ModelAttribute</h4>
    </div>
    <div class="indent">

        <a href="http://localhost:8080/springmvc/controller/hello?para=Hello World">无参返回void(E28)</a>
        <a href="http://localhost:8080/springmvc/controller/hello?para=Hello China">有参返回string(E29)</a>
        <a href="http://localhost:8080/springmvc/controller/user1?name=zhangsan">无参返回对象(E30)</a>
        <a href="http://localhost:8080/springmvc/controller/user2?name=lisi&age=20">有参返回对象(E31)</a>
        <a href="http://localhost:8080/springmvc/controller/user3?name=zhaoliu&age=30">修饰方法参数(E32)</a>
        <a href="http://localhost:8080/springmvc/controller/hellospring">与@requestMapping同时使用(E33)</a>
    </div>
    <div>
        <h4> 四、SessionAttribute</h4>
    </div>
    <div class="indent">
        <a href="http://localhost:8080/springmvc/controller/setSessionUser">生成属性键值对(E34)</a>
        <a href="http://localhost:8080/springmvc/controller/getSessionUser1" class="marginleft">Model引用(E35)</a>
        <a href="http://localhost:8080/springmvc/controller/getSessionUser2" class="marginleft">@ModelAttribute引用(E36)</a>
    </div>
    <div>
        <h4> 五、upload/download</h4>
    </div>
    <div class="indent">
        <div class="box left">
            <form target="iform" action="controller/fileUpload" method="post" enctype="multipart/form-data">
                上传人：<input type="text" name="name"/><br/>
                请选择文件：<input type="file" name="uploadfile" multiple="multiple"/><br/>
                <input type="submit" value="上传(E37)"/>
            </form>
            <iframe name="iform" id="iform" style="display:none"></iframe>
        </div>
        <div class="box right">
            <a href="${pageContext.request.contextPath }/controller/download?filename=001.jpg">
                文件下载(E38)
            </a>
            <br/>
            <a href="${pageContext.request.contextPath }/controller/downloadcn?filename=壁纸.jpg">
                中文名称文件下载(E39)
            </a>
        </div>
    </div>
</div>
<div id="rightpane" class="rightbox right">
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
    function myasync(myurl,ctype,dtype,obj,) {
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: ctype,
            dataType:dtype,       //"text"--String     "json"--js Object
            //请求地址
            url: myurl,
            //数据，json字符串
            data: obj,
            //请求成功
            success: function (result) {
                if(this.dataType=="json")  result=JSON.stringify(result);
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

    var json1 =JSON.stringify({"name": "zhangsan", "age": 20});
    var json2 = {"name": "lisi", "age": 30};
    var contenttype="application/json;charset=UTF-8"
    $("#responsebody1").click(function () {
        myasync("http://localhost:8080/springmvc/controller/json1", contenttype,"json",json1);
    });
    $("#responsebody2").click(function () {
        myasync("http://localhost:8080/springmvc/controller/json2", contenttype,"json",json1);
    });
    $("#responsebody3").click(function () {
        myasync("http://localhost:8080/springmvc/controller/json3", contenttype,"json",json1);
    });
    $("#requestbody0").click(function () {
        myasync("http://localhost:8080/springmvc/controller/requestbody", contenttype,"text",json1);
    });
    $("#requestbody1").click(function () {
        contenttype="application/x-www-form-urlencoded;charset=UTF-8"
        myasync("http://localhost:8080/springmvc/controller/pojo2", contenttype,"text",json2);
        contenttype="application/json;charset=UTF-8"
    });
    $("#requestbody2").click(function () {
        contenttype="application/x-www-form-urlencoded;charset=UTF-8"
        myasync("http://localhost:8080/springmvc/controller/pojo1", contenttype,"text",json2);
        contenttype="application/json;charset=UTF-8"
    });

    $("#requestbody3").click(function () {
        myasync("http://localhost:8080/springmvc/controller/pojo3", contenttype,"text",json1);
    });
    $("#requestbody4").click(function () {
        myasync("http://localhost:8080/springmvc/controller/pojo4", contenttype,"text",json1);
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
</script>
<script>
    var jsonArrayObject = {names:["张三", "李四", "王五"]};

    function f(num) {
        $.ajax({
            //请求方式
            type: "POST",
            //请求地址
            url: "http://localhost:8080/springmvc/controller/array" + num,
            //数据，json字符串
            data: jsonArrayObject,
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

    var dataArr = [{"name": "zhangsan", "age": 10}, {"name": "lisi", "age": 12}];  //js对象
    var params = JSON.stringify(dataArr);   //json字符串
    $("#arr4,#responsebody4").click(function () {
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/springmvc/controller/array4",
            data: params,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
               users = eval(
                    data
                );
                // for (var i in users) {
                //     var user=JSON.parse(data[i]);
                //     console.log("obj----" + user.name  );
                // }
                    var tr = "";
                $.each(users, function (index, item) {
                    //等效js替代 data.forEach(function(item){
                    tr += '<tr>';
                    tr += '<td>' + item.name + '</td>' + '<td>' + item.age + '</td>';
                    tr += '</tr>';
                });
                $("#headresult").append('<table border="6">' + tr + '</table>');

                //转为json字符串输出：  $("#headresult").html(JSON.stringify(data));
            },
            error: function (e) {
                alert("error")
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });

    var users = new Array();
    var userList = {};
    userList.users = users;
    $("#list2,#responsebody5").click(function () {
        users.splice(0, users.length);
        users.push({name: "张三", age: 22});
        users.push({name: "李四", age: 12});
        listfun(2);
    });

    $("#list3,#responsebody6").click(function () {
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

                //转为json字符串输出： $("#headresult").html(JSON.stringify(data));
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
