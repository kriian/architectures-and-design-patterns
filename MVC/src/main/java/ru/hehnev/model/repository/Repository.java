package ru.hehnev.model.repository;

import ru.hehnev.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface Repository {
    Optional<Product> findById(Long id);
    void insert(String title, Integer cost);
    void update(Long id, String title, Integer cost);
    void delete(Long id);
    List<Product> getProducts();
}
