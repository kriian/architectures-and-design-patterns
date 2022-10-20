package ru.hehnev.controller;

import ru.hehnev.model.entity.Product;

import java.util.List;

public class ProductSerializer {

    public static String json(Product product) {
        return "{" + System.lineSeparator() +
                " " + "\"id\" : " + product.getId() + "," + System.lineSeparator() +
                " " + "\"title\" : \"" + product.getTitle() + "\"" + "," + System.lineSeparator() +
                " " + "\"cost\" : " + product.getCost() + System.lineSeparator() +
                "}";
    }

    public static String json(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(System.lineSeparator());
        for (Product product : products) {
            sb.append(json(product)).append(",").append(System.lineSeparator());
        }
        String s = sb.toString().replaceAll(".$", "");
        return s.concat("]");
    }
}
