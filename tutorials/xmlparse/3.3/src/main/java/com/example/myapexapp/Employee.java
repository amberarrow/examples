package com.example.myapexapp;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee
{
  @XmlElement
  private String name;
  @XmlElement
  private int id;
  @XmlElement
  protected Date date;
  @XmlElement
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

