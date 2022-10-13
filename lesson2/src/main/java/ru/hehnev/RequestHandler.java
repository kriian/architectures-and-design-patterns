package ru.hehnev;

import ru.hehnev.domain.HttpRequest;
import ru.hehnev.domain.HttpResponse;
import ru.hehnev.logger.Logger;
import ru.hehnev.logger.LoggerFactory;
import ru.hehnev.parser.RequestParser;
import ru.hehnev.parser.RequestParserFactory;
import ru.hehnev.serializer.ResponseSerializer;
import ru.hehnev.serializer.ResponseSerializerFactory;

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

        Path path = Paths.get(WWW, httpRequest.getPath());
        ResponseSerializer responseSerializer = ResponseSerializerFactory.creatResponseSerializer();
        if (!Files.exists(path)) {
            // TODO use implementation of interface ResponseSerializer
            var httpResponse = HttpResponse.creatBuilder()
                    .withStatusLine("HTTP/1.1 404 NOT_FOUND")
                    .withHeaders("Content-Type", "text/html; charset=utf-8")
                    .build();
            socketService.writeResponse(
                    responseSerializer.serialize(httpResponse),
                    new StringReader("<h1>Файл не найден!</h1>\n")
            );
            return;
        }

        try {
            // TODO use implementation of interface ResponseSerializer
            var httpResponse = HttpResponse.creatBuilder()
                    .withStatusLine("HTTP/1.1 200 OK")
                    .withHeaders("Content-Type", "text/html; charset=utf-8")
                    .build();
            socketService.writeResponse(
                    responseSerializer.serialize(httpResponse),
                    Files.newBufferedReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Client disconnected!");
    }
}
