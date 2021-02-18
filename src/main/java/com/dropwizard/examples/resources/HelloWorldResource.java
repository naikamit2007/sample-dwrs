package com.dropwizard.examples.resources;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.dropwizard.examples.api.Fibonacci;
import com.dropwizard.examples.api.Saying;
import com.dropwizard.examples.service.FibonacciService;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

  private final String template;
  private final String defaultName;
  private final Integer defaultNum;
  private final AtomicLong counter;
  private final FibonacciService fibonacciService;

  public HelloWorldResource(String template, String defaultName, Integer defaultNum, FibonacciService fibonacciService) {
    this.template = template;
    this.defaultName = defaultName;
    this.defaultNum = defaultNum;
    this.counter = new AtomicLong();
    this.fibonacciService = new FibonacciService();
  }

  @GET
  @Timed
  public Saying sayHello(@QueryParam("name") Optional<String> name) {
    final String value = String.format(template, name.orElse(defaultName));
    return new Saying(counter.incrementAndGet(), value);
  }

  @GET
  @Path("/fib")
  @Timed
  public Fibonacci getFib(@QueryParam("number") Optional<Integer> num) {
    final int number = num.orElse(defaultNum);
    Fibonacci fibonacci = fibonacciService.getFibonacciObject(number);
    return fibonacci;
  }
}
