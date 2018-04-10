<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jeff
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
    <h1>Search For Director</h1>
</div>
<div>
    <form method="GET" action="/movie-api/selectedDirector">
        <div class="form-group">
            <div class="ui-widget">
                <label for="combobox">Find a Director: </label>
                <select class="form-control" id="combobox" name="directorId" required>
                    <c:forEach var="director" items="${directors}">
                        <option value="<c:out value="${director.id}"/>">${director.directorName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-success" name="submit" id="submit" value="submit">Submit</button>
        </div>
    </form>
</div>
<div><a href="index.jsp">Back to Homepage</a></div>
</body>
</html>
