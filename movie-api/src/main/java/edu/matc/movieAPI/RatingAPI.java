package edu.matc.movieAPI;

import edu.matc.entity.Rating;
import edu.matc.persistence.GenericDAO;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.log4j.Logger;
import edu.matc.util.JsonParser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/ratings")
public class RatingAPI {
    private Logger logger =  Logger.getLogger(this.getClass());
    private JsonParser jsonParser = new JsonParser();

    @GET
    @Produces
    @Path("/{id}")
    public Response getAll(@PathParam("id") String id){
        GenericDAO ratingDAO = new GenericDAO(Rating.class);

        Rating rating = (Rating)ratingDAO.getByID(Integer.parseInt(id));
        if (rating != null) {
            String output = "Rating Name: " + rating.getRatingName() + "\n";

            return Response.status(200).entity(output).build();
        } else {
            String output = "Status 404: rating Not Found";
            return Response.status(404).entity(output).build();
        }
    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/all")
    public Response getAllRatings(){
        GenericDAO moviesDAO = new GenericDAO(Rating.class);

        List<Rating> ratingsList = moviesDAO.getAll();

        String stringResponse = "";
        if (ratingsList != null) {
            try {
                logger.info("starting the try block");
                stringResponse += jsonParser.returnJson(ratingsList);
                logger.debug("in the try block and added parsedjson for all ratings");
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }

            logger.debug("string response: " + stringResponse);
            return Response.status(200).entity(stringResponse).build();
        } else {
            String output = "Status 404: Ratings List Not Found";
            return Response.status(404).entity(output).build();
        }
    }
}
