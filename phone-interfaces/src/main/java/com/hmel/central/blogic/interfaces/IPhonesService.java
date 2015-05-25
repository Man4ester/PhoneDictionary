package com.hmel.central.blogic.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hmel.central.models.Phones;
import com.hmel.exception.PhoneDictionaryException;

public interface IPhonesService {

  public Phones saveUpdate(Phones phones) throws PhoneDictionaryException;

  public Phones findByID(int id) throws PhoneDictionaryException;

  public List<Phones> findAll() throws PhoneDictionaryException;

  public List<Phones> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResult)
      throws PhoneDictionaryException;

  public void delete(int id) throws PhoneDictionaryException;

}
