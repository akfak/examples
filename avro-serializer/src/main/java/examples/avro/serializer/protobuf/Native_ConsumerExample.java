package examples.avro.serializer.protobuf;

import com.protobuf.Serializers;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Native_ConsumerExample {
  public static void main(String[] args){

    String url = "http://localhost:8081";


    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaProtobufDeserializer");
    props.put("schema.registry.url", url);
    props.put("group.id", "consumer-tutorial");


    KafkaConsumer<String,Serializers.Serializer> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Arrays.asList("ProtobufNative"));


    try {
      while (true) {
        ConsumerRecords<String, Serializers.Serializer> records = consumer.poll(100);
        for (ConsumerRecord<String, Serializers.Serializer> record : records)
          System.out.println(record.offset() + ": " + record.value());
      }
    } finally {
      consumer.close();
    }
  }
}
