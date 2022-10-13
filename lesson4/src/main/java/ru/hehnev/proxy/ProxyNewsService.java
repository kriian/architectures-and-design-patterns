package ru.hehnev.proxy;

import java.util.HashMap;
import java.util.Map;

public class ProxyNewsService implements NewsService {
    private final CNNNewsService cnn = new CNNNewsService();
    private final Map<String, String> news = new HashMap<>();

    @Override
    public String getNews(String newsHeader) {
        if (news.containsKey(newsHeader)) {
            return news.get(newsHeader);
        } else {
            String newNews = cnn.getNews(newsHeader);
            news.put(newsHeader, newNews);
            return newNews;
        }
    }
}
