package ru.hehnev.adapter;

public class AdapterHttpRequest implements Request{
    HttpRequest httpRequest = new HttpRequest();

    @Override
    public void show() {
        httpRequest.showHttpRequest();
    }
}
