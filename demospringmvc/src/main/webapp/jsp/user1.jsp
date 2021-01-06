<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring Controller Demo<</title>
</head>
<body>
<h1>Spring Controller Demo</h1>
<h3>返回对象</h3>
<br/>
<p>name1:${user.name}</p>
<p>age1:${user.age}</p>

<p>name2:${account.user.name}</p>
<p>age2:${account.user.age}</p>
</body>
</html>