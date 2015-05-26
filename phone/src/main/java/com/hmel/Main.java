package com.hmel;

import com.hmel.central.blogic.interfaces.IAddressesService;
import com.hmel.central.blogic.services.AddressesService;
import com.hmel.central.models.Addresses;
import com.hmel.exception.PhoneDictionaryException;


public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world guys hello");

    Addresses addresses = new Addresses();
    addresses.setCity("Chmeln");
    addresses.setClientID(4);

    IAddressesService addressesService = new AddressesService();
    try {
      addressesService.saveUpdate(addresses);
    } catch (PhoneDictionaryException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
