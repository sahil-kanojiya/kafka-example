package org.example;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.errors.TopicExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

import static org.example.config.KafkaConfig.BOOTSTRAP_SERVERS;
import static org.example.config.KafkaConfig.NUM_PARTITIONS;
import static org.example.config.KafkaConfig.REPLICATION_FACTOR;
import static org.example.config.KafkaConfig.TOPIC;

public class Arch {

    public static final Logger log = LoggerFactory.getLogger(Arch.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        try (AdminClient adminClient = AdminClient.create(properties)) {
            NewTopic newTopic = new NewTopic(TOPIC, NUM_PARTITIONS, REPLICATION_FACTOR);
            newTopic.configs(Collections.singletonMap(TopicConfig.CLEANUP_POLICY_CONFIG, "compact"));
            adminClient.createTopics(Collections.singleton(newTopic)).all().get();
            log.info("Topic created successfully!");
        } catch (TopicExistsException e) {
            log.warn("Topic already exists: {}", TOPIC);
        } catch (Exception ex) {
            log.error("Ex occurred:", ex);
        }
    }
}
