package com.hmel.central;

import java.util.List;

import com.hmel.central.blogic.interfaces.IClientService;
import com.hmel.central.blogic.services.ClientService;
import com.hmel.central.models.Client;
import com.hmel.exception.PhoneDictionaryException;

public class TestCRUD {
  
  public static void main(String[] args) {
    IClientService service = new ClientService();
    Client cl = new Client();
    cl.setFirstName("Burkovskiy");
    try {
      Client cls =service.findByID(2);
      List<Client> lst = service.findAll();
      System.out.println(lst.size());
      service.delete(3);
    } catch (PhoneDictionaryException e) {
      e.printStackTrace();
    }
    System.out.println("Stop");
  }

}
