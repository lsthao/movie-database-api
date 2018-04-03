package edu.matc.persistence;

import edu.matc.entity.Movies;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MovieDAO {

    private final Logger logger = Logger.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Movies> getAll() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Movies> query = builder.createQuery(Movies.class);

        Root<Movies> root = query.from(Movies.class);
        List<Movies> movies = session.createQuery(query).getResultList();

        session.close();

        return movies;
    }

    /**
     * Get movie by ID
     * @param id id of movie
     */
    public Movies getByID(int id) {
        Session session = sessionFactory.openSession();
        Movies movie = session.get(Movies.class, id);
        session.close();
        return movie;
    }

    /**
     * Update movie
     * @param movie movie to be updated
     */
    public void update(Movies movie) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(movie);
        transaction.commit();
        session.close();

    }

    /**
     * Add a movie
     * @param movie movie to be added
     */

    public int add(Movies movie) {
        int id = 0;

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer)session.save(movie);
        transaction.commit();
        session.close();
        return id;

    }

    /**
     * Delete a movie
     * @param movie
     */
    public void delete(Movies movie) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(movie);
        transaction.commit();
        session.close();
    }
}
