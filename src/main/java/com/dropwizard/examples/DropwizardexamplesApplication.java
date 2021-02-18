package com.dropwizard.examples;

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
    public void run(final DropwizardexamplesConfiguration configuration,
                    final Environment environment) {
      final HelloWorldResource resource = new HelloWorldResource(
          configuration.getTemplate(),
          configuration.getDefaultName(),
          configuration.getDefaultNum(),
          new FibonacciService()
      );
      final TemplateHealthCheck healthCheck =
          new TemplateHealthCheck(configuration.getTemplate());
      final FibonacciHealthCheck fibonacciHealthCheck =
          new FibonacciHealthCheck(new FibonacciService());
      environment.healthChecks().register("template", healthCheck);
      environment.healthChecks().register("fibonacci", fibonacciHealthCheck);
      environment.jersey().register(resource);
    }

}
