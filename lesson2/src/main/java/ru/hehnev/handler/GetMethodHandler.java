package ru.hehnev.handler;

import ru.hehnev.domain.HttpRequest;
import ru.hehnev.domain.HttpResponse;
import ru.hehnev.domain.ResponseCode;
import ru.hehnev.serializer.ResponseSerializer;
import ru.hehnev.service.SocketService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Handler(order = 0)
public class GetMethodHandler extends MethodHandler {

    private static final String WWW = "C:\\Users\\maniana\\IdeaProjects\\architectures-and-design-patterns\\lesson2\\src\\main\\resources\\www";

    protected GetMethodHandler(
            String method,
            MethodHandler next,
            SocketService socketService,
            ResponseSerializer responseSerializer) {
        super(method, next, socketService, responseSerializer);
    }

    @Override
    protected HttpResponse handelInternal(HttpRequest request) {
        Path path = Paths.get(WWW, request.getPath());
        if (!Files.exists(path)) {
            return HttpResponse.creatBuilder()
                    .withStatusLine("HTTP/1.1 " + ResponseCode.NOT_FOUND)
                    .withHeaders("Content-Type", "text/html; charset=utf-8")
                    .withBody("<h1>Файл не найден!</h1>\n")
                    .build();
        }

        StringBuilder sb = new StringBuilder();
        try {
            Files.readAllLines(path).forEach(sb::append);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return HttpResponse.creatBuilder()
                .withStatusLine("HTTP/1.1 " + ResponseCode.OK)
                .withHeaders("Content-Type", "text/html; charset=utf-8")
                .withBody(sb.toString())
                .build();
    }
}
