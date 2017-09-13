package org.axonframework.sample.axonbank.myaxonbank;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.CreateAccountCommand;
import org.axonframework.spring.config.EnableAxon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableAxon
@SpringBootApplication
public class MyAxonbankApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext cac = SpringApplication.run(MyAxonbankApplication.class, args);
    CommandBus commandBus = cac.getBean(CommandBus.class);
    commandBus.dispatch(asCommandMessage(new CreateAccountCommand("123", 1000)));
  }
}
