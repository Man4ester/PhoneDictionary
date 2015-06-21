package com.hmel.central.blogic.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hmel.central.blogic.interfaces.IDAOAddressService;
import com.hmel.central.models.Address;
import com.hmel.dao.blogic.services.AbstractHibernateDAO;

/**
 * @author Alexandr Sydorchuk
 */
@Transactional(value = "transactionManager")
@Repository
public class DAOAddressService extends AbstractHibernateDAO<Address, Integer>
	implements IDAOAddressService {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory
	    .getLogger(DAOAddressService.class);

}
