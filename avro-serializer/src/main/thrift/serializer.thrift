namespace java com.thrift

enum Type {
        Avro = 1,
		Protobuf = 2,
		Thrift = 3
}

struct SubSerializer {
    1: required i32 id;
    2: required string title;
}

struct Serializer {
    1: required i32 id;
    2: required string title;
    3: required string className;
    4: optional Type type;
    5: optional SubSerializer subSerializer;
    6: optional list<i32> tags;
    7: optional map<string,string> properties;
}

