package com.hmel;

import java.util.ArrayList;
import java.util.List;

import com.hmel.central.blogic.interfaces.IClientService;
import com.hmel.central.blogic.services.ClientService;
import com.hmel.central.models.Address;
import com.hmel.central.models.Client;
import com.hmel.central.models.Phone;
import com.hmel.exception.PhoneDictionaryException;


public class Main {
  public static void main(String[] args) {

    Address address = new Address("Kyiv", "");

    List<Phone> phones = new ArrayList<Phone>();
    phones.add(new Phone("0977776080"));
    phones.add(new Phone("0977776081"));

    Client client = new Client("Ivanov", "Ivan", address, phones);

    IClientService clientService = new ClientService();
    try {
      clientService.saveUpdate(client);
    } catch (PhoneDictionaryException e) {
      e.printStackTrace();
    }

  }
}
