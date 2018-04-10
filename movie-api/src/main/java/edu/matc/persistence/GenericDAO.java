package edu.matc.persistence;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericDAO<T> {
    private Class<T> type;
    Logger logger =  Logger.getLogger(this.getClass());

    public GenericDAO() {

    }

    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    public <T>T getByID(int id) {
        Session session = getSession();
        T entity = (T)session.get( type, id );
        session.close();
        return entity;
    }

    public void delete( T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    public List<T> getAll() {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery( type );
        Root<T> root = query.from( type );
        List<T> entity = session.createQuery( query ).getResultList();

        logger.debug("The list of entity type " + entity);
        session.close();

        return entity;
    }

    public int add(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    public void update(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    public List<T> getByPropertyEqual(String propertyName, String value) {
        Session session = getSession();

        logger.debug("Searching for " + type + " with " + propertyName + " = " + value);

        int searchId = Integer.parseInt(value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery( type );
        Root<T> root = query.from( type );
        query.select(root).where(builder.equal(root.get(propertyName), searchId));
        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;
    }

    /**
     * Get user by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = getSession();

        logger.debug("Searching for type with {} = {}, " + propertyName + ", " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery( type );
        Root<T> root = query.from( type );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<T> entities = session.createQuery( query ).getResultList();
        session.close();
        return entities;
    }

}