
docker exec -it kafka1 /bin/bash 

kafka-topics --bootstrap-server localhost:9092 --list 

kafka-topics --bootstrap-server localhost:9092 --topic first_topic --create --partitions 3

kafka-topics --bootstrap-server localhost:9092 --topic first_topic --describe
kafka-topics --bootstrap-server localhost:9092 --topic first_topic --delete

kafka-console-producer --bootstrap-server localhost:9092 --producer-property partitioner.class=org.apache.kafka.clients.producer.RoundRobinPartitioner --topic first_topic

kafka-console-consumer --bootstrap-server localhost:9092 --topic first_topic --group my-first-application

kafka-console-consumer --bootstrap-server localhost:9092 --topic first_topic --group my-second-application

kafka-console-consumer --bootstrap-server localhost:9092 --topic first_topic --group my-first-application --from-beginning

kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group my-first-application


# list consumer groups
kafka-consumer-groups --bootstrap-server localhost:9092 --list



kafka-console-consumer --bootstrap-server localhost:9092 --topic wikimedia.recentchange --group my-first-consumer-group
