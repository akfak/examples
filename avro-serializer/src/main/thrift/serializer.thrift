namespace java com.thrift

enum Type {
        Avro = 1,
		Protobuf = 2,
		Thrift = 3
}

struct Serializer {
    1: required i32 id;
    2: required string title;
    3: required string className;
    4: optional Type type = Type.Avro;
}

