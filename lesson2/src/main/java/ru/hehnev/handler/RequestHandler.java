package ru.hehnev.handler;

import ru.hehnev.domain.HttpRequest;
import ru.hehnev.logger.Logger;
import ru.hehnev.logger.LoggerFactory;
import ru.hehnev.parser.RequestParser;
import ru.hehnev.parser.RequestParserFactory;
import ru.hehnev.service.SocketService;

import java.util.List;

public class RequestHandler implements Runnable {

    private static final String WWW = "C:\\Users\\maniana\\IdeaProjects\\architectures-and-design-patterns\\lesson2\\src\\main\\resources\\www";

    private static final Logger logger = LoggerFactory.createLogger();

    private final SocketService socketService;
    private final MethodHandler methodHandler;

    public RequestHandler(SocketService socketService, MethodHandler methodHandler) {
        this.socketService = socketService;
        this.methodHandler = methodHandler;
    }

    @Override
    public void run() {
        List<String> request = socketService.readRequest();

        RequestParser requestParser = RequestParserFactory.createRequestParser();
        HttpRequest httpRequest = requestParser.parse(request);
        methodHandler.handel(httpRequest);


        logger.info("Client disconnected!");
    }
}
