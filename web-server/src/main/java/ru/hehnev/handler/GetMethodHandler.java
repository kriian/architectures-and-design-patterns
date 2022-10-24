package ru.hehnev.handler;

import ru.hehnev.config.ConfigFromFile;
import ru.hehnev.domain.HttpRequest;
import ru.hehnev.domain.HttpResponse;
import ru.hehnev.domain.ResponseCode;
import ru.hehnev.domain.ResponseHeader;
import ru.hehnev.serializer.ResponseSerializer;
import ru.hehnev.service.SocketService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Handler(order = 0, method = "GET")
public class GetMethodHandler extends MethodHandler {
    public GetMethodHandler(
            String method,
            MethodHandler next,
            SocketService socketService,
            ResponseSerializer responseSerializer) {
        super(method, next, socketService, responseSerializer);
    }

    @Override
    protected HttpResponse handelInternal(HttpRequest request) {
        Path path = Paths.get(ConfigFromFile.getWWWFile(), request.getPath());
        if (!Files.exists(path)) {
            return HttpResponse.creatBuilder()
                    .withStatusLine("HTTP/1.1 " + ResponseCode.NOT_FOUND)
                    .withHeaders(ResponseHeader.HEADER.getContent(), ResponseHeader.HEADER_VALUE.getContent())
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
                .withHeaders(ResponseHeader.HEADER.getContent(), ResponseHeader.HEADER_VALUE.getContent())
                .withBody(sb.toString())
                .build();
    }
}
