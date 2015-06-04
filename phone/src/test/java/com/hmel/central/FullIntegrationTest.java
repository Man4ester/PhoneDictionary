package com.hmel.central;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.central.blogic.interfaces.IClientService;
import com.hmel.central.models.Address;
import com.hmel.central.models.Client;
import com.hmel.central.models.Phone;
import com.hmel.exception.PhoneDictionaryException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class FullIntegrationTest {
  private static final String FIRST_NAME = "Burkovskiy";
  private static final String LAST_NAME = "Alexander";
  private static final String EXAMPLE_CITY = "Khmelnitsky";
  private static final String EXAMPLE_STREET_1 = "Proskurivska street, 156";
  private static final String EXAMPLE_STREET_2 = "Proskurivska street, 55";
  private static final String EXAMPLE_PHONE_1 = "0970000000";
  private static final String EXAMPLE_PHONE_2 = "0930000000";
  private static final String EXAMPLE_PHONE_3 = "0990000000";

  @Autowired
  private IClientService iClientService;

  @Test
  public void test() {
    try {
      Client client = new Client();
      client.setFirstName(FIRST_NAME);
      client.setLastName(LAST_NAME);

      Set<Phone> phones = new HashSet<Phone>();
      phones.add(new Phone(EXAMPLE_PHONE_1));
      phones.add(new Phone(EXAMPLE_PHONE_2));
      phones.add(new Phone(EXAMPLE_PHONE_3));

      Set<Address> addresses = new HashSet<Address>();
      addresses.add(new Address(EXAMPLE_CITY, EXAMPLE_STREET_1));
      addresses.add(new Address(EXAMPLE_CITY, EXAMPLE_STREET_2));

      client.setPhones(phones);
      client.setAdresses(addresses);

      client = iClientService.saveUpdate(client);

      assertThat(client.getId()).isNotNull().describedAs("After saving id should be filled");
      assertThat(client.getFirstName()).isEqualTo(FIRST_NAME).as(
          "After saving first name should be the same as before insert");

      Client client2 = iClientService.findByID(client.getId());

      System.out.println(client2.getPhones().size());
      System.out.println(client2.getAdresses().size());

      assertThat(client2).isNotNull().as("After saving we should have the same order in the DB");
      assertThat(client.getId()).isEqualTo(client2.getId()).as("Id's should be the same");
      assertThat(client.getFirstName()).isEqualTo(client2.getFirstName()).as(
          "The first name's should be equals");

    } catch (PhoneDictionaryException e) {
      e.printStackTrace();
    }
  }

}
