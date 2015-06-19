package com.hmel.central;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.central.blogic.interfaces.IDAOAddressService;
import com.hmel.central.models.Address;
import com.hmel.central.models.Client;
import com.hmel.exception.PhoneDictionaryException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class AddressIntegrationTest {
    private static final String NAME_DAO_ADDRESS_SERVICE = "DAOAddressService";

    private static final String TEST_NAME_CITY = "Khmelnitsky";
    private static final String TEST_ADDRESS = "Instytutska street 5";

    private static final Logger logger = LoggerFactory
	    .getLogger(AddressIntegrationTest.class);

    @Test
    public void test() {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
		"spring-context.xml");

	IDAOAddressService service = (IDAOAddressService) context
		.getBean(NAME_DAO_ADDRESS_SERVICE);

	Address inAddress = new Address();
	inAddress.setCity(TEST_NAME_CITY);
	inAddress.setAddress(TEST_ADDRESS);

	Address savingAddress = new Address();
	savingAddress.setCity(inAddress.getCity());
	savingAddress.setAddress(inAddress.getAddress());
	try {
	    savingAddress = service.save(savingAddress);
	} catch (PhoneDictionaryException e) {
	    logger.error(e.getMessage());
	}

	assertThat(savingAddress).isNotNull().as(
		"After saving we should have the same order in the DB");
	assertThat(inAddress.getCity()).isEqualTo(savingAddress.getCity()).as(
		"The first name's should be equals");
	assertThat(inAddress.getAddress())
		.isEqualTo(savingAddress.getAddress()).as(
			"The last name's should be equals");

	Address outAddress = null;
	try {
	    outAddress = service.findOne(savingAddress.getId());
	} catch (PhoneDictionaryException e) {
	    logger.error(e.getMessage());
	}

	assertThat(outAddress).isNotNull().as(
		"After saving we should have the same order in the DB");
	assertThat(savingAddress.getId()).isEqualTo(outAddress.getId()).as(
		"Id's should be the same");
	assertThat(savingAddress.getCity()).isEqualTo(outAddress.getCity()).as(
		"The first name's should be equals");
	assertThat(savingAddress.getAddress()).isEqualTo(
		outAddress.getAddress()).as("The last name's should be equals");
    }

}
