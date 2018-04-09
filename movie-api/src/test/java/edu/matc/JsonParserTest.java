package edu.matc;

import edu.matc.entity.Director;
import edu.matc.entity.Genre;
import edu.matc.entity.Movies;
import edu.matc.persistence.GenericDAO;

import edu.matc.persistence.MoviesDAO;
import edu.matc.util.JsonParser;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import sun.net.www.content.text.Generic;


import java.io.IOException;

import java.util.List;



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

        logger.debug(parser.returnJson(dao));

        System.out.println(parser.returnJson(dao));

        assertNotNull(parser.returnJson(dao), "The parser contains json");

    }

    @Test

    public void checkIfAllMoviesWorks() throws IOException {
        GenericDAO dao = new GenericDAO(Movies.class);
        JsonParser parser = new JsonParser();
        dao.getAll();
        assertNotNull(parser.returnJson(dao), "The parser contains json");
    }

    public void getAllDirectors() {
        GenericDAO dao = new GenericDAO(Director.class);
        Director director = (Director) dao.getByID(1);
        List<Director> allDirectors = dao.getAll();
        JsonParser parser = new JsonParser();
        try {
            System.out.println(parser.returnJson(allDirectors));
        } catch (IOException ioexception) {
            logger.error(ioexception.getMessage());
        }

    }
}