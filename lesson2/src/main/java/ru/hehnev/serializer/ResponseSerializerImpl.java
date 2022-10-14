package ru.hehnev.serializer;

import ru.hehnev.domain.HttpResponse;

class ResponseSerializerImpl implements ResponseSerializer{

    @Override
    public String serialize(HttpResponse httpResponse) {
        StringBuilder sb = new StringBuilder();
        sb.append(httpResponse.getStatusLine());
        sb.append(System.lineSeparator());
        httpResponse.getHeaders().forEach((key, value) -> {
            sb.append(key).append(": ").append(value);
            sb.append(System.lineSeparator()).append(System.lineSeparator());
        });
        sb.append(httpResponse.getBody());
        sb.append(System.lineSeparator()).append(System.lineSeparator());
        return sb.toString();
    }
}
