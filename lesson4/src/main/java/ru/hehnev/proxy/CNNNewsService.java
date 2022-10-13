package ru.hehnev.proxy;

public class CNNNewsService implements NewsService {

    public void request(String newsHeader) {
        System.out.println("Отправили запрос на получения новостей..." + newsHeader);
    }

    public String response() {
        return "Получили новость!!!";
    }

    @Override
    public String getNews(String newsHeader) {
        request(newsHeader);
        return response();
    }
}
