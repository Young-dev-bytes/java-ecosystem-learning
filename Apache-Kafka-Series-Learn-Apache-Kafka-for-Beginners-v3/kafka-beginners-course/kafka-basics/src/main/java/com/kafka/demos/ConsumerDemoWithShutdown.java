package com.kafka.demos;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


public class ConsumerDemoWithShutdown {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerDemoWithShutdown.class);

    public static void main(String[] args) {

        logger.info("I'm a kafka consumer!!!");

        String groupId = "my-java-application";
        String topic = "demo_java";


        // create consumer configuration
        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", groupId);
        properties.setProperty("auto.offset.reset", "earliest"); // none earliest latest

        // create a consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // get reference to the main thread
        final Thread mainThread = Thread.currentThread();

        // adding the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Detected a shutdown, let's exit by calling consumer.wakeup()...");
            consumer.wakeup();

            // join the main thread to allow the execution of the code in the main thread
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));


        try {

            // subscribe to a topic
            consumer.subscribe(Arrays.asList(topic));

            // poll from data
            while (true) {

                logger.info("Polling");

                ConsumerRecords<String, String> consumerRecords =
                        consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : consumerRecords) {

                    logger.info("Key: " + record.key() + ", Value: " + record.value());
                    logger.info("Partition: " + record.partition() + ", Offset: " + record.offset());
                }
            }

        } catch (WakeupException e) {
            logger.info("Consumer is starting to shut down");
        } catch (Exception e) {
            logger.error("Unexpected exception in the consumer", e);
        } finally {
            consumer.close();
            logger.info("The consumer is now gracefully shut down");
        }
    }
}
