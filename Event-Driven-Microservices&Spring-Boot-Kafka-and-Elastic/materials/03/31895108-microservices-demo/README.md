# Running the application
- Please enter the correct credentials in twitter4j.properties file.
- Then run TwitterToKafkaServiceApplication inside IntelliJ, or run with mvn spring-boot:run command
- Check the kafka-model module. We have twitter.avsc avro schema file which is used by avro-maven-plugin to create the java model
- Check the avro-maven-plugin usage in kafka-model/pom.xml file