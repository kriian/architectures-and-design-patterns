package ru.hehnev;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleRequest implements AutoCloseable {

    private final PrintWriter output;

    public HandleRequest(Socket socket) throws IOException {
        output = new PrintWriter(socket.getOutputStream());
    }

    public PrintWriter getOutput() {
        return output;
    }

    public void statusNotFound() {
        output.println(Constants.NOT_FOUND);
        output.println(Constants.CONTENT_TYPE);
        output.println();
        output.println("<h1>Файл не найден!</h1>");
        output.flush();
    }

    public void statusOk() {
        output.println(Constants.STATUS_OK);
        output.println(Constants.CONTENT_TYPE);
        output.println();
    }

    @Override
    public void close() throws IOException {
        output.close();
    }
}
