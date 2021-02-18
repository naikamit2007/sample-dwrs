package com.dropwizard.examples.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fibonacci {

  private int memberCount;
  private List<Integer> sequence;
  private long total;
  
  public Fibonacci() {
  }

  public Fibonacci(int memberCount, List<Integer> sequence, long total) {
    super();
    this.memberCount = memberCount;
    this.sequence = sequence;
    this.total = total;
  }

  @JsonProperty
  public int getMemberCount() {
    return memberCount;
  }

  @JsonProperty
  public List<Integer> getSequence() {
    return sequence;
  }

  @JsonProperty
  public long getTotal() {
    return total;
  }
}
