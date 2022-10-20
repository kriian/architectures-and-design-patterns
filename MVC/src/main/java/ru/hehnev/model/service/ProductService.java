package ru.hehnev.model.service;

import ru.hehnev.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product findById(Long id);
    void insert(String title, Integer cost);
    void update(Long id, String title, Integer cost);
    void delete(Long id);
    List<Product> getProducts();
}
