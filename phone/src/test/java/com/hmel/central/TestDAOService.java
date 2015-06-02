package com.hmel.central;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

import com.hmel.central.blogic.interfaces.IDAOClientService;
import com.hmel.central.models.Client;
import com.hmel.exception.PhoneDictionaryException;

public class TestDAOService {

  private static final Logger logger = LoggerFactory.getLogger(TestDAOService.class);

  public static void main(String[] args) {
    logger.info("Start test");
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    IDAOClientService service = (IDAOClientService) context.getBean("DAOClientService");
    
    try {
      List<Client> lst =  service.findAll();
      logger.info("Size: "+lst.size());
    } catch (PhoneDictionaryException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
