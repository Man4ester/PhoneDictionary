package com.hmel.dao.blogic.services;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hmel.dao.blogic.interfaces.IHibernateDAO;
import com.hmel.exception.PhoneDictionaryException;

/**
 * @author Burkovskiy Alexander
 */
@Transactional(value = "transactionManager")
public class AbstractHibernateDAO<T extends Serializable, P extends Serializable> implements
    IHibernateDAO<T, P> {

  protected Class<T> clazz;

  @Autowired
  @Qualifier("localSessionFactory")
  protected SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  public AbstractHibernateDAO() {
    this.clazz =
        (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }


  @SuppressWarnings("unchecked")
  public T findOne(P id) throws PhoneDictionaryException {
    getCurrentSession().beginTransaction();
    try {
      return (T) getCurrentSession().get(clazz, id);
    } finally {
      getCurrentSession().close();
    }

  }

  @SuppressWarnings("unchecked")
  public List<T> findAll() throws PhoneDictionaryException {
    getCurrentSession().beginTransaction();
    try {
      return getCurrentSession().createQuery("from " + clazz.getName()).list();
    } finally {
      getCurrentSession().close();
    }
  }

  public T create(T entity) throws PhoneDictionaryException {
    getCurrentSession().beginTransaction();
    try {
      getCurrentSession().persist(entity);
      getCurrentSession().getTransaction().commit();
    }

    finally {
      getCurrentSession().close();
    }
    return entity;
  }

  public T update(T entity) throws PhoneDictionaryException {
    getCurrentSession().beginTransaction();
    try {
      getCurrentSession().merge(entity);
      getCurrentSession().getTransaction().commit();
    }

    finally {
      getCurrentSession().close();
    }
    return entity;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public T save(T entity) throws PhoneDictionaryException {
    getCurrentSession().beginTransaction();
    try {
      getCurrentSession().saveOrUpdate(entity);
      getCurrentSession().getTransaction().commit();
    } finally {
      getCurrentSession().close();
    }
    return entity;
  }


  public void delete(T entity) throws PhoneDictionaryException {
    getCurrentSession().beginTransaction();
    try {
      getCurrentSession().delete(entity);
      getCurrentSession().getTransaction().commit();
    } finally {
      getCurrentSession().close();
    }
  }

  public void deleteById(P entityId) throws PhoneDictionaryException {
    T entity = findOne(entityId);
    delete(entity);

  }

  /**
   * 
   * @param criteria
   * @return all records
   */
  public List<T> findByCriteria(DetachedCriteria criteria) throws PhoneDictionaryException {
    return findByCriteria(criteria, 0, Integer.MAX_VALUE);
  }


  /**
   * 
   * @param criteria
   * @return records starting from firstResult and amount = maxResuls (or less if not present
   *         necessary amout in db)
   */
  @SuppressWarnings("unchecked")
  public List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults)
      throws PhoneDictionaryException {
    if (criteria == null) {
      throw new IllegalArgumentException("criteria can't be null");
    }
    if (firstResult < 0) {
      throw new IllegalArgumentException("firstResult can't be < 0");
    }

    if (maxResults < 0) {
      throw new IllegalArgumentException("maxResults can't be < 0");
    }
    List<T> res =
        (List<T>) criteria.getExecutableCriteria(getCurrentSession()).setFirstResult(firstResult)
            .setMaxResults(maxResults).list();
    return res;
  }

  protected final Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }


  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }


  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
}
