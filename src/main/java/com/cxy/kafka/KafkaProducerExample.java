package com.cxy.kafka;

/**
 * Created by lidongpeng on 2018/3/29.
 */
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerExample {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        /**
         * The producer maintains buffers of unsent records for each partition
         * 生产者保持该消息不发送，
         */
        props.put("batch.size", 16384);
        /**
         * 最长等待时间，与batch.size配合，满足其中一个就可以发送，毫秒级
         */
        props.put("linger.ms", 1);
        /**
         * 设置生产者可用内存大小，当发送速度远大于消费速度，就会导致内存耗尽，发送消息线程阻塞，超过这个时间max.block.ms，将会抛出TimeoutException
         */
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 100; i++) {
            Future<RecordMetadata> future = producer.send(new ProducerRecord<>("test", Integer.toString(i), Integer.toString(i)));
            try {
                RecordMetadata recordMetadata=future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        producer.close();
    }
}