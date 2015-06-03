package com.hmel.central;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.central.blogic.interfaces.IClientService;
import com.hmel.central.models.Client;
import com.hmel.exception.PhoneDictionaryException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class CRUDIntegrationTest {

	@Autowired
	private IClientService service;

	@Test
	public void test() {
		try {
			Client cl = new Client();
			cl.setFirstName("Burkovskiy");

			Client cls = service.saveUpdate(cl);
			service.delete(cls.getId());
		} catch (PhoneDictionaryException e) {
			e.printStackTrace();
		}
	}

}
