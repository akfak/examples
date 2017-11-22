package examples.avro.serializer.thrif;

import com.thrift.Serializer;
import com.thrift.Type;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Avro_ProducerExample {
  public static void main(String[] args){

    long events = 10;
    String url = "http://localhost:8081";


    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "io.confluent.kafka.serializers.KafkaThriftAvroSerializer");
    props.put("schema.registry.url", url);

    Serializer serializer = new Serializer();
    serializer.setClassName("io.confluent.kafka.serializers.KafkaThriftSerializer");
    serializer.setId(2);
    serializer.setTitle("KafkaThriftSerializer");
    serializer.setType(Type.Avro);

    Producer<String, Serializer> producer = new KafkaProducer<>(props);


    for (long nEvents = 0; nEvents < events; nEvents++) {

      ProducerRecord<String, Serializer> data = new ProducerRecord<>("KafkaThriftAvro", serializer);
      producer.send(data);
    }

    producer.close();
  }
}
