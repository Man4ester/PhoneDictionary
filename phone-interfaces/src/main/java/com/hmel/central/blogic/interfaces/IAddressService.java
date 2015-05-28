package com.hmel.central.blogic.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hmel.central.models.Address;
import com.hmel.exception.PhoneDictionaryException;

public interface IAddressService {
  public Address saveUpdate(Address addresses) throws PhoneDictionaryException;

  public Address findByID(int id) throws PhoneDictionaryException;

  public List<Address> findAll() throws PhoneDictionaryException;

  public List<Address> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResult)
      throws PhoneDictionaryException;

  public void delete(int id) throws PhoneDictionaryException;
}
