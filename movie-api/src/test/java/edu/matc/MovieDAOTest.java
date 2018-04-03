package edu.matc;

import edu.matc.entity.Genre;
import edu.matc.entity.Movies;
import edu.matc.persistence.GenreDAO;
import edu.matc.persistence.MovieDAO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieDAOTest {
    MovieDAO movieDAO;
    int initialNumberOfMovies;

    private Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setup() {
        edu.matc.util.Database database = edu.matc.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        movieDAO= new MovieDAO();
    }

    @Test
    public void addTest() {
        GenreDAO genreDAO = new GenreDAO();
        Genre genre = new Genre("romance");

        Movies movie = new Movies("new description", 2009, "new title", genre);
        logger.info(genre);
        genre.addMovie(movie);

        int id = movieDAO.add(movie);

        Movies insertedMovie = movieDAO.getByID(id);

        assertEquals("new title", insertedMovie.getTitle());
        assertEquals("new description", insertedMovie.getDescription());
        assertEquals(2009, insertedMovie.getReleaseYear());
        assertEquals(1, insertedMovie.getGenre().getId());

    }

}
