<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring MVC Demo<</title>
<%--<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/jquery/http_cdn.bootcdn.net_ajax_libs_jquery_1.9.1_jquery.js"></script>
</head>
<body>
<h3>${daotitle}</h3>
<button id="b1"  onClick="f(this.id)">1.增加</button>
<br/>
<button id="b2" onClick="f(this.id)">2.修改</button>
<br/>
<button id="b3" onClick="f(this.id)">3.删除</button>
<br/>
<button id="b4" onClick="f(this.id)">4.查询</button>
<br/>
<button id="b5" onClick="f(this.id)">5.查询全部</button>
<script>
        function f(bid) {
            $("#result, window.parent.document").html("")
            switch (bid) {
                case "b1":
                    $("#detail, window.parent.document").load("jsp/opuser1.jsp",{"mytitle":"增加"})
                    break;
                case "b2":
                    $("#detail, window.parent.document").load("jsp/opuser1.jsp",{"mytitle":"修改"})
                    break;
                case "b3":
                    $("#detail, window.parent.document").load("jsp/opuser2.jsp",{"mytitle":"删除"})
                    break;
                case "b4":
                    $("#detail, window.parent.document").load("jsp/opuser2.jsp",{"mytitle":"查询"})
                    break;
                case "b5":
                            $.get("http://localhost:8080/springmvc/queryalluser",
                                function(data){
                                    $("#result, window.parent.document").html(data)
                                })
                    break;
                default:
                    alert("error button id")
            }
        }
</script>
</body>
</html>