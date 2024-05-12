# Running the application
- Please enter the correct credentials in twitter4j.properties file.
- Then run TwitterToKafkaServiceApplication inside IntelliJ, or run with mvn spring-boot:run command
- Check the new kafka-admin module, where we added KafkaAdminConfig and started to implement KafkaAdminClient
- Check the new common-config module, where we added the RetryConfig class to create a RetryTemplate
- Check also the app-config-data module, where we added RetryConfigData and KafkaConfigData. Then see the default values in twitter-to-kafka-service application.yml file