package ru.hehnev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InitServer {
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(Constants.port)) {
            PrintStringToConsole.print("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                PrintStringToConsole.print("New client connected! " + socket.toString());

                new Thread(() -> handleRequest(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket socket) {
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             HandleRequest handleRequest = new HandleRequest(socket)) {

            while (!input.ready()) ;

            String firstLine = input.readLine();
            String[] parts = firstLine.split(" ");
            PrintStringToConsole.print(firstLine);
            while (input.ready()) {
                PrintStringToConsole.print(input.readLine());
            }

            Path path = Paths.get(Constants.WWW, parts[1]);
            if (!Files.exists(path)) {
                handleRequest.statusNotFound();
                return;
            }

            handleRequest.statusOk();

            Files.newBufferedReader(path).transferTo(handleRequest.getOutput());

            PrintStringToConsole.print("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
