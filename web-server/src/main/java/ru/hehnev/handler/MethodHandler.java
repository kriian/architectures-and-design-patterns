package ru.hehnev.handler;

import ru.hehnev.domain.HttpRequest;
import ru.hehnev.domain.HttpResponse;
import ru.hehnev.domain.ResponseCode;
import ru.hehnev.serializer.ResponseSerializer;
import ru.hehnev.service.SocketService;

public abstract class MethodHandler {

    protected static final String WWW = "C:\\Users\\maniana\\IdeaProjects\\architectures-and-design-patterns\\web-server\\src\\main\\resources\\www";

    private final String method;
    private final MethodHandler next;
    protected final SocketService socketService;
    protected final ResponseSerializer responseSerializer;


    protected MethodHandler(String method, MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer) {
        this.method = method;
        this.next = next;
        this.socketService = socketService;
        this.responseSerializer = responseSerializer;
    }

    public void handel(HttpRequest request) {
        HttpResponse response;
        if (method.equals(request.getMethod())) {
            response = handelInternal(request);
        } else if (next != null) {
            next.handel(request);
            return;
        } else {
            response = HttpResponse.creatBuilder()
                    .withStatusLine("HTTP/1.1 " + ResponseCode.METHOD_NOT_ALLOWED)
                    .withHeaders("Content-Type", "text/html; charset=utf-8")
                    .withBody("<h1>Метод не поддерживается</h1>")
                    .build();
        }
        socketService.writeResponse(responseSerializer.serialize(response));
    }

    protected abstract HttpResponse handelInternal(HttpRequest request);
}
