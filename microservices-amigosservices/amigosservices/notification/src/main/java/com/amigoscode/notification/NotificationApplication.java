package com.amigoscode.notification;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.amigoscode.notification",
                "com.amigoscode.amqp"
        }
)
@EnableEurekaClient
public class NotificationApplication /*implements CommandLineRunner */{
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    /*private RabbitMQMessageProducer producer;
    private NotificationConfig notificationConfig;

    public NotificationApplication(
            RabbitMQMessageProducer producer,
            NotificationConfig notificationConfig) {
        this.producer = producer;
        this.notificationConfig = notificationConfig;
    }*/


    /*@Override
    public void run(String... args) throws Exception {
        producer.publish(
                new Notification(
                        10000,
                        10000,
                        "test@gmail.com",
                        "test2",
                        "test3",
                        null),
                notificationConfig.getInternalExchange(),
                notificationConfig.getInternalNotificationRoutingKey());
    }*/
    /*@Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
                                        NotificationConfig notificationConfig) {

        return args -> {
            producer.publish(
                    new Notification(
                            10000,
                            10000,
                            "test@gmail.com",
                            "test2",
                            "test3" ,
                            null),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey());
        };

    }*/
}
