package com.exchnage.service.currencyexchangedemo;

@FunctionalInterface
public interface Conditionally {
  public boolean test(Client c);
}
