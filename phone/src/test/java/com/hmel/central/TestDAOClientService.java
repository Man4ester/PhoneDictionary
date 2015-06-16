package com.hmel.central;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hmel.central.blogic.interfaces.IDAOClientService;
import com.hmel.central.models.Client;
import com.hmel.exception.PhoneDictionaryException;

public class TestDAOClientService {
    private static final String NAME_DAO_CLIENT_SERVICE = "DAOClientService";

    private static final Logger logger = LoggerFactory
	    .getLogger(TestDAOClientService.class);

    public static void main(String[] args) {
	logger.info("Start test");
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
		"spring-context.xml");

	IDAOClientService service = (IDAOClientService) context
		.getBean(NAME_DAO_CLIENT_SERVICE);

	try {
	    List<Client> lst = service.findAll();
	    System.out.println("stop");
	} catch (PhoneDictionaryException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
