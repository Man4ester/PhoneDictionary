package com.hmel.central.blogic.services;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.hmel.central.blogic.interfaces.IClientService;
import com.hmel.central.models.Client;
import com.hmel.central.utils.HibernateUtils;

@Transactional
public class ClientService implements IClientService {
  
  private SessionFactory sessionFactory = HibernateUtils.getSessionfactory();


  @Override
  public Client saveUpdate(Client client) {
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
  public Client findByID(int id) {
    if (id == 0) {
      throw new IllegalArgumentException("ID can't be 0");
    }
    return null;
  }

  @Override
  public List<Client> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Client> findByCriteria(DetachedCriteria criteria, int from, int size) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(int id) {
    // TODO Auto-generated method stub

  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

}
