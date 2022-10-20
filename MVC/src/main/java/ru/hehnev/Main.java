package ru.hehnev;

import ru.hehnev.view.ViewConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            ViewConsole console = new ViewConsole();

            String request;
            while (!(request = reader.readLine()).equals("/exit")) {
                console.print(request);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
