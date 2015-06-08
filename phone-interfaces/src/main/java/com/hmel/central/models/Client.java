package com.hmel.central.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String firstName;

  private String lastName;

  @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinColumn(name = "clientId")
  private List<Address> addresses = new ArrayList<Address>();

  @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinColumn(name = "clientId")
  private List<Phone> phones = new ArrayList<Phone>();

  public Client() {}

  public Client(String firstName, String lastName, List<Address> addresses, List<Phone> phones) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.addresses = addresses;
    this.setPhones(phones);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Address> getAdresses() {
    return addresses;
  }

  public void setAdresses(List<Address> adresses) {
    this.addresses = adresses;
  }

  public List<Phone> getPhones() {
    return phones;
  }

  public void setPhones(List<Phone> phones) {
    this.phones = phones;
  }
}
