package com.example.myapexapp;

public class Employee
{
  String fullName;
  String id;
  double amount;

  public String getFullName() { return fullName; }
  public void   setFullName(String v) { fullName = v; }

  public String getId() { return id; }
  public void   setId(String v) { id = v; }

  public double getAmount() { return amount; }
  public void   setAmount(double v) { amount = v; }

  public String toString() {
    return String.format("name = %s, id = %s, amount = %s", fullName, id, amount);
  }
}
