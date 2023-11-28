package at.fhtw.swen3.paperless.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringDocConfiguration {

    public static final String EXCHANGE = "rabbitExchange";
    public static final String QUEUE = "rabbitQueue";
    public static final String QUEUE_KEY = "rabbitQueueKey";

    public static final String ECHO_IN_QUEUE_NAME = "rabbitEchoIn";
    public static final String ECHO_OUT_QUEUE_NAME = "rabbitEchoOut";
    public static final String ECHO_MESSAGE_COUNT_PROPERTY_NAME = "MessageCount";

    @Bean
    public Queue echoInQueue() {
        return new Queue(ECHO_IN_QUEUE_NAME, false);
    }

    @Bean
    public Queue echoOutQueue() {
        return new Queue(ECHO_OUT_QUEUE_NAME, false);
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("rabbitmq");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setDefaultReceiveQueue(ECHO_IN_QUEUE_NAME);
        return rabbitTemplate;
    }

    @Bean(name = "at.fhtw.swen3.paperless.config.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("Paperless Rest Server").description("No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)").version("1.0"));
    }
}