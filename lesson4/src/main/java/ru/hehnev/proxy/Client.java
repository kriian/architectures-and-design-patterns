package ru.hehnev.proxy;

public class Client {
    public static void main(String[] args) {
        NewsService newsService = new ProxyNewsService();
        System.out.println(newsService.getNews("Чемпион России по футболу 2020"));
    }
}
