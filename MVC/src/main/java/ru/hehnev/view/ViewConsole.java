package ru.hehnev.view;

public class ViewConsole {
    private final ViewHandler viewHandler;

    public ViewConsole() {
        this.viewHandler = new ViewHandler();
    }

    public void print(String request) {
        System.out.println(viewHandler.handler(request));
    }
}
