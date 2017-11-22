# avro-serializer
Simple examples how to use [protobuf](https://developers.google.com/protocol-buffers/) `com.google.protobuf.GeneratedMessage` and [thrift](https://thrift.apache.org/) `org.apache.thrift.TBase` objects with [confluent schema registry](https://github.com/confluentinc/schema-registry).  
This [enhancement](https://github.com/akfak/schema-registry/tree/3.3.0-post) converts the originals schemata of the originals objects to Avro schemata for compatibility verification.  
   
In order to use it, you should replace `kafka-avro-serializer` dependency with this [schema-registry](https://github.com/akfak/schema-registry/tree/3.3.0-post).
* git clone -b 3.3.0-post https://github.com/akfak/schema-registry.git
* cd schema-registry
* mvn install
* also for [common](https://github.com/akfak/common/tree/3.3.0-post) and [rest-utils](https://github.com/akfak/rest-utils/tree/3.3.0-post). 

### Protobuf
* Use cases:
  * Protobuf serialization with Protobuf object:
    * Use `KafkaProtobufSerializer` and `KafkaProtobufDeserializer`.
    * Example: `Native_ProducerExample` and `Native_ConsumerExample`.
  * Avro serialization with Protobuf object:
    * Use `KafkaProtobufAvroSerializer` and `KafkaProtobufAvroDeserializer`.
    * Example: `Avro_ProducerExample` and `Avro_ConsumerExample`.
  * Serialize Protobuf object with Avro and deserialize it back to Avro object `GenericRecord`:
    * Use `KafkaProtobufAvroSerializer` and `KafkaProtobufAvroDeserializer`.
    * Example: `Avro_ProducerExample` and `Avro_GenericRecord_ConsumerExample`.
* Example classes in `examples.avro.serializer.protobuf`.
* Generate Java class from `serializer.proto` (already generated)  
protoc --java_out=src/main/java/ src/main/protobuf/serializer.proto

### Thrift
* Use cases:
  * Thrift serialization with Thrift object:
    * Use `KafkaThriftSerializer` and `KafkaThriftDeserializer`.
    * Example: `Native_ProducerExample` and `Native_ConsumerExample`.
  * Avro serialization with Thrift object:
    * Use `KafkaThriftAvroSerializer` and `KafkaThriftAvroDeserializer`.
    * Example: `Avro_ProducerExample` and `Avro_ConsumerExample`.
  * Serialize Thrift object with Avro and deserialize it back to Avro object `GenericRecord`:
    * Use `KafkaThriftAvroSerializer` and `KafkaThriftAvroDeserializer`.
    * Example: `Avro_ProducerExample` and `Avro_GenericRecord_ConsumerExample`.
* Example classes in `examples.avro.serializer.thrift`.
* Generate Java class from `serializer.thrift` (already generated)  
thrift -out src/main/java -r --gen java src/main/thrift/serializer.thrift