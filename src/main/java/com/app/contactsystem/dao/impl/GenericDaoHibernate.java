package com.app.contactsystem.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.app.contactsystem.dao.GenericDao;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend.
 * this class when your require custom CRUD logic.
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses Logger.getLogger(getClass())
     * from Commons Logging
     */
    private final Logger log = Logger.getLogger(getClass());
    private Class<T> persistentClass;
    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     * 
     * @param persistentClass
     *            the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of
     * DAO.
     * 
     * @param persistentClass
     *            the class type you'd like to persist
     * @param sessionFactory
     *            the pre-configured Hibernate SessionFactory
     */
    public GenericDaoHibernate(final Class<T> persistentClass, final SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
        this.hibernateTemplate.setFlushMode(HibernateTemplate.FLUSH_COMMIT);
    }

    /**
     * Set session factory to the dao, this method should be call by spring
     * framework with bean xml configuration.
     * 
     * @param sessionFactory
     *            the session factory.
     */
    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
        this.hibernateTemplate.setFlushMode(HibernateTemplate.FLUSH_COMMIT);
    }

    /**
     * Generic method used to get all objects of a particular type. This is the
     * same as lookup up all rows in a table.
     * 
     * @return List of populated objects
     */
    public List<T> getAll() {
        return hibernateTemplate.loadAll(this.persistentClass);
    }

    /**
     * Gets all records without duplicates.
     * <p>
     * Note that if you use this method, it is imperative that your model
     * classes correctly implement the hashcode/equals methods
     * </p>
     * 
     * @return List of populated objects
     */
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     * 
     * @param id
     *            the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    public T get(PK id) {
        T entity = (T) hibernateTemplate.get(this.persistentClass, id);

        if (null == entity) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            // throw new ObjectRetrievalFailureException(this.persistentClass,
            // id);
            return null;
        }

        return entity;
    }

    /**
     * Checks for existence of an object of type T using the id arg.
     * 
     * @param id
     *            the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    public boolean exists(PK id) {
        T entity = (T) hibernateTemplate.get(this.persistentClass, id);
        return entity != null;
    }

    /**
     * Generic method to save an object - handles both update and insert.
     * 
     * @param object
     *            the object to save
     * @return the persisted object
     */
    public T save(T object) {
        return (T) hibernateTemplate.merge(object);
    }

    /**
     * Generic method to delete an object based on class and id.
     * 
     * @param id
     *            the identifier (primary key) of the object to remove
     */
    public void remove(PK id) {
        hibernateTemplate.bulkUpdate("delete from " + persistentClass.getName() + " where id=?", id);
    }

    /**
     * Find a list of records by using a named query.
     * 
     * @param queryName
     *            query name of the named query
     * @param queryParams
     *            a map of the query names and the values
     * @return a list of the records found
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        String[] params = new String[queryParams.size()];
        Object[] values = new Object[queryParams.size()];

        int index = 0;
        for (String s : queryParams.keySet()) {
            params[index] = s;
            values[index++] = queryParams.get(s);
        }

        return hibernateTemplate.findByNamedQueryAndNamedParam(queryName, params, values);
    }

    /**
     * Find a list of records by provide query String.
     * 
     * @param queryString
     *            query string.
     * @param values
     *            the parameters of the query.
     * @return a list of the records found.
     */
    public List<?> find(String queryString, Object[] values) {
        return hibernateTemplate.find(queryString, values);
    }

    /**
     * Refresh the status of the object.
     * 
     * @param object
     *            the object to be refreshed
     */
    public void refresh(T object) {
        hibernateTemplate.refresh(object);
    }

    /**
     * Remove all record from DB.
     */
    public void removeAll() {
        hibernateTemplate.deleteAll(getAll());
    }

    /**
     * Flush persisted object to DB.
     */
    public void flush() {
        hibernateTemplate.flush();
    }

    /**
     * save or update a batch of Objects.it's much faster. This can be used when
     * you want to save/update a batch of objects at the same time
     * 
     * @param c
     *            the collection to save or update
     */
    @SuppressWarnings("rawtypes")
    public void saveOrUpdateAll(Collection c) {
        hibernateTemplate.saveOrUpdateAll(c);
    }

    /**
     * get the hibernate template.
     * 
     * @return HibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * the wrap code for get items.
     * 
     * return an empty List if no result exist
     * 
     * @param hql
     *            the hql to query
     * @param params
     *            the params to set into the hql
     * @return List<T> result
     */
    @SuppressWarnings("unchecked")
    public List<T> getItems(String hql, Object[] params) {
        if (hql != null) {
            List<T> items = this.getHibernateTemplate().find(hql, params);

            if (items != null) {
                return items;

            }
        }
        return new ArrayList<T>();
    }

    /**
     * the wrap code for get one item.
     * 
     * 
     * return the first one in the query result
     * 
     * @param hql
     *            the hql to query
     * @param params
     *            the params to set into the hql
     * @return T result
     */
    @SuppressWarnings("unchecked")
    public T getItem(String hql, Object[] params) {
        if (hql != null) {
            List<T> items = this.getHibernateTemplate().find(hql, params);

            if (items != null && !items.isEmpty()) {
                return items.get(0);
            }
        }

        return null;
    }

    /**
     * the wrap code for check the specified item or items exists.
     * 
     * @param hql
     *            the hql to query
     * @param params
     *            the params to set into the hql
     * @return whether the queried item exists
     */
    @SuppressWarnings("unchecked")
    public boolean exists(String hql, Object[] params) {
        if (hql != null) {
            List<T> items = this.getHibernateTemplate().find(hql, params);

            if (items != null && !items.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    /**
     * get session factory.
     * 
     * @return SessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
