package com.teedjay.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class Producer {

    AtomicLong count = new AtomicLong();
    KafkaProducer<String, String> producer;

    @PostConstruct
    void createKafkaProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "the-producer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<String, String>(props);
    }

    public void send(String msg) {
        String key = "" + count.incrementAndGet();
        producer.send(new ProducerRecord<>("quarkus-kafka-vanilla-topic", key, msg));
        producer.flush();
    }

}
