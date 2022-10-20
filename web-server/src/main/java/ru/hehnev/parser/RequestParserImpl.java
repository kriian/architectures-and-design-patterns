package ru.hehnev.parser;

import ru.hehnev.domain.HttpRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RequestParserImpl implements RequestParser {

    int sizeHeader;

    @Override
    public HttpRequest parse(List<String> rawRequest) {
        String[] parts = rawRequest.get(0).split(" ");
        return HttpRequest.createBuilder()
                .withMethod(parts[0])
                .withPath(parts[1])
                .withHeaders(headers(rawRequest))
                .withBody(body(rawRequest))
                .build();
    }

    private Map<String, String> headers(List<String> rawRequest) {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i < rawRequest.size(); i++) {
            if (rawRequest.get(i).equals("")) break;
            String[] parts = rawRequest.get(i).split(": ");
            map.put(parts[0], parts[1]);
        }
        sizeHeader = map.size() + 1;
        return map;
    }

    private String body(List<String> rewRequest) {
        StringBuilder sb = new StringBuilder();
        for (int i = sizeHeader; i < rewRequest.size(); i++) {
            sb.append(rewRequest.get(i));
        }
        return sb.toString();
    }
}
