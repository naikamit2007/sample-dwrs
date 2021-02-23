package com.dropwizard.examples;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class DropwizardexamplesConfiguration extends Configuration {
  @NotEmpty
  private String template;

  @NotEmpty
  private String defaultName = "Stranger";
  
  private Integer defaultNum = 1;
  
  @JsonProperty("metrics_enabled")
  private Boolean metricsEnabled = true;
  public Boolean metricsEnabled() {
      return metricsEnabled;
  }

  @JsonProperty
  public String getTemplate() {
      return template;
  }

  @JsonProperty
  public void setTemplate(String template) {
      this.template = template;
  }

  @JsonProperty
  public String getDefaultName() {
      return defaultName;
  }

  @JsonProperty
  public void setDefaultName(String name) {
      this.defaultName = name;
  }

  @JsonProperty
  public Integer getDefaultNum() {
    return defaultNum;
  }

  @JsonProperty
  public void setDefaultNum(Integer defaultNum) {
    this.defaultNum = defaultNum;
  }
}
