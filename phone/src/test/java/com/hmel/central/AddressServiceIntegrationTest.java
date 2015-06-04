package com.hmel.central;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.central.blogic.interfaces.IAddressService;
import com.hmel.central.models.Address;
import com.hmel.exception.PhoneDictionaryException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class AddressServiceIntegrationTest {

  private static final String EXAMPLE_CITY = "Khmelnitsky";
  private static final String EXAMPLE_STREET = "Proskurivska street, 156";

  @Autowired
  private IAddressService iAddressService;

  @Test
  public void test() {
    try {
      Address address = new Address();
      address.setCity(EXAMPLE_CITY);
      address.setAddress(EXAMPLE_STREET);

      address = iAddressService.saveUpdate(address);

      assertThat(address.getId()).isNotNull().describedAs("After saving id should be filled");
      assertThat(address.getAddress()).isEqualTo(EXAMPLE_STREET).as(
          "After saving first name should be the same as before insert");

      Address address2 = iAddressService.findByID(address.getId());

      assertThat(address2).isNotNull().as("After saving we should have the same order in the DB");
      assertThat(address.getId()).isEqualTo(address2.getId()).as("Id's should be the same");
      assertThat(address.getAddress()).isEqualTo(address2.getAddress()).as(
          "The first name's should be equals");

    } catch (PhoneDictionaryException e) {
      e.printStackTrace();
    }
  }
}
