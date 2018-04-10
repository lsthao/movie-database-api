<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/9/18
  Time: 7:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search By Title</title>
</head>
<body>
<h3>Search Movies By Title</h3>
    <form method="GET" action="/movie-api/search">
        <input type="text" id="title" name="title">
        <input type="submit" value="submit">
    </form>
</body>
</html>
