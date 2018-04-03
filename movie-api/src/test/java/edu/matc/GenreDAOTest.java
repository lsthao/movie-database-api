package edu.matc;

import edu.matc.entity.Director;
import edu.matc.entity.Genre;
import edu.matc.entity.Movies;
import edu.matc.entity.Rating;
import edu.matc.persistence.GenericDAO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GenreDAOTest {
    GenericDAO genreDAO;
    int initialNumberOfGenres;

    private Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setup() {
        edu.matc.util.Database database = edu.matc.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        genreDAO= new GenericDAO(Genre.class);
        initialNumberOfGenres = genreDAO.getAll().size();
    }

    @Test
    public void addWithMovieTest() {
        GenericDAO ratingDAO = new GenericDAO(Rating.class);
        Rating rating =(Rating)ratingDAO.getByID(1);
        GenericDAO directorDAO = new GenericDAO(Director.class);
        Director director = (Director) directorDAO.getByID(1);

        Genre genre = new Genre("test genre");
        Movies movie = new Movies("test description", 2000, "test title", genre, director, rating);
        genre.addMovie(movie);

        int id = genreDAO.add(genre);

        Genre insertedGenre = (Genre) genreDAO.getByID(id);
        assertNotNull(insertedGenre);
        assertEquals("test genre", insertedGenre.getGenreName());
        assertEquals(initialNumberOfGenres + 1, genreDAO.getAll().size());
    }

    @Test
    public void deleteGenre() {
        Genre genre = (Genre) genreDAO.getByID(3);
        genreDAO.delete(genre);
        assertNull(genreDAO.getByID(3));
    }

}
