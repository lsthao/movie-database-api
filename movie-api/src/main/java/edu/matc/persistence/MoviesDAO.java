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
import java.util.ArrayList;
import java.util.List;

public class MoviesDAO {
    private Logger logger =  Logger.getLogger(this.getClass());
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    public List<Movies> getRelatedMovies(int id) {
        Movies movie;
        List<Movies> movies = new ArrayList<Movies>();

        Session session = getSession();
        movie = session.get( Movies.class, id );
        session.close();


        List<Movies> dMovies = getByPropertyEqualDirector( movie.getDirector());
        List<Movies> gMovies = getByPropertyEqualGenre( movie.getGenre());


        if (dMovies.size() > 1 && gMovies.size() > 1 ) {
            logger.info("both have stuff");
            movieArrayParser(movies, dMovies, movie);
            movieArrayParser(movies, gMovies, movie);
        }else if (dMovies.size() > 1 && gMovies.size() <= 1) {
            logger.info("genre has none");
            movieArrayParser(movies, dMovies, movie);
        } else if (dMovies.size() <= 1 && gMovies.size() > 1) {
            logger.info("director has none");
           movieArrayParser(movies,gMovies, movie);
        } else if (dMovies.size() <= 1 && gMovies.size() <= 1){
            logger.info("nothing was alike");
        } else {
            logger.info("something went wrong");
        }

        return movies;
    }

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


}
