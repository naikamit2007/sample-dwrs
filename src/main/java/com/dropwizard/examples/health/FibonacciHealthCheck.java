package com.dropwizard.examples.health;

import com.codahale.metrics.health.HealthCheck;
import com.dropwizard.examples.api.Fibonacci;
import com.dropwizard.examples.service.FibonacciService;

public class FibonacciHealthCheck extends HealthCheck {
  
  private final FibonacciService fibonacciService;

  public FibonacciHealthCheck(FibonacciService fibonacciService) {
    super();
    this.fibonacciService = fibonacciService;
  }


  @Override
  protected Result check() throws Exception {
    final Fibonacci fibonacci = fibonacciService.getFibonacciObject(2);
    if (fibonacci.getTotal()!=3 || fibonacci.getMemberCount()!=2) {
      return Result.unhealthy("fibonacci service has issue!");
    }
    return Result.healthy();
  }
}
