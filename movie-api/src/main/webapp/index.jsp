<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<c:set var = "title" value = "Madison Movie Database API"/>
<%@include file="jsp-contents/head.jsp" %>
<body>
<h1>Welcome to Madison Movie Database!</h1>
<h2>Links</h2>
  <h4>
      <ul>
          <li><a href="allMovies">See All Movies</a></li>
          <li><a href="searchmovie.jsp">Search Movies By Title</a></li>
          <li><a href="movieSuggester">Get Movies Suggestions</a></li>
          <li><a href="addMovieForm">Add a Movie</a></li>
          <li><a href="searchGenres">See All Genres</a></li>
      </ul>
  </h4>

<h2>Movies Endpoints</h2>
  <h4>movieAPI/movies/{id}</h4>
    <ul>
        <li>Endpoint for movies by ID</li>
    </ul>
  <h4>movieAPI/movies/all</h4>
    <ul>
        <li>Endpoint for all movies</li>
    </ul>
  <h4>movieAPI/movies/search/{title}</h4>
    <ul>
        <li>Endpoint to search for movie by title</li>
    </ul>
  <h4>movieAPI/movies/related-movies/</h4>
    <ul>
        <li>Endpoint that returns similar Movies in our database!</li>
    </ul>

<h2>Genre Endpoints</h2>
  <h4>movieAPI/genre/JSON/all</h4>
    <ul>
        <li>Endpoint for all genres in JSON</li>
    </ul>
  <h4>movieAPI/genre/JSON/< id ></h4>
    <ul>
        <li>Endpoint for genre by ID in JSON</li>
    </ul>

<h2>Director Endpoints</h2>
  <h4>movieAPI/director/JSON/all</h4>
    <ul>
        <li>Endpoint for all directors in JSON</li>
    </ul>
  <h4>movieAPI/director/JSON/< id ></h4>
    <ul>
        <li>Endpoint for director by ID in JSON</li>
    </ul>

<h2>Rating Endpoints</h2>
  <h4>movieAPI/rating/JSON/all</h4>
    <ul>
        <li>Endpoint for all ratings in JSON</li>
    </ul>
  <h4>movieAPI/rating/JSON/< id ></h4>
    <ul>
        <li>Endpoint for rating by ID in JSON</li>
    </ul>
</body>
</html>
