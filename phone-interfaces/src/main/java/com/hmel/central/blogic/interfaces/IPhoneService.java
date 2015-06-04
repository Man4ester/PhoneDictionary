package com.hmel.central.blogic.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hmel.central.models.Phone;
import com.hmel.exception.PhoneDictionaryException;

public interface IPhoneService {

  public Phone saveUpdate(Phone phones) throws PhoneDictionaryException;

  public Phone findByID(int id) throws PhoneDictionaryException;

  public List<Phone> findAll() throws PhoneDictionaryException;

  public List<Phone> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResult)
      throws PhoneDictionaryException;

  public void delete(int id) throws PhoneDictionaryException;

}
