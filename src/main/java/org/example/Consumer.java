package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static org.example.config.KafkaConfig.AUTO_OFFSET_RESET;
import static org.example.config.KafkaConfig.BOOTSTRAP_SERVERS;
import static org.example.config.KafkaConfig.CONSUMER_GROUP_ID;
import static org.example.config.KafkaConfig.KEY_DESERIALIZER_CLASS;
import static org.example.config.KafkaConfig.TOPIC;
import static org.example.config.KafkaConfig.VALUE_DESERIALIZER_CLASS;

public class Consumer {

    public static final Logger log = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KEY_DESERIALIZER_CLASS);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER_CLASS);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_ID);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET);

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Collections.singletonList(TOPIC));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                records.forEach(record -> log.info("Consumed message: key: {} value: {}", record.key(), record.value()));
            }
        } catch (RuntimeException ex) {
            log.error("Runtime ex occurred:", ex);
        }
    }
}
