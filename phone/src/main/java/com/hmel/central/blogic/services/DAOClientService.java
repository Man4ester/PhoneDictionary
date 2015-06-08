package com.hmel.central.blogic.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hmel.central.blogic.interfaces.IDAOClientService;
import com.hmel.central.models.Client;
import com.hmel.dao.blogic.services.AbstractHibernateDAO;

/**
 * @author Burkovskiy Alexander
 */
@Transactional(value="transactionManager")
@Repository
public class DAOClientService extends AbstractHibernateDAO<Client, Integer> implements IDAOClientService{
 
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(DAOClientService.class); 

}
