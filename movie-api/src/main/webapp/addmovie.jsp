<%--
  Created by IntelliJ IDEA.
  User: Ren
  Date: 4/7/2018
  Time: 1:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Movie</title>
</head>
<body>
<h4>Add a Movie to the database</h4>
<form method="POST" action="/movie-api/movieAPI/movies/add">
    <p>Title</p>
    <input type="text" name="title">
    <p>Description</p>
    <input type="text" name="description">
    <p>Release Year</p>
    <input type="text" name="releaseYear">
    <p>Genre</p>
    <select name="genre">
        <c:forEach items="${genres}" var="genre">
            <option value="${genre.genreName}">${genre.genreName}</option>
        </c:forEach>
    </select>
    <p>Director</p>
    <select name="director">
        <c:forEach items="${directors}" var="director">
            <option value="${director.directorName}">${director.directorName}</option>
        </c:forEach>
    </select>
    <p>Rating</p>
    <select name="rating">
        <c:forEach items="${ratings}" var="rating">
            <option value="${rating.ratingName}">${rating.ratingName}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="submit movie">
</form>
</body>
</html>