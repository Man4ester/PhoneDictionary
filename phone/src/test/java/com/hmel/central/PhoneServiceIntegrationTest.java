package com.hmel.central;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.central.blogic.interfaces.IPhoneService;
import com.hmel.central.models.Phone;
import com.hmel.central.models.Phone.PhoneType;
import com.hmel.exception.PhoneDictionaryException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class PhoneServiceIntegrationTest {

  private static final String EXAMPLE_PHONE = "234534522345";
  private static final PhoneType EXAMPLE_PHONE_TYPE = PhoneType.HOME;
  @Autowired
  private IPhoneService iPhoneService;

  @Test
  public void test() {
    try {
      Phone phone = new Phone();
      phone.setCreationTime(new Date());
      phone.setModifiedTime(new Date());
      phone.setPhone(EXAMPLE_PHONE);
      phone.setPhoneType(EXAMPLE_PHONE_TYPE);

      phone = iPhoneService.saveUpdate(phone);

      assertThat(phone.getId()).isNotNull().describedAs("After saving id should be filled");
      assertThat(phone.getPhone()).isEqualTo(EXAMPLE_PHONE).as(
          "After saving first name should be the same as before insert");

      Phone phone2 = iPhoneService.findByID(phone.getId());

      assertThat(phone2).isNotNull().as("After saving we should have the same order in the DB");
      assertThat(phone.getId()).isEqualTo(phone2.getId()).as("Id's should be the same");
      assertThat(phone.getPhone()).isEqualTo(phone2.getPhone()).as(
          "The first name's should be equals");

    } catch (PhoneDictionaryException e) {
      e.printStackTrace();
    }
  }
}
