package ru.hehnev.handler;

import ru.hehnev.domain.HttpRequest;
import ru.hehnev.domain.HttpResponse;
import ru.hehnev.domain.ResponseCode;
import ru.hehnev.serializer.ResponseSerializer;
import ru.hehnev.service.SocketService;

@Handler(order = 1, method = "POST")
public class PostMethodHandler extends MethodHandler{
    public PostMethodHandler(
            String method,
            MethodHandler next,
            SocketService socketService,
            ResponseSerializer responseSerializer) {
        super(method, next, socketService, responseSerializer);
    }

    @Override
    protected HttpResponse handelInternal(HttpRequest request) {
        return HttpResponse.creatBuilder()
                .withStatusLine("HTTP/1.1 " + ResponseCode.OK)
                .withHeaders("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>POST method handled</h1>")
                .build();
    }
}
