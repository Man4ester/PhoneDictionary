package com.hmel.central.blogic.services;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hmel.central.blogic.interfaces.IClientService;
import com.hmel.central.models.Client;

public class ClientService implements IClientService {

  @Override
  public Client saveUpdate(Client client) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Client findByID(int id) {
    // TODO Auto-generated method stub
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

}
