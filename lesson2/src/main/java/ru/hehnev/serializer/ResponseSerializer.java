package ru.hehnev.serializer;


import ru.hehnev.domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse);
}
