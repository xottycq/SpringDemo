<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring MVC Demo<</title>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
</head>
<body>
<button id="b1"  onClick="f(this.id)">1.开户</button>
<button id="b11"  onClick="m(this.id)" class="marginlefta">1)开户</button>
<br/>
<button id="b2" onClick="f(this.id)">2.销户</button>
<button id="b12"  onClick="m(this.id)" class="marginlefta">2)销户</button>
<br/>
<button id="b3" onClick="f(this.id)">3.存款</button>
<button id="b13"  onClick="m(this.id)" class="marginlefta">3)存款</button>
<br/>
<button id="b4" onClick="f(this.id)">4.取款</button>
<button id="b14"  onClick="m(this.id)" class="marginlefta">4)取款</button>
<br/>
<button id="b5" onClick="f(this.id)">5.转账(普通)</button>
<button id="b15"  onClick="m(this.id)" class="marginleftb">5)转账(普通)</button>
<br/>
<button id="b6" onClick="f(this.id)">6.转账(事务)</button>
<button id="b16"  onClick="m(this.id)" class="marginleftb">6)转账(事务)</button>
<br/>
<script>
        function f(bid) {
            let contenttype="application/x-www-form-urlencoded;charset=UTF-8"
            $("#result, window.parent.document").html("Result")
            switch (bid) {
                case "b1":
                    ajaxrequest("GET", "http://localhost:8080/springmvc/openaccount", contenttype, "text", {
                        "name": "zs1",
                        "age": 40
                    })
                    break;
                case "b2":
                    ajaxrequest("GET", "http://localhost:8080/springmvc/closeaccount", contenttype, "text", {
                        "name": "zs1",
                        "age": 40
                    })
                    break;
                case "b3":

                    ajaxrequest("GET", "http://localhost:8080/springmvc/save", contenttype, "text", {
                        "name": "zs1",
                        "age": 40,
                        "money": 100
                    })
                    break;
                case "b4":
                    ajaxrequest("GET", "http://localhost:8080/springmvc/withdraw", contenttype, "text", {
                        "name": "zs1",
                        "age": 40,
                        "money": 50
                    })
                    break;
                case "b5":
                    contenttype="application/json;charset=UTF-8"
                    var s=JSON.stringify({"outuser":{"name":"zs1","age":40},"inuser":{"name":"zs","age":40},"money":10});
                    ajaxrequest("POST", "http://localhost:8080/springmvc/transfer1",contenttype, "text", s);

                    break;
                case "b6":
                    contenttype="application/json;charset=UTF-8"
                    var s=JSON.stringify({"outuser":{"name":"zs1","age":40},"inuser":{"name":"zs","age":40},"money":10});
                    ajaxrequest("POST", "http://localhost:8080/springmvc/transfer2",contenttype, "text", s);
                    break;
                default:
                    alert("error button id")
            }
        }

        function ajaxrequest(method, myurl, ctype, dtype, obj) {
            $.ajax({
                //请求方式
                type: method,
                //请求的媒体类型
                contentType: ctype,
                dataType: dtype,       //"text"--String     "json"--js Object
                //请求地址
                url: myurl,
                //数据，json字符串
                data: obj,
                //请求成功
                success: function (result) {
                    if (this.dataType == "json") result = JSON.stringify(result);
                    $("#result, window.parent.document").html(result)
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    alert("error")
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        }
        function m(bid) {
            let contenttype = "application/x-www-form-urlencoded;charset=UTF-8"
            $("#result, window.parent.document").html("Result")
            switch (bid) {
                case "b11":
                    $("#detail, window.parent.document").load("jsp/opaccount1.jsp",{"mytitle":"开户"})
                    break;
                case "b12":
                    $("#detail, window.parent.document").load("jsp/opaccount1.jsp",{"mytitle":"销户"})
                    break;
                case "b13":
                    $("#detail, window.parent.document").load("jsp/opaccount2.jsp",{"mytitle":"存款"})
                    break;
                case "b14":
                    $("#detail, window.parent.document").load("jsp/opaccount2.jsp",{"mytitle":"取款"})
                    break;
                case "b15":
                    $("#detail, window.parent.document").load("jsp/opaccount3.jsp",{"mytitle":"转账1"})
                    break;
                case "b16":
                    $("#detail, window.parent.document").load("jsp/opaccount3.jsp",{"mytitle":"转账2"})
                    break;
                default:
                    alert("error button id")
            }
        }
</script>
</body>
</html>