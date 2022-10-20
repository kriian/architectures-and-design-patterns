package ru.hehnev.handler;

import ru.hehnev.serializer.ResponseSerializer;
import ru.hehnev.service.SocketService;

public class MethodHandlerFactory {

    public static MethodHandler create(
            SocketService socketService,
            ResponseSerializer responseSerializer) {


        PutMethodHandler put = new PutMethodHandler("PUT", null, socketService, responseSerializer);
        PostMethodHandler post = new PostMethodHandler("POST", put, socketService, responseSerializer);
        return new GetMethodHandler("GET", post, socketService, responseSerializer);
    }
}
