package ru.hehnev.handler;

import ru.hehnev.domain.HttpRequest;
import ru.hehnev.domain.HttpResponse;
import ru.hehnev.domain.ResponseCode;
import ru.hehnev.domain.ResponseHeader;
import ru.hehnev.serializer.ResponseSerializer;
import ru.hehnev.service.SocketService;

@Handler(order = 2, method = "PUT")
public class PutMethodHandler extends MethodHandler{
    public PutMethodHandler(
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
                .withHeaders(ResponseHeader.HEADER.getContent(), ResponseHeader.HEADER_VALUE.getContent())
                .withBody("<h1>PUT method handled</h1>")
                .build();
    }
}
