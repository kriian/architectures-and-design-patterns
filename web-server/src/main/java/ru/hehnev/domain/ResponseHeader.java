package ru.hehnev.domain;

public enum ResponseHeader {
    HEADER("Content-Type"),
    HEADER_VALUE("text/html; charset=utf-8");
    private final String content;

    ResponseHeader(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
