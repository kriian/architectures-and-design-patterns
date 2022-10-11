package ru.hehnev.domain;

import java.util.Map;

public class HttpRequest {

    private String method;

    private String path;

    private Map<String, String> headers;

    private String body;

    // TODO constructors, getters, setters

    private HttpRequest() {}
    
    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {

        private final HttpRequest request;

        private Builder() {
            this.request = new HttpRequest();
        }

        public Builder withMethod(String method) {
            this.request.method = method;
            return this;
        }

        public Builder withPath(String path) {
            this.request.path = path;
            return this;
        }

        public Builder withHeaders(Map<String, String> headers) {
            this.request.headers = headers;
            return this;
        }

        public Builder withBody(String body) {
            this.request.body = body;
            return this;
        }

        public HttpRequest build() {
            return this.request;
        }
    }
}
