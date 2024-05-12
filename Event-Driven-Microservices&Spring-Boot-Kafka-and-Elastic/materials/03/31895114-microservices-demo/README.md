# Running the application
- Please enter the correct credentials in twitter4j.properties file.
- Then run TwitterToKafkaServiceApplication inside IntelliJ, or run with mvn spring-boot:run command
- Check docker-compose folder, and run kafka cluster using docker-compose -f common.yml -f kafka_cluster.yml up command
- Then check the docker containers using docker ps command
- Use standalone kafkacat or docker container(https://hub.docker.com/r/confluentinc/cp-kafkacat) to install kafkacat
- Then check the kafka cluster information using kafkacat -L -b localhost:19092 command