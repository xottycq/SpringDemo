<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>Index</title>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery.form/4.3.0/jquery.form.js"></script>
</head>
<body>
<div>
    <form id="ajaxForm" method="post" action="/Home/Index" enctype="multipart/form-data">
        <input type="text" name="name" />
        <input type="text" name="sex" />
        <input type="file" name="file" />
        <button type="submit" id="btnSubmit">提交1</button>
    </form>
    <button id="btnButton" type="button">提交2</button>
</div>
<script type="text/javascript">
    $(function () {
        $("#ajaxForm").ajaxForm(function () {
            alert("提交成功11");
        });
        $("#ajaxForm").submit(function () {
            $(this).ajaxSubmit(function () {
                alert("提交成功12");
            });
            return false;
        });
        $("#btnButton").click(function () {
            $("#ajaxForm").ajaxSubmit(function () {
                alert("提交成功2");
            });
            return false;
        });
    });
</script>
</body>
</html>