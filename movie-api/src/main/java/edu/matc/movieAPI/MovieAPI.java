package edu.matc.movieAPI;

import edu.matc.entity.Movies;
import edu.matc.persistence.GenericDAO;
import edu.matc.persistence.MoviesDAO;
import org.apache.log4j.Logger;
import edu.matc.util.JsonParser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/filter")
    public Response getMoviesByFilter() {
        GenericDAO moviesDAO = new GenericDAO(Movies.class);

        List<Movies> movieList = moviesDAO.getAll();

        String stringResponse = "";
        if (movieList != null) {
            try {

                stringResponse += jsonParser.returnJson(movieList);

            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }

            return Response.status(200).entity(stringResponse).build();
        } else {
            String output = "Status 404: Movie List Not Found";
            return Response.status(404).entity(output).build();
        }
    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/related-movies/{id}")
    public Response getRelatedMovies(@PathParam("id") String id) {
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
