package ru.hehnev.serializer;

public class ResponseSerializerFactory {

    private ResponseSerializerFactory() {}

    public static ResponseSerializer creatResponseSerializer() {
        return new ResponseSerializerImpl();
    }
}
