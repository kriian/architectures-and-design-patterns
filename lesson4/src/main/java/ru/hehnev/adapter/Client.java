package ru.hehnev.adapter;

public class Client {
    public static void main(String[] args) {
        Request request = new AdapterHttpRequest();
        request.show();
    }
}
