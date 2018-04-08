package edu.matc;

import edu.matc.entity.Genre;
import edu.matc.entity.Movies;
import edu.matc.persistence.GenericDAO;

import edu.matc.persistence.MoviesDAO;
import edu.matc.util.JsonParser;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;



import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {
    GenericDAO movieDAO;
    int initialNumberOfMovies;
    MoviesDAO entMoviesDAO;

    private Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setup() {
        edu.matc.util.Database database = edu.matc.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        movieDAO= new GenericDAO(Movies.class);
        initialNumberOfMovies = movieDAO.getAll().size();
        entMoviesDAO = new MoviesDAO();
    }

    @Test
    public void checkIfParserReturnsJson() throws IOException {
        GenericDAO dao = new GenericDAO(Genre.class);
        JsonParser parser = new JsonParser();

        dao.getAll();

        //logger.debug(parser.returnJson(movie));

        System.out.println(parser.returnJson(dao));

        assertNotNull(parser.returnJson(dao), "The parser contains json");

    }
}