<%--
  Created by IntelliJ IDEA.
  User: Ren
  Date: 4/7/2018
  Time: 1:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<title>All Movies</title>

<body>

<div class="container-fluid">
    <h2>Search Results: </h2>

    <table border="1px" width="100px">
        <th>Id</th>
        <th>Description</th>
        <th>Title</th>

        <c:forEach items="${movies}" var="movie">

            <tr>
                <td>${movie.id}</td>
                <td>${movie.description}</td>
                <td>${movie.title}</td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>