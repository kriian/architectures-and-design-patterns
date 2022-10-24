package ru.hehnev;


import ru.hehnev.handler.*;
import ru.hehnev.logger.Logger;
import ru.hehnev.logger.LoggerFactory;
import ru.hehnev.serializer.ResponseSerializer;
import ru.hehnev.serializer.ResponseSerializerFactory;
import ru.hehnev.service.SocketService;
import ru.hehnev.service.SocketServiceFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class HttpServer {
    private static final Logger logger = LoggerFactory.createLogger();

    public static void main(String[] args) {
        SocketService socketService = null;
        List<Class<?>> classes = ScanningClassesForPresenceAnnotationHandler.scan("ru.hehnev.handler");
        try (ServerSocket serverSocket = new ServerSocket(8088)) {
            logger.info("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");

                socketService = SocketServiceFactory.createSocketService(socket);
                ResponseSerializer responseSerializer = ResponseSerializerFactory.creatResponseSerializer();

                new Thread(new RequestHandler(
                        socketService,
                        AnnotatedHandlerFactory.create(socketService, responseSerializer, classes))
                ).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert socketService != null;
                socketService.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
