package examples.avro.serializer.protobuf;

import com.protobuf.Serializers;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Properties;

public class Native_ProducerExample {
  public static void main(String[] args){

    long events = 10;
    String url = "http://localhost:8081";


    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "io.confluent.kafka.serializers.KafkaProtobufSerializer");
    props.put("schema.registry.url", url);

    Serializers.Serializer.SubSerializer.Builder subSerializerBuilder = Serializers.Serializer.SubSerializer.newBuilder();
    subSerializerBuilder.setId(2);
    subSerializerBuilder.setTitle("KafkaProtobufSerializer");
    Serializers.Serializer.SubSerializer subSerializer = subSerializerBuilder.build();

    Serializers.Serializer.Builder serializerBuilder = Serializers.Serializer.newBuilder();

    serializerBuilder.setTitle("KafkaProtobufSerializer");
    serializerBuilder.setClassName("io.confluent.kafka.serializers.KafkaProtobufSerializer");
    serializerBuilder.setId(1);
    serializerBuilder.setType(Serializers.Serializer.Type.Protobuf);
    serializerBuilder.setSubSerializer(subSerializer);
    serializerBuilder.addAllTags(Arrays.asList(1, 2));
    Serializers.Serializer serializer = serializerBuilder.build();

    Producer<String, Serializers.Serializer> producer = new KafkaProducer<>(props);


    for (long nEvents = 0; nEvents < events; nEvents++) {

      ProducerRecord<String, Serializers.Serializer> data = new ProducerRecord<>("ProtobufNative_", serializer);
      producer.send(data);
    }

    producer.close();
  }
}
