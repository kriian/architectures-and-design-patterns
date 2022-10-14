package ru.hehnev.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private int statusCode;
    // TODO
    private String statusLine;

    private Map<String, String> headers;

    private HttpResponse() {}

    public String getStatusLine() {
        return statusLine;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public static Builder creatBuilder() {
        return new Builder();
    }

    public static class Builder {

        private final HttpResponse httpResponse;

        private Builder() {
            this.httpResponse = new HttpResponse();
        }

        public Builder withStatusCode(int statusCode) {
            this.httpResponse.statusCode = statusCode;
            return this;
        }

        public Builder withStatusLine(String statusLine) {
            this.httpResponse.statusLine = statusLine;
            return this;
        }

        public Builder withHeaders(String header, String value) {
            this.httpResponse.headers = new HashMap<>();
            this.httpResponse.headers.put(header, value);
            return this;
        }

        public HttpResponse build() {
            return this.httpResponse;
        }
    }
}
