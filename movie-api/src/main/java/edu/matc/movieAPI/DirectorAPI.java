package edu.matc.movieAPI;

import edu.matc.entity.Director;
import edu.matc.entity.Movies;
import edu.matc.persistence.GenericDAO;
import edu.matc.util.JsonParser;
import org.apache.log4j.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/directors")
public class DirectorAPI {

    GenericDAO directorDAO = new GenericDAO(Director.class);
    JsonParser jsonParser = new JsonParser();
    Logger logger = Logger.getLogger(this.getClass());

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces({"application/json", "text/plain"})

    @Path("/all")
    public Response getDirectors() {

        List<Director> allDirectors = directorDAO.getAll();
        String stringResponse = "";

        try {

            stringResponse += jsonParser.returnJson(allDirectors);

        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }

        logger.debug("string response: " + stringResponse);

        return Response.status(200).entity(stringResponse).build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addDirector(@FormParam("directorName") String directorName) {

        String result = "";
        int status = 200;
        int id = 0;

        Director newDirector = new Director(directorName);
        id = directorDAO.add(newDirector);

        if (id > 0) {
            result = "New director was added";
            status = 201;
        } else {
            result = "Status 500: Director was not added";
            status = 500;
        }

        return Response.status(status).entity(result).build();
    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/getByDirectorId/{directorId}")
    public Response getMoviesByGenreId(@PathParam("directorId") String directorId){
        GenericDAO movieDao = new GenericDAO(Movies.class);

        List<Movies> moviesList = movieDao.getByPropertyEqual("director", directorId);

        String stringResponse = "";
        if (moviesList != null) {
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
