package org.axonframework.sample.axonbank.myaxonbank;

import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.Channel;

//@EnableAxon
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

    @Bean
    public SpringAMQPMessageSource springAMQPMessageSource(Serializer serializer) {
      return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {

        @RabbitListener(queues = "bank-queue")
        @Override
        public void onMessage(Message message, Channel channel) throws Exception {
          super.onMessage(message, channel);
        }
      };
    }
  }
}
