<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/8/18
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Movies</title>
</head>
<table border="1px" width="100px">
    <th>Id</th>
    <th>Movie Name</th>

    <c:forEach items="${allMovies}" var="movie">

        <tr>
            <td>${movie.id}</td>
            <td>${movie.title}</td>
        </tr>
    </c:forEach>

</table>
</html>
