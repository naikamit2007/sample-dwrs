package com.dropwizard.examples;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.dropwizard.examples.health.FibonacciHealthCheck;
import com.dropwizard.examples.health.TemplateHealthCheck;
import com.dropwizard.examples.resources.HelloWorldResource;
import com.dropwizard.examples.service.FibonacciService;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardexamplesApplication extends Application<DropwizardexamplesConfiguration> {

  public static void main(final String[] args) throws Exception {
    new DropwizardexamplesApplication().run(args);
  }

  @Override
  public String getName() {
    return "Dropwizardexamples";
  }

  @Override
  public void initialize(final Bootstrap<DropwizardexamplesConfiguration> bootstrap) {
    // TODO: application initialization
  }

  @Override
  public void run(final DropwizardexamplesConfiguration configuration, final Environment environment) {
    final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(),
        configuration.getDefaultName(), configuration.getDefaultNum(), new FibonacciService(), environment.metrics());
    final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
    final FibonacciHealthCheck fibonacciHealthCheck = new FibonacciHealthCheck(new FibonacciService());

    if (configuration.metricsEnabled()) {

      final Graphite graphite = new Graphite(new InetSocketAddress("monitor.pubsubnet.vcn.oraclevcn.com", 2003));

      final GraphiteReporter reporter = GraphiteReporter.forRegistry(environment.metrics()).prefixedWith("prefix")
          .convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).filter(MetricFilter.ALL)
          .build(graphite);
      reporter.start(5, TimeUnit.SECONDS);

      final ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(environment.metrics()).build();
      consoleReporter.start(5, TimeUnit.SECONDS);
    }

    environment.healthChecks().register("template", healthCheck);
    environment.healthChecks().register("fibonacci", fibonacciHealthCheck);
    environment.jersey().register(resource);
  }

}
