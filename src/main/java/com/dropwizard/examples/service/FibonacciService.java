package com.dropwizard.examples.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.dropwizard.examples.api.Fibonacci;

public class FibonacciService {

  public Fibonacci getFibonacciObject(int num) {
    List<Integer> sequence = new ArrayList<Integer>();
    
    int sum = Stream.iterate(new int[]{1, 2}, t -> new int[]{t[1], t[0] + t[1]})
    .limit(num)
    .map(t -> t[0])
    .peek(t -> sequence.add(t))
    //.forEach(x -> System.out.println(x))
    .reduce(0, (subtotal, element) -> subtotal + element);
    
    //System.out.println(sum);
    Fibonacci fibonacci = new Fibonacci(num, sequence, sum);
    return fibonacci;
  }
}
