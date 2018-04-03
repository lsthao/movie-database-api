package edu.matc.persistence;

import edu.matc.entity.Genre;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenreDAO {

    private final Logger logger = Logger.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Genre> getAll() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Genre> query = builder.createQuery(Genre.class);

        Root<Genre> root = query.from(Genre.class);
        List<Genre> genres = session.createQuery(query).getResultList();

        session.close();

        return genres;
    }

    /**
     * Get genre by ID
     * @param id id of genre
     */
    public Genre getByID(int id) {
        Session session = sessionFactory.openSession();
        logger.info(id);
        Genre genre = session.get(Genre.class, id);
        session.close();
        return genre;
    }

    /**
     * Update genre
     * @param genre genre to be updated
     */
    public void update(Genre genre) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(genre);
        transaction.commit();
        session.close();

    }

    /**
     * Add a genre
     * @param genre genre to be added
     */

    public int add(Genre genre) {
        int id = 0;

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer)session.save(genre);
        transaction.commit();
        session.close();
        return id;

    }

    /**
     * Delete a genre
     * @param genre
     */
    public void delete(Genre genre) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(genre);
        transaction.commit();
        session.close();
    }
}
