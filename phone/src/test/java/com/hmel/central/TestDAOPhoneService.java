package com.hmel.central;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hmel.central.blogic.interfaces.IDAOPhoneService;
import com.hmel.central.models.Phone;
import com.hmel.exception.PhoneDictionaryException;

public class TestDAOPhoneService {
    private static final String NAME_DAO_PHONE_SERVICE = "DAOPhoneService";

    private static final Logger logger = LoggerFactory
	    .getLogger(TestDAOPhoneService.class);

    public static void main(String[] args) {
	logger.info("Start test");
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
		"spring-context.xml");

	IDAOPhoneService service = (IDAOPhoneService) context
		.getBean(NAME_DAO_PHONE_SERVICE);

	try {
	    List<Phone> lst = service.findAll();
	} catch (PhoneDictionaryException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
