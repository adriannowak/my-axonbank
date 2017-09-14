package org.axonframework.sample.axonbank.myaxonbank;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitMQConfiguration {

    @Autowired
    private Environment env;

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cf =
            new CachingConnectionFactory(env.getProperty("spring.rabbitmq.host"));
        cf.setUsername(env.getProperty("spring.rabbitmq.username"));
        cf.setPassword(env.getProperty("spring.rabbitmq.password"));
        cf.setPort(Integer.parseInt(env.getProperty("spring.rabbitmq.port")));
        return cf;
    }

    @Bean
    Queue defaultStream() {
        return new Queue(env.getProperty("spring.application.queue"), true);
    }

    @Bean
    FanoutExchange eventBusExchange() {
        return new FanoutExchange(env.getProperty("spring.application.exchange"), true, false);
    }

    @Bean
    Binding binding() {
        return new Binding(env.getProperty("spring.application.queue"), Binding.DestinationType.QUEUE,
            env.getProperty("spring.application.exchange"), "*.*", null);
    }

    @Bean
    @Required
    RabbitAdmin rabbitAdmin() {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory());
        admin.setAutoStartup(true);
        admin.declareExchange(eventBusExchange());
        admin.declareQueue(defaultStream());
        admin.declareBinding(binding());
        return admin;
    }

    @Bean
    RabbitTransactionManager transactionManager() {
        RabbitTransactionManager rabbitTransactionManager =
            new RabbitTransactionManager(connectionFactory());
        return rabbitTransactionManager;
    }
}
