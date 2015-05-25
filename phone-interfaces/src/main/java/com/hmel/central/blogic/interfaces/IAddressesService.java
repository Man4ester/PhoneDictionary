package com.hmel.central.blogic.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hmel.central.models.Addresses;
import com.hmel.exception.PhoneDictionaryException;

public interface IAddressesService {
  public Addresses saveUpdate(Addresses addresses) throws PhoneDictionaryException;

  public Addresses findByID(int id) throws PhoneDictionaryException;

  public List<Addresses> findAll() throws PhoneDictionaryException;

  public List<Addresses> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResult)
      throws PhoneDictionaryException;

  public void delete(int id) throws PhoneDictionaryException;
}
