<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SpringBoot Web Demo<</title>
    <script src="${pageContext.request.contextPath}/js/jquery/http_cdn.bootcdn.net_ajax_libs_jquery_1.9.1_jquery.js"></script>
</head>
<body>
<h3>SpringBoot Web JSP</h3>
<button id="b1"  onClick="f(this.id)">1.开户</button>
<br/>
<button id="b2" onClick="f(this.id)">2.销户</button>
<br/>
<button id="b3" onClick="f(this.id)">3.存款</button>
<br/>
<button id="b4" onClick="f(this.id)">4.取款</button>
<br/>
<button id="b5" onClick="f(this.id)">5.转账(普通)</button>
<br/>
<button id="b6" onClick="f(this.id)">6.转账(事务)</button>
<br/>
<script>
    function f(bid) {
        let contenttype = "application/x-www-form-urlencoded;charset=UTF-8"
        $("#result, window.parent.document").html("")
        switch (bid) {
            case "b1":
                $("#detail, window.parent.document").load("jsp/opaccount1.jsp",{"mytitle":"开户"})
                break;
            case "b2":
                $("#detail, window.parent.document").load("jsp/opaccount1.jsp",{"mytitle":"销户"})
                break;
            case "b3":
                $("#detail, window.parent.document").load("jsp/opaccount2.jsp",{"mytitle":"存款"})
                break;
            case "b4":
                $("#detail, window.parent.document").load("jsp/opaccount2.jsp",{"mytitle":"取款"})
                break;
            case "b5":
                $("#detail, window.parent.document").load("jsp/opaccount3.jsp",{"mytitle":"转账1"})
                break;
            case "b6":
                $("#detail, window.parent.document").load("jsp/opaccount3.jsp",{"mytitle":"转账2"})
                break;
            default:
                alert("error button id")
        }
    }
</script>
</body>
</html>