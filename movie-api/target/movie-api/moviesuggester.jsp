<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ren
  Date: 4/7/2018
  Time: 1:12 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<c:set var = "title" value = "Madison Movie Database Movie Suggester"/>
<%@include file="jsp-contents/suggester-head.jsp" %>
<body>
<div>
    <h1>Find A New Movie Suggestion!</h1>
</div>
<div>
    <h3>Use our state of the art application to suggest similar movies within our database!</h3>
</div>
<div>
    <form method="GET" action="movieAPI/movies/related-movies">
        <div class="ui-widget">
            <label for="combobox">Find a Related Movie: </label>
            <select class="form-control" id="combobox" name="relatedMovie">
                <option value="select">Select a Category</option>
                <c:forEach var="movie" items="${movies}">
                    <option value="${movie.id}">${movie.title}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success" name="submit" id="submit" value="submit">Submit</button>
    </form>
</div>
</body>
</html>
