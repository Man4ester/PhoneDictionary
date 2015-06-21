package com.hmel.central.blogic.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hmel.central.blogic.interfaces.IDAOPhoneService;
import com.hmel.central.models.Phone;
import com.hmel.dao.blogic.services.AbstractHibernateDAO;

/**
 * @author Alexandr Sydorchuk
 */
@Transactional(value = "transactionManager")
@Repository
public class DAOPhoneService extends AbstractHibernateDAO<Phone, Integer>
	implements IDAOPhoneService {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory
	    .getLogger(DAOPhoneService.class);

}
