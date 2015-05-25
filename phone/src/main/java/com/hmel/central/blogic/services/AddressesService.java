package com.hmel.central.blogic.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.hmel.central.blogic.interfaces.IAddressesService;
import com.hmel.central.models.Addresses;
import com.hmel.central.utils.HibernateUtils;
import com.hmel.exception.PhoneDictionaryException;

public class AddressesService implements IAddressesService {

  private SessionFactory sessionFactory = HibernateUtils.getSessionfactory();

  @Override
  public Addresses saveUpdate(Addresses addresses) throws PhoneDictionaryException {
    if (addresses == null) {
      throw new IllegalArgumentException("Addresses can't be NULL for saving");
    }
    getSession().beginTransaction();
    try {
      if (addresses.getId() == 0) {
        getSession().persist(addresses);
      } else {
        getSession().merge(addresses);
      }
      getSession().getTransaction().commit();
    } finally {
      getSession().close();
    }
    return addresses;
  }

  @Override
  public Addresses findByID(int id) throws PhoneDictionaryException {
    if (id == 0) {
      throw new IllegalArgumentException("ID can't be 0");
    }
    Addresses addresses = null;
    getSession().beginTransaction();
    try {
      addresses = (Addresses) getSession().get(Addresses.class, id);
    } finally {
      getSession().close();
    }
    if (addresses == null) {
      throw new NullPointerException("No addresses with Id: " + id);
    }
    return addresses;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Addresses> findAll() throws PhoneDictionaryException {
    String sql = "SELECT a FROM Addresses a";
    getSession().beginTransaction();
    List<Addresses> res = new ArrayList<Addresses>();
    try {
      Query query = getSession().createQuery(sql);
      res = query.list();
    } finally {
      getSession().close();
    }
    return res;
  }

  @Override
  public List<Addresses> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResult)
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
    List<Addresses> res = new ArrayList<Addresses>();
    try {
      res =
          (List<Addresses>) criteria.getExecutableCriteria(getSession())
              .setFirstResult(firstResult).setMaxResults(maxResult).list();
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
      Addresses addresses = findByID(id);
      if (addresses != null) {
        getSession().beginTransaction();
        getSession().delete(addresses);
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
