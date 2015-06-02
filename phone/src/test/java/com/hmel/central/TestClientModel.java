package com.hmel.central;

import java.util.HashSet;
import java.util.Set;

import com.hmel.central.blogic.interfaces.IClientService;
import com.hmel.central.blogic.services.ClientService;
import com.hmel.central.models.Address;
import com.hmel.central.models.Client;
import com.hmel.central.models.Phone;
import com.hmel.exception.PhoneDictionaryException;

public class TestClientModel {

  public static void main(String[] args) {
    Set<Phone> phones = new HashSet<Phone>();
    phones.add(new Phone("94673456546"));
    phones.add(new Phone("345345"));
    phones.add(new Phone("456356546"));

    Set<Address> addresses = new HashSet<Address>();
    addresses.add(new Address("Sity3", "Street3"));
    addresses.add(new Address("Sity4", "Street4"));

    Client client = new Client("Sydorchuk", "Alex", addresses, phones);

    IClientService clientService = new ClientService();
    try {
      clientService.saveUpdate(client);
    } catch (PhoneDictionaryException e) {
      e.printStackTrace();
    }

    try {
      Client clientOut = clientService.findByID(2);
      System.out.println(clientOut.getFirstName());

      Set<Address> addressesOut = clientOut.getAdresses();
      for (Address address : addressesOut) {
        System.out.println(address.getAddress() + address.getCity());
      }

      System.out.println();

      Set<Phone> phonesOut = clientOut.getPhones();
      for (Phone phone : phonesOut) {
        System.out.println(phone.getPhone());
      }
    } catch (PhoneDictionaryException e) {
      e.printStackTrace();
    }

  }

}
