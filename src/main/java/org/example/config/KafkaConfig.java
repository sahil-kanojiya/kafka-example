package org.example.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaConfig {

    public static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static final String KEY_SERIALIZER_CLASS = StringSerializer.class.getName();
    public static final String VALUE_SERIALIZER_CLASS = StringSerializer.class.getName();

    public static final String KEY_DESERIALIZER_CLASS = StringDeserializer.class.getName();
    public static final String VALUE_DESERIALIZER_CLASS = StringDeserializer.class.getName();

    public static final String TOPIC = "my-topic";
    public static final int NUM_PARTITIONS = 3;
    public static final short REPLICATION_FACTOR = 1;

    public static final String CONSUMER_GROUP_ID = "my-consumer-group";

    public static final String AUTO_OFFSET_RESET = "earliest";
}
