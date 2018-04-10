package edu.matc.movieAPI;

import edu.matc.entity.*;
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
            String output = jsonParser.returnJsonResponseMessage("Status 404: No movies returned");
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
        if (!movieList.isEmpty()) {
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
            String output = jsonParser.returnJsonResponseMessage("Status 404: No movies returned");
            return Response.status(404).entity(output).build();
        }
    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/search/{title}")
    public Response searchMovieByTitle(@PathParam("title") String title) throws java.io.UnsupportedEncodingException {
        GenericDAO moviesDAO = new GenericDAO(Movies.class);
        JsonParser jsonParser = new JsonParser();
        List<Movies> movieList = moviesDAO.getByPropertyLike("title", title.replace("+", " "));
        logger.info(movieList);
        String stringResponse = "";
        if (!movieList.isEmpty()) {
            try {

                stringResponse += jsonParser.returnJson(movieList);

            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }

            return Response.status(200).entity(stringResponse).build();
        } else {

            String output = jsonParser.returnJsonResponseMessage("Status 404: No movies returned");
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
            result = jsonParser.returnJsonResponseMessage("Status 500: Movie couldn't be added");
            status = 500;
        }

        return Response.status(status).entity(result).build();

    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/related-movies/{relatedMovie}")
    public Response getRelatedMovies(@PathParam("relatedMovie")  String relatedMovie) {
        MoviesDAO moviesDAO = new MoviesDAO();

        List<Movies> relatedList = moviesDAO.getRelatedMovies(Integer.parseInt(relatedMovie));


        String stringResponse = "";
        int status = 200;

        try {
            stringResponse += jsonParser.returnJson(relatedList);
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
        if (relatedList.size() > 0) {
            return Response.status(201).entity(stringResponse).build();
        } else if(relatedList.size() == 0){

            String output = jsonParser.returnJsonResponseMessage("Status 404: No Movies Related! Sorry!");

            return Response.status(404).entity(output).build();
        }  else {
            String output = jsonParser.returnJsonResponseMessage("Unexpected Error has Occurred. Self Destruct in 5.... 4... 3...");

            return Response.status(500).entity(output).build();
        }
    }

}
