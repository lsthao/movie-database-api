package edu.matc.persistence;

import edu.matc.entity.Director;
import edu.matc.entity.Genre;
import edu.matc.entity.Movies;
import edu.matc.entity.Rating;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoviesDAO {
    private Logger logger =  Logger.getLogger(this.getClass());
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Method that calls other parsers and methods that returns like-movies by Director and Genre, placing
     * movies that contain both on top
     *
     * @param id id of movie to be processed
     * @return movies
     */
    public List<Movies> getRelatedMovies(int id) {
        Movies movie;
        List<Movies> movies = new ArrayList<Movies>();

        Session session = getSession();
        movie = session.get( Movies.class, id );
        session.close();


        List<Movies> dMovies = getByPropertyEqualDirector( movie.getDirector());
        List<Movies> gMovies = getByPropertyEqualGenre( movie.getGenre());
        List<Movies> rMovies = getByPropertyEqualRating( movie.getRating());


        if (dMovies.size() > 1 && gMovies.size() > 1 && rMovies.size() > 1) {
            logger.info("all have stuff");
            movieArrayParser(movies, dMovies, movie);
            movieArrayParser(movies, gMovies, movie);
            movieArrayParser(movies, rMovies, movie);
            movies = addMostAlikeMoviesOnTop(movies, movie);
        } else if (dMovies.size() > 1 && rMovies.size() > 1 && gMovies.size() <= 1) {
            logger.info("genre has none, d&r");
            movieArrayParser(movies, dMovies, movie);
            movieArrayParser(movies, rMovies, movie);
            movies = addMostAlikeMoviesOnTop(movies, movie);
        } else if (dMovies.size() <= 1 && rMovies.size() > 1 && gMovies.size() > 1) {
            logger.info("director has none, g&r");
           movieArrayParser(movies,gMovies, movie);
            movieArrayParser(movies, rMovies, movie);
            movies = addMostAlikeMoviesOnTop(movies, movie);
        } else if (dMovies.size() > 1 && gMovies.size() > 1 && rMovies.size() <= 1) {
            logger.info("ratings has none");
            movieArrayParser(movies, dMovies, movie);
            movieArrayParser(movies, gMovies, movie);
            movies = addMostAlikeMoviesOnTop(movies, movie);
        } else if (dMovies.size() > 1 && rMovies.size() <= 1 && gMovies.size() <= 1) {
            logger.info("dir only");
            movieArrayParser(movies, dMovies, movie);
        } else if (dMovies.size() <= 1 && rMovies.size() <= 1 && gMovies.size() > 1) {
            logger.info("genre only");
            movieArrayParser(movies, gMovies, movie);
        } else if (dMovies.size() <= 1 && gMovies.size() <= 1){
            logger.info("nothing was alike");
        } else {
            logger.info("something went wrong");
        }

        return movies;
    }

    /**
     * This method process the movie list and places the most alike movies on top
     *
     * @param movies master movie list
     * @param movie parameter movie object
     * @return movies
     */
    private List<Movies> addMostAlikeMoviesOnTop(List<Movies> movies, Movies movie) {
        ArrayList<Movies> newList1 = new ArrayList<>();
        ArrayList<Movies> newList2 = new ArrayList<>();
        Iterator<Movies> iter = movies.iterator();

        while (iter.hasNext()) {
            Movies movieEl = iter.next();
            if(!movieEl.getGenre().getGenreName().equals(movie.getGenre().getGenreName())
                    && !movieEl.getDirector().getDirectorName().equals(movie.getDirector().getDirectorName())
                    && movieEl.getRating().getRatingName().equals(movie.getRating().getRatingName())){
                iter.remove();
            } else if (movieEl.getGenre().getGenreName().equals(movie.getGenre().getGenreName())
                    && movieEl.getDirector().getDirectorName().equals(movie.getDirector().getDirectorName())
                    && movieEl.getRating().getRatingName().equals(movie.getRating().getRatingName())) {

                newList1.add(movieEl);
                iter.remove();
                logger.info(newList1.size());
            } else if (movieEl.getGenre().getGenreName().equals(movie.getGenre().getGenreName())
                    && movieEl.getDirector().getDirectorName().equals(movie.getDirector().getDirectorName())) {

                newList2.add(movieEl);
                iter.remove();
                logger.info(newList2.size());
            } else {
                logger.info("i did nothing");
            }
        }
        newList1.addAll(newList2);
        newList1.addAll(movies);
        movies = newList1;
        return movies;
    }

    /**
     * This method parses movies to make sure movies are not added twice into the related movie array
     *
     * @param movieList master movie list
     * @param foundMoviesList related movie list
     * @param movie parameter movie object
     */
    private void movieArrayParser(List<Movies> movieList, List<Movies> foundMoviesList, Movies movie){
        for (Movies foundMovies : foundMoviesList) {
            List<Integer> movies1 = new ArrayList<>();
            movies1.add(movie.getId());
            for (Movies ids : movieList) {
                movies1.add(ids.getId());
            }

            if (movies1.contains(foundMovies.getId())) {
                logger.info("hey this exists already. I worked. yay");
            } else {
                logger.info("added based on genre");
                movieList.add(foundMovies);
            }
        }
    }

    /**
     * This method returns a Movie array list by property of Genre
     * @param genre passed genre
     * @return movies
     */
    private List<Movies> getByPropertyEqualGenre( Genre genre) {
        Session session = getSession();

        logger.debug("Searching for movies with genre" + " = " + genre);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Movies> query = builder.createQuery( Movies.class );
        Root<Movies> root = query.from( Movies.class );
        query.select(root).where(builder.equal(root.get("genre"), genre));
        List<Movies> movies = session.createQuery( query ).getResultList();

        session.close();
        return movies;
    }

    /**
     * This method returns a Movie array list by property of Director
     * @param director passed director
     * @return movies
     */
    private List<Movies> getByPropertyEqualDirector(Director director) {
        Session session = getSession();

        logger.debug("Searching for movies with director" + " = " + director);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Movies> query = builder.createQuery( Movies.class );
        Root<Movies> root = query.from( Movies.class );
        query.select(root).where(builder.equal(root.get("director"), director));
        List<Movies> movies = session.createQuery( query ).getResultList();

        session.close();
        return movies;
    }

    private List<Movies> getByPropertyEqualRating(Rating rating) {
        Session session = getSession();

        logger.debug("Searching for movies with rating" + " = " + rating);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Movies> query = builder.createQuery( Movies.class );
        Root<Movies> root = query.from( Movies.class );
        query.select(root).where(builder.equal(root.get("rating"), rating));
        List<Movies> movies = session.createQuery( query ).getResultList();

        session.close();
        return movies;
    }
}
