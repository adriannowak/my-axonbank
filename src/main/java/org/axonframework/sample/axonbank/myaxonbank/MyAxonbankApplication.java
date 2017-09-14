package org.axonframework.sample.axonbank.myaxonbank;

import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.spring.config.EnableAxon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAxon
@SpringBootApplication
public class MyAxonbankApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyAxonbankApplication.class, args);
  }

  @Configuration
  static class BankConfiguration {

    @Bean
    public EventStorageEngine eventStorageEngine() {
      return new InMemoryEventStorageEngine();
    }
  }
}
