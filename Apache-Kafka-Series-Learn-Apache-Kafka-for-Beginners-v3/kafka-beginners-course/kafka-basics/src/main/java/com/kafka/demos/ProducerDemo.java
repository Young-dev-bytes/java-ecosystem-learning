package com.kafka.demos;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    private static final Logger logger = LoggerFactory.getLogger(ProducerDemo.class);

    public static void main(String[] args) {

        logger.info("I'm a kafka producer!!!");


        // create Producer properties
        Properties properties = new Properties();
        /*properties.setProperty("boostrap.Servers", "127.0.0.1");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());*/

        // offset
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        // create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


        // create producer record, (before send a data)
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("demo_java", "hello, java");

        // send the data - asynchronous
        producer.send(producerRecord);
       /* producer.send(producerRecord,
                (RecordMetadata metadata, Exception exception) -> {

                    // logger.info(String.valueOf(exception));
                    System.out.println(exception); // null
                    System.out.println(metadata.partition()); // 3
                    if(exception != null) {
                        exception.printStackTrace();
                    } else {
                        System.out.println("The offset of the record we just sent is: " + metadata.offset()); // 1
                    }


        });*/

        // tell the producer to send all data and block until done - synchronous
        producer.flush();

        // flush and close Producer
        producer.close();

    }
}
