package edu.matc.persistence;

import edu.matc.entity.Movies;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class MoviesDAO {
    Logger logger =  Logger.getLogger(this.getClass());
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    public List<Movies> getRelatedMovies(int id) {
        Movies movie;
        List<Movies> movies = null;
        Session session = getSession();
        movie = session.get( Movies.class, id );
        session.close();
        List<Movies> dMovies = getByPropertyEqual("director", movie.getDirector().getDirectorName());
        List<Movies> gMovies = getByPropertyEqual("genre", movie.getGenre().getGenreName());
        if (dMovies != null && gMovies != null ) {
         movies.addAll(dMovies);
         movies.addAll(gMovies);
        }

        return movies;
    }

    public List<Movies> getByPropertyLike(String propertyName, String value) {
        Session session = getSession();

        logger.debug("Searching for user with {} = {}, " + propertyName + ", " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Movies> query = builder.createQuery( Movies.class );
        Root<Movies> root = query.from( Movies.class);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Movies> movies = session.createQuery( query ).getResultList();
        session.close();
        return movies;
    }

    public List<Movies> getByPropertyEqual(String propertyName, String value) {
        Session session = getSession();

        logger.debug("Searching for movies with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Movies> query = builder.createQuery( Movies.class );
        Root<Movies> root = query.from( Movies.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Movies> movies = session.createQuery( query ).getResultList();

        session.close();
        return movies;
    }


}
