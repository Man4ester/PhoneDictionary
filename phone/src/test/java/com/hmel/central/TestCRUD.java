package com.hmel.central;

import com.hmel.central.blogic.interfaces.IClientService;
import com.hmel.central.blogic.services.ClientService;
import com.hmel.central.models.Client;

public class TestCRUD {
  
  public static void main(String[] args) {
    IClientService service = new ClientService();
    Client cl = new Client();
    cl.setFirstName("Burkovskiy");
    service.saveUpdate(cl);
    System.out.println("Stop");
  }

}
