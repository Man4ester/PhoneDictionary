package com.hmel.central.blogic.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hmel.central.models.Client;

public interface IClientService {
  
  public Client saveUpdate(Client client);
  
  public Client findByID(int id);
  
  public List<Client> findAll();
  
  public List<Client> findByCriteria(DetachedCriteria criteria, int from, int size);
  
  public void delete(int id);

}
