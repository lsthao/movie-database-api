package edu.matc;

import edu.matc.entity.Director;
import edu.matc.entity.Genre;
import edu.matc.entity.Movies;
import edu.matc.entity.Rating;
import edu.matc.persistence.GenericDAO;
import edu.matc.persistence.MoviesDAO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GenericMovieDAOTest {
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
    public void addTest() {

        GenericDAO genreDAO = new GenericDAO(Genre.class);
        Genre genre = (Genre) genreDAO.getByID(1);

        GenericDAO directorDAO = new GenericDAO(Director.class);
        Director director = (Director)directorDAO.getByID(1);

        GenericDAO ratingDAO = new GenericDAO(Rating.class);
        Rating rating = (Rating) ratingDAO.getByID(1);

        Movies movie = new Movies("new description", 2009, "new title", genre, director, rating);
        genre.addMovie(movie);
        director.addMovie(movie);
        rating.addMovie(movie);

        int id = movieDAO.add(movie);

        Movies insertedMovie = (Movies)movieDAO.getByID(id);

        assertEquals("new title", insertedMovie.getTitle());
        assertEquals("new description", insertedMovie.getDescription());
        assertEquals(2009, insertedMovie.getReleaseYear());
        assertEquals(1, insertedMovie.getGenre().getId());
        assertEquals(1, insertedMovie.getRating().getId());
        assertEquals(1, insertedMovie.getDirector().getId());

    }

    @Test
    public void getAllMoviesTest() {
        List<Movies> moviesList = movieDAO.getAll();
        assertNotNull(moviesList);
        assertEquals(initialNumberOfMovies, moviesList.size());
    }

    @Test
    public void deleteMovieTest() {
        Movies movie = (Movies)movieDAO.getByID(3);
        movieDAO.delete(movie);
        assertNull(movieDAO.getByID(3));
    }

    @Test
    public void updateMovieTest() {
        String newMovieName = "updated movie name";
        Movies movie = (Movies)movieDAO.getByID(2);
        movie.setTitle(newMovieName);
        movie.setReleaseYear(2000);

        movieDAO.update(movie);

        Movies updatedMovie =(Movies) movieDAO.getByID(2);
        assertEquals(newMovieName, updatedMovie.getTitle());
        assertEquals(2000, updatedMovie.getReleaseYear());
    }

    @Test
    public void getMovieByID() {
        Movies movie = (Movies) movieDAO.getByID(1);
        assertNotNull(movie);
        assertEquals(1, movie.getId());
        assertEquals("test movie title", movie.getTitle());
    }

//    @Test
//    public void getMovieByProperty() {
//
//        List<Movies> movies = entMoviesDAO.getByPropertyEqual("genre", "com");
//        assertEquals(1, movies.size());
//        assertEquals(1, movies.get(0).getId());
//    }
//
//    @Test
//    public void getMovieLikeProperty() {
//        List<Movies> movies = entMoviesDAO.getByPropertyEqual("genre", "comedy");
//        for(Movies movie : movies) {
//            logger.info(movie.getGenre().getGenreName());
//        }
//        assertEquals(2, movies.size());
//    }
//
//    @Test
//    public void getRelatedMoviesTest() {
//        List<Movies> movies = entMoviesDAO.getRelatedMovies(1);
//        for (Movies movie : movies){
//                logger.info(movie.getTitle());
//                logger.info(movie.getGenre().getGenreName());
//                logger.info(movie.getDirector().getDirectorName());
//        }
//        // TODO asserts Equals and trues
//    }


}
