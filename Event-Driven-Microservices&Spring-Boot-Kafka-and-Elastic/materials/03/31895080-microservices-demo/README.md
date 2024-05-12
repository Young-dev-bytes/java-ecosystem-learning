# Running the application
- Please enter the correct credentials in twitter4j.properties file.
- Then run TwitterToKafkaServiceApplication inside IntelliJ, or run with mvn spring-boot:run command
- Check KafkaProducerConfig and TwitterKafkaProducer classes, where we added config and then implemented
the logic to send the data to kafka using spring's KafkaTemplate class