package ru.hehnev.service;

import ru.hehnev.logger.Logger;
import ru.hehnev.logger.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class SocketServiceImpl implements SocketService {

    private static final Logger logger = LoggerFactory.createLogger();

    private final Socket socket;

    SocketServiceImpl(Socket socket) {
        this.socket = socket;
    }

    public List<String> readRequest() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), StandardCharsets.UTF_8));

            while (!input.ready());

            List<String> request = new ArrayList<>();
            while (input.ready()) {
                String line = input.readLine();
                logger.info(line);
                request.add(line);
            }
            return request;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void writeResponse(String rewResponse) {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.print(rewResponse);
            output.flush();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }


    @Override
    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }
}
