package examples.avro.serializer.thrif;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Native_GenericRecord_ConsumerExample {
  public static void main(String[] args){

    String url = "http://localhost:8081";


    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaThriftDeserializer");
    props.put("schema.registry.url", url);
    props.put("group.id", "consumer-tutorial");
    props.put("convertToGenericRecord", "");


    KafkaConsumer<String, GenericRecord> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Arrays.asList("ThriftNative_"));


    try {
      while (true) {
        ConsumerRecords<String, GenericRecord> records = consumer.poll(100);
        for (ConsumerRecord<String, GenericRecord> record : records)
          System.out.println(record.offset() + ": " + record.value());
      }
    } finally {
      consumer.close();
    }
  }
}
