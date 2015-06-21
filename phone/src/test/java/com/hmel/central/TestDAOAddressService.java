package com.hmel.central;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hmel.central.blogic.interfaces.IDAOAddressService;
import com.hmel.central.models.Address;
import com.hmel.exception.PhoneDictionaryException;

public class TestDAOAddressService {
    private static final String NAME_DAO_ADDRESS_SERVICE = "DAOAddressService";

    private static final Logger logger = LoggerFactory
	    .getLogger(TestDAOAddressService.class);

    public static void main(String[] args) {
	logger.info("Start test");
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
		"spring-context.xml");

	IDAOAddressService service = (IDAOAddressService) context
		.getBean(NAME_DAO_ADDRESS_SERVICE);

	try {
	    List<Address> lst = service.findAll();
	    System.out.println("stop");
	} catch (PhoneDictionaryException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
