package com.hmel.central;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.central.blogic.interfaces.IDAOPhoneService;
import com.hmel.central.models.Phone;
import com.hmel.exception.PhoneDictionaryException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class PhoneIntegrationTest {
    private static final String NAME_DAO_PHONE_SERVICE = "DAOPhoneService";

    private static final String EXAMPLE_PHONE = "09799999999";

    private static final Logger logger = LoggerFactory
	    .getLogger(AddressIntegrationTest.class);

    @Test
    public void test() {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
		"spring-context.xml");

	IDAOPhoneService service = (IDAOPhoneService) context
		.getBean(NAME_DAO_PHONE_SERVICE);

	Phone inPhone = new Phone();
	inPhone.setPhone(EXAMPLE_PHONE);

	Phone savingPhone = new Phone();
	savingPhone.setPhone(inPhone.getPhone());
	try {
	    savingPhone = service.save(savingPhone);
	} catch (PhoneDictionaryException e) {
	    logger.error(e.getMessage());
	}

	assertThat(savingPhone).isNotNull().as(
		"After saving we should have the same order in the DB");
	assertThat(inPhone.getPhone()).isEqualTo(savingPhone.getPhone()).as(
		"The first name's should be equals");

	Phone outPhone = null;
	try {
	    outPhone = service.findOne(savingPhone.getId());
	} catch (PhoneDictionaryException e) {
	    logger.error(e.getMessage());
	}

	assertThat(outPhone).isNotNull().as(
		"After saving we should have the same order in the DB");
	assertThat(savingPhone.getId()).isEqualTo(outPhone.getId()).as(
		"Id's should be the same");
	assertThat(savingPhone.getPhone()).isEqualTo(outPhone.getPhone()).as(
		"The first name's should be equals");
    }
}
