package com.kafka.demos;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoCallback {

    private static final Logger logger = LoggerFactory.getLogger(ProducerDemoCallback.class);

    public static void main(String[] args) {

        logger.info("I'm a kafka producer!!!");




        // create Producer properties
        Properties properties = new Properties();
        /*properties.setProperty("boostrap.Servers", "127.0.0.1");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());*/

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // properties.setProperty("partitioner.class", RoundRobinPartitioner.class.getName());
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class.getName());
        // properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, DefaultPartitioner.class.getName());


        // create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


        // create producer record, (before send a data)
        // ProducerRecord<String, String> producerRecord =
        //         new ProducerRecord<>("demo_java", "hello, Young - Hi");

        // send the data - asynchronous
        for (int j = 0; j < 10; j++) {

            // create a Producer Record
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>("demo_java", "hello Young -> " + j);

            producer.send(
                    producerRecord,
                    (RecordMetadata metadata, Exception e) -> {

                        // executes every time a record successfully sent or an exception is thrown
                        if (e == null) {
                            // the record was successfully sent
                            logger.info("Received new metadata \n" +
                                    "Topic: " + metadata.topic() + "\n" +
                                    "Partition: " + metadata.partition() + "\n" +
                                    "Offset: " + metadata.offset() + "\n" +
                                    "Timestamp: " + metadata.timestamp());
                        } else {
                            logger.error("Error while producing", e);
                        }
                    });

            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        }


        // tell the producer to send all data and block until done - synchronous
        producer.flush();

        // flush and close Producer
        producer.close();

    }
}
