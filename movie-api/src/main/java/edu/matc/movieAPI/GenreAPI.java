package edu.matc.movieAPI;

import edu.matc.entity.Genre;
import edu.matc.persistence.GenericDAO;
import edu.matc.util.JsonParser;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/genres")
public class GenreAPI {
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
}
