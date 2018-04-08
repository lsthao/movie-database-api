package edu.matc.movieAPI;

import edu.matc.entity.Movies;
import edu.matc.persistence.GenericDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/movies")
public class MovieAPI {
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
}
