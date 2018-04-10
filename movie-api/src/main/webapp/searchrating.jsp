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
    <title>Ratings</title>
</head>

<form action="/movie-api/selectedRating" method="GET">
    <h2>Select a Rating</h2>
    <select name="ratingId">
        <option>Choose</option>
        <c:forEach items="${ratingObject}" var="rating">
            <option name="search" value="<c:out value="${rating.id}"/>">${rating.ratingName}</option>
        </c:forEach>
    </select>
    <br />
    <button type="submit" name="submit" value="submit">Submit Choice</button>
</form>
<div><a href="index.jsp">Back to Homepage</a></div>
</html>