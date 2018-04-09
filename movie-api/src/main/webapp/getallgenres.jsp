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
    <title>Genres</title>
</head>

    <form>
        <h2>Select a Genre</h2>
            <select>
                <option>Choose</option>
                <c:forEach items="${genreObject}" var="genre">
                    <option value = "${genre.genreName}">${genre.genreName}</option>
                </c:forEach>
            </select>
        <br />
        <button type="submit">Submit Choice</button>
    </form>
</html>
