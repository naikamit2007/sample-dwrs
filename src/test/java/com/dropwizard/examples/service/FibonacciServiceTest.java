package com.dropwizard.examples.service;

import org.junit.jupiter.api.Test;

class FibonacciServiceTest {

  @Test
  void testGetFibonacciObject() {
    FibonacciService fibonacciService = new FibonacciService();
    fibonacciService.getFibonacciObject(6);
  }

}
