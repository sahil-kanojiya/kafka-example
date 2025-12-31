# Kafka

Commands to run compose and start console producer and consumer:
1) ```docker compose up -d```
2) ```docker exec -it kafka kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3```
3) ```docker exec -it kafka kafka-console-producer.sh --topic my-topic --bootstrap-server localhost:9092```
4) ```docker exec -it kafka kafka-console-consumer.sh --topic my-topic --from-beginning --bootstrap-server localhost:29092```

Steps to run compose and java kafka client
1) ```docker compose up -d```
2) run org/example/kafka/Arch.java to create topic 
3) run org/example/kafka/Consumer.java
4) run org/example/kafka/Producer.java

