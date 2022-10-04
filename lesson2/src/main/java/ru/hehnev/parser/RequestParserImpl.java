package ru.hehnev.parser;

import ru.hehnev.domain.HttpRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParserImpl implements RequestParser {
    @Override
    public HttpRequest parse(List<String> rawRequest) {
        String[] parts = rawRequest.get(0).split(" ");
        return new HttpRequest() {{
            setMethod(parts[0]);
            setPath(parts[1]);
            setHeaders(headers(rawRequest));
        }};
    }

    private Map<String, String> headers(List<String> rawRequest) {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i < rawRequest.size() - 1; i++) {
            String[] parts = rawRequest.get(i).split(": ");
            map.put(parts[0], parts[1]);
        }
        return map;
    }
}
