package ru.hehnev.serializer;

import ru.hehnev.domain.HttpResponse;

import java.util.Map;

public class ResponseSerializerImpl implements ResponseSerializer{
    @Override
    public String serialize(HttpResponse httpResponse) {
        StringBuilder sb = new StringBuilder();
        sb.append(httpResponse.getStatusLine());
        sb.append(System.lineSeparator());
        for (Map.Entry<String, String> pair : httpResponse.getHeaders().entrySet()) {
            sb.append(pair.getKey()).append(": ").append(pair.getValue());
            sb.append(System.lineSeparator()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
