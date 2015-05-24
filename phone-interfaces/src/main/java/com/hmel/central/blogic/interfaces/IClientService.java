package com.hmel.central.blogic.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hmel.central.models.Client;
import com.hmel.exception.PhoneDictionaryException;

public interface IClientService {
  
  public Client saveUpdate(Client client) throws PhoneDictionaryException;
  
  public Client findByID(int id) throws PhoneDictionaryException;
  
  public List<Client> findAll() throws PhoneDictionaryException;
  
  public List<Client> findByCriteria(DetachedCriteria criteria, int from, int size) throws PhoneDictionaryException;
  
  public void delete(int id) throws PhoneDictionaryException;

}
