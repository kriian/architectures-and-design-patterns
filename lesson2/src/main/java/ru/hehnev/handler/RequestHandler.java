package ru.hehnev.handler;

import ru.hehnev.domain.HttpRequest;
import ru.hehnev.domain.HttpResponse;
import ru.hehnev.domain.ResponseCode;
import ru.hehnev.logger.Logger;
import ru.hehnev.logger.LoggerFactory;
import ru.hehnev.parser.RequestParser;
import ru.hehnev.parser.RequestParserFactory;
import ru.hehnev.serializer.ResponseSerializer;
import ru.hehnev.serializer.ResponseSerializerFactory;
import ru.hehnev.service.SocketService;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RequestHandler implements Runnable {

    private static final String WWW = "C:\\Users\\maniana\\IdeaProjects\\architectures-and-design-patterns\\lesson2\\src\\main\\resources\\www";

    private static final Logger logger = LoggerFactory.createLogger();

    private final SocketService socketService;

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {
        List<String> request = socketService.readRequest();

        // TODO use here implementation of interface RequestParser
        RequestParser requestParser = RequestParserFactory.createRequestParser();
        HttpRequest httpRequest = requestParser.parse(request);
        ResponseSerializer responseSerializer = ResponseSerializerFactory.creatResponseSerializer();

        if (httpRequest.getMethod().equals("GET")) {
            Path path = Paths.get(WWW, httpRequest.getPath());
            if (!Files.exists(path)) {
                // TODO use implementation of interface ResponseSerializer
                var httpResponse = HttpResponse.creatBuilder()
                        .withStatusLine("HTTP/1.1 " + ResponseCode.NOT_FOUND)
                        .withHeaders("Content-Type", "text/html; charset=utf-8")
                        .withBody("<h1>Файл не найден!</h1>\n")
                        .build();
                socketService.writeResponse(responseSerializer.serialize(httpResponse));
                return;
            }

            try {
                // TODO use implementation of interface ResponseSerializer
                StringBuilder sb = new StringBuilder();
                Files.readAllLines(path).forEach(sb::append);
                var httpResponse = HttpResponse.creatBuilder()
                        .withStatusLine("HTTP/1.1 " + ResponseCode.OK)
                        .withHeaders("Content-Type", "text/html; charset=utf-8")
                        .withBody(sb.toString())
                        .build();
                socketService.writeResponse(responseSerializer.serialize(httpResponse));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            var httpResponse = HttpResponse.creatBuilder()
                    .withStatusLine("HTTP/1.1 " + ResponseCode.METHOD_NOT_ALLOWED)
                    .withHeaders("Content-Type", "text/html; charset=utf-8")
                    .withBody("<h1>Метод не поддерживается</h1>")
                    .build();
            socketService.writeResponse(responseSerializer.serialize(httpResponse));
            return;
        }

        logger.info("Client disconnected!");
    }
}
