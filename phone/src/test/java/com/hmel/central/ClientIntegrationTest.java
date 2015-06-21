package com.hmel.central;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.central.blogic.interfaces.IDAOClientService;
import com.hmel.central.models.Client;
import com.hmel.exception.PhoneDictionaryException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class ClientIntegrationTest {
    private static final String NAME_DAO_CLIENT_SERVICE = "DAOClientService";

    private static final String FIRST_NAME = "Alexandr";
    private static final String LAST_NAME = "Sydorchuk";

    private static final Logger logger = LoggerFactory
	    .getLogger(ClientIntegrationTest.class);

    @Test
    public void test() {
	Client inClient = new Client();
	inClient.setFirstName(FIRST_NAME);
	inClient.setLastName(LAST_NAME);

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
		"spring-context.xml");

	IDAOClientService service = (IDAOClientService) context
		.getBean(NAME_DAO_CLIENT_SERVICE);

	Client savingClient = new Client();
	savingClient.setFirstName(inClient.getFirstName());
	savingClient.setLastName(inClient.getLastName());
	try {
	    savingClient = service.save(savingClient);
	} catch (PhoneDictionaryException e) {
	    logger.error(e.getMessage());
	}

	assertThat(savingClient).isNotNull().as(
		"After saving we should have the same order in the DB");
	assertThat(inClient.getFirstName()).isEqualTo(
		savingClient.getFirstName()).as(
		"The first name's should be equals");
	assertThat(inClient.getLastName())
		.isEqualTo(savingClient.getLastName()).as(
			"The last name's should be equals");

	Client outClient = null;
	try {
	    outClient = service.findOne(savingClient.getId());
	} catch (PhoneDictionaryException e) {
	    logger.error(e.getMessage());
	}

	assertThat(outClient).isNotNull().as(
		"After saving we should have the same order in the DB");
	assertThat(savingClient.getId()).isEqualTo(outClient.getId()).as(
		"Id's should be the same");
	assertThat(savingClient.getFirstName()).isEqualTo(
		outClient.getFirstName()).as(
		"The first name's should be equals");
	assertThat(savingClient.getLastName()).isEqualTo(
		outClient.getLastName()).as("The last name's should be equals");
    }
}
