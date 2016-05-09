package com.example.myapexapp;

public class Address
{

  @Override
  public String toString()
  {
    return "Address [city=" + city + ", country=" + country + "]";
  }

  private String city;
  private String country;

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }
}
