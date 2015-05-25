package com.hmel.central.blogic.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.hmel.central.blogic.interfaces.IClientService;
import com.hmel.central.models.Client;
import com.hmel.central.utils.HibernateUtils;
import com.hmel.exception.PhoneDictionaryException;

@Service
public class ClientService implements IClientService {

  private SessionFactory sessionFactory = HibernateUtils.getSessionfactory();

  @Override
  public Client saveUpdate(Client client) throws PhoneDictionaryException {
    if (client == null) {
      throw new IllegalArgumentException("Client can't be NULL for saving");
    }
    getSession().beginTransaction();
    try {
      if (client.getId() == 0) {
        getSession().persist(client);
      } else {
        getSession().merge(client);
      }
      getSession().getTransaction().commit();
    } finally {
      getSession().close();
    }
    return client;
  }

  @Override
  public Client findByID(int id) throws PhoneDictionaryException {
    if (id == 0) {
      throw new IllegalArgumentException("ID can't be 0");
    }
    Client cl = null;
    getSession().beginTransaction();
    try {
      cl = (Client) getSession().get(Client.class, id);
    } finally {
      getSession().close();
    }
    if (cl == null) {
      throw new NullPointerException("No client with Id: " + id);
    }
    return cl;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Client> findAll() throws PhoneDictionaryException {
    String sql = "SELECT c FROM Client c";
    getSession().beginTransaction();
    List<Client> res = new ArrayList<Client>();
    try {
      Query query = getSession().createQuery(sql);
      res = query.list();
    } finally {
      getSession().close();
    }
    return res;
  }

  @Override
  public List<Client> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResult)
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
    List<Client> res = new ArrayList<Client>();
    try {
      res =
          (List<Client>) criteria.getExecutableCriteria(getSession()).setFirstResult(firstResult)
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
      Client cl = findByID(id);
      if (cl != null) {
        getSession().beginTransaction();
        getSession().delete(cl);
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
