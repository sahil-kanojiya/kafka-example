package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static org.example.config.KafkaConfig.BOOTSTRAP_SERVERS;
import static org.example.config.KafkaConfig.KEY_SERIALIZER_CLASS;
import static org.example.config.KafkaConfig.TOPIC;
import static org.example.config.KafkaConfig.VALUE_SERIALIZER_CLASS;

public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KEY_SERIALIZER_CLASS);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER_CLASS);

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            for (int i = 0; i < 50; i++) {
                String key = "key" + i;
                String message = "message" + i;
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, message);
                producer.send(record);
                log.info("Sent record with key: {} message: {}", key, message);
            }
        } catch (RuntimeException ex) {
            log.error("Runtime ex occurred:", ex);
        }
    }
}
