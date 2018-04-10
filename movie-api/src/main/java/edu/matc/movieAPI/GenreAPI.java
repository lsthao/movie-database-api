package edu.matc.movieAPI;

import edu.matc.entity.Genre;
import edu.matc.entity.Movies;
import edu.matc.persistence.GenericDAO;
import edu.matc.persistence.MoviesDAO;
import edu.matc.util.JsonParser;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/genres")
public class GenreAPI {
    Logger logger =  Logger.getLogger(this.getClass());
    JsonParser jsonParser = new JsonParser();
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces({"application/json", "text/plain"})
    @Path("/all")

    public Response getGenres() throws IOException {
        GenericDAO genreDao = new GenericDAO(Genre.class);
        JsonParser parser = new JsonParser();

        List<Genre> genreList = genreDao.getAll();
        //genreDao.getAll();

        String jsonGenre = parser.returnJson(genreList);

        return Response.status(200).entity(jsonGenre).build();
    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/getByGenreId/{genreId}")
    public Response getMoviesByGenreId(@PathParam("genreId") String genreId){
        GenericDAO movieDao = new GenericDAO(Movies.class);


        List<Movies> moviesList = movieDao.getByPropertyEqual("genre", genreId);

        String stringResponse = "";
        if (!moviesList.isEmpty()) {
            try {
                logger.info("starting the try block");
                stringResponse += jsonParser.returnJson(moviesList);
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