package com.hmel.central;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.central.blogic.interfaces.IClientService;
import com.hmel.central.models.Client;
import com.hmel.exception.PhoneDictionaryException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class ClientServiceIntegrationTest {

	private static final String FIRST_NAME = "Burkovskiy";
	@Autowired
	private IClientService iClientService;

	@Test
	public void test() {
		try {
			Client client = new Client();
			client.setFirstName(FIRST_NAME);

			client = iClientService.saveUpdate(client);

			assertThat(client.getId()).isNotNull().describedAs(
					"After saving id should be filled");
			assertThat(client.getFirstName())
					.isEqualTo(FIRST_NAME)
					.as("After saving first name should be the same as before insert");

			Client client2 = iClientService.findByID(client.getId());

			assertThat(client2).isNotNull().as(
					"After saving we should have the same order in the DB");
			assertThat(client.getId()).isEqualTo(client2.getId()).as(
					"Id's should be the same");
			assertThat(client.getFirstName()).isEqualTo(client2.getFirstName())
					.as("The first name's should be equals");

		} catch (PhoneDictionaryException e) {
			e.printStackTrace();
		}
	}

}
