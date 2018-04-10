package edu.matc.movieAPI;

import edu.matc.entity.Director;
import edu.matc.entity.Genre;
import edu.matc.entity.Movies;
import edu.matc.entity.Rating;
import edu.matc.persistence.GenericDAO;
import edu.matc.persistence.MoviesDAO;
import org.apache.log4j.Logger;
import edu.matc.util.JsonParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/movies")
public class MovieAPI {
    Logger logger =  Logger.getLogger(this.getClass());
    JsonParser jsonParser = new JsonParser();

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Path("/{id}")
    public Response getMessage(@PathParam("id") String id) {
        GenericDAO movieDAO = new GenericDAO(Movies.class);

        Movies movie = (Movies)movieDAO.getByID(Integer.parseInt(id));
        if (movie != null) {
        String output = "Movie Title: " + movie.getTitle() + "\n"
                + "Description: " + movie.getDescription()+ "\n";

        return Response.status(200).entity(output).build();
        } else {
            String output = "Status 404: Movie Not Found";
            return Response.status(404).entity(output).build();
        }
    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/all")
    public Response getAllMovies() {
        GenericDAO moviesDAO = new GenericDAO(Movies.class);

        List<Movies> movieList = moviesDAO.getAll();

        String stringResponse = "";
        if (movieList != null) {
            try {
                logger.info("starting the try block");
                stringResponse += jsonParser.returnJson(movieList);
                logger.debug("in the try block and added parsedjson for all movies");
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }

            logger.debug("string response: " + stringResponse);
            return Response.status(200).entity(stringResponse).build();
        } else {
            String output = "Status 404: Movie List Not Found";
            return Response.status(404).entity(output).build();
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addMovie(@FormParam("title") String title,
                             @FormParam("description") String description,
                             @FormParam("releaseYear") String releaseYear,
                             @FormParam("genre") String genre,
                             @FormParam("director") String director,
                             @FormParam("rating") String rating
    ) {

        String result = "";
        int status = 200;
        int id = 0;

        GenericDAO movieDAO = new GenericDAO(Movies.class);
        GenericDAO genreDAO = new GenericDAO(Genre.class);
        GenericDAO directorDAO = new GenericDAO(Director.class);
        GenericDAO ratingDAO = new GenericDAO(Rating.class);

        Genre movieGenre = (Genre)genreDAO.getByPropertyEqual("genreName", genre).get(0);
        Director movieDirector = (Director)directorDAO.getByPropertyEqual("directorName", director).get(0);
        Rating movieRating = (Rating)ratingDAO.getByPropertyEqual("ratingName", rating).get(0);

        Movies newMovie = new Movies(description, Integer.parseInt(releaseYear), title, movieGenre, movieDirector, movieRating);
        id = movieDAO.add(newMovie);

        if (id > 0) {
            try {
                result = jsonParser.returnJson(newMovie);
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }
            status = 201;
        } else {
            result = "Status 500: Movie was not added";
            status = 500;
        }

        return Response.status(status).entity(result).build();

    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/related-movies")
    public Response getRelatedMovies(@QueryParam("relatedMovie")  String id) {
        MoviesDAO moviesDAO = new MoviesDAO();

        List<Movies> relatedList = moviesDAO.getRelatedMovies(Integer.parseInt(id));

        String stringResponse = "";
        if (relatedList != null) {
            try {
                logger.info("starting the try block");
                stringResponse += jsonParser.returnJson(relatedList);
                logger.debug("in the try block and added parsedjson for related movies");
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }

            logger.debug("string response: " + stringResponse);
            return Response.status(200).entity(stringResponse).build();
        } else {
            String output = "Status 404: Movie List Not Found";
            return Response.status(404).entity(output).build();
        }
    }

}
