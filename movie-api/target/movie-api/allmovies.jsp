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
    <th>Description</th>
    <th>Release Year</th>
    <th>Genre</th>
    <th>Director</th>
    <th>Rating</th>

    <c:forEach items="${movies}" var="movie">

        <tr>
            <td>${movie.id}</td>
            <td>${movie.title}</td>
            <td>${movie.description}</td>
            <td>${movie.rating}</td>
            <td>${movie.genre.genreName}</td>
            <td>${movie.director.directorName}</td>
            <td>${movie.rating.ratingName}</td>
        </tr>
    </c:forEach>

</table>
</html>
