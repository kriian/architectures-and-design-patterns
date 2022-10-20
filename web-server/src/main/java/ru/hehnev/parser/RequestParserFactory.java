package ru.hehnev.parser;

public class RequestParserFactory {

    private RequestParserFactory() {
    }

    public static RequestParser createRequestParser() {
        return new RequestParserImpl();
    }
}
