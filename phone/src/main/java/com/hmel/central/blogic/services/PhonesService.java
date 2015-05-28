package com.hmel.central.blogic.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.hmel.central.blogic.interfaces.IPhonesService;
import com.hmel.central.models.Phone;
import com.hmel.central.utils.HibernateUtils;
import com.hmel.exception.PhoneDictionaryException;

public class PhonesService implements IPhonesService {

  private SessionFactory sessionFactory = HibernateUtils.getSessionfactory();

  @Override
  public Phone saveUpdate(Phone phones) throws PhoneDictionaryException {
    if (phones == null) {
      throw new IllegalArgumentException("Phones can't be NULL for saving");
    }
    getSession().beginTransaction();
    try {
      if (phones.getId() == 0) {
        getSession().persist(phones);
      } else {
        getSession().merge(phones);
      }
      getSession().getTransaction().commit();
    } finally {
      getSession().close();
    }
    return phones;
  }

  @Override
  public Phone findByID(int id) throws PhoneDictionaryException {
    if (id == 0) {
      throw new IllegalArgumentException("ID can't be 0");
    }
    Phone phones = null;
    getSession().beginTransaction();
    try {
      phones = (Phone) getSession().get(Phone.class, id);
    } finally {
      getSession().close();
    }
    if (phones == null) {
      throw new NullPointerException("No phones with Id: " + id);
    }
    return phones;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Phone> findAll() throws PhoneDictionaryException {
    String sql = "SELECT p FROM Phones p";
    getSession().beginTransaction();
    List<Phone> res = new ArrayList<Phone>();
    try {
      Query query = getSession().createQuery(sql);
      res = query.list();
    } finally {
      getSession().close();
    }
    return res;
  }

  @Override
  public List<Phone> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResult)
      throws PhoneDictionaryException {
    if (criteria == null) {
      throw new IllegalArgumentException("criteria can't be null");
    }
    if (firstResult < 0) {
      throw new IllegalArgumentException("firstResult can't be < 0");
    }

    if (maxResult < 0) {
      throw new IllegalArgumentException("maxResults can't be < 0");
    }
    getSession().beginTransaction();
    List<Phone> res = new ArrayList<Phone>();
    try {
      res =
          (List<Phone>) criteria.getExecutableCriteria(getSession()).setFirstResult(firstResult)
              .setMaxResults(maxResult).list();
    } finally {
      getSession().close();
    }
    return res;
  }

  @Override
  public void delete(int id) throws PhoneDictionaryException {
    if (id == 0) {
      throw new IllegalArgumentException("Id can't be 0");
    }

    try {
      Phone phones = findByID(id);
      if (phones != null) {
        getSession().beginTransaction();
        getSession().delete(phones);
        getSession().getTransaction().commit();
      }
    } finally {
      getSession().close();
    }
  }

  private Session getSession() throws PhoneDictionaryException {
    return sessionFactory.getCurrentSession();
  }

}
