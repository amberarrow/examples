package com.example.myapexapp;

import java.util.Date;

public class Employee
{
  private String name;
  private int id;
  protected Date date;
  private Address address;

  public String getName()
  {
    return name;
  }

  public void setName(String v)
  {
    name = v;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int v)
  {
    id = v;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date v)
  {
    date = v;
  }

  public Address getAddress()
  {
    return address;
  }

  public void setAddress(Address v)
  {
    address = v;
  }

  @Override
  public String toString()
  {
    return String.format("[name=%s, id=%s, date=%s, address=[city=%s, country=%s]]",
                         name, id, date, address.getCity(), address.getCountry());
  }
}  // Employee

