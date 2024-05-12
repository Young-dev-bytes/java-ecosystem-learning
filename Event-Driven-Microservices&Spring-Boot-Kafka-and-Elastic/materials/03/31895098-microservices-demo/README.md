# Running the application
- Please enter the correct credentials in twitter4j.properties file.
- Then run TwitterToKafkaServiceApplication inside IntelliJ, or run with mvn spring-boot:run command
- Check WebClientConfig, where we defined WebClient bean
- Check the KafkaAdminClient class in kafka-admin module, where we added 3 methods 
to check the kafka and schema registry are up and kafka topics are created: createTopics, checkTopicsCreated and checkSchemaRegistry
