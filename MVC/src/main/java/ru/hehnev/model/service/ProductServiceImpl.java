package ru.hehnev.model.service;

import ru.hehnev.model.entity.Product;
import ru.hehnev.model.repository.ProductRepository;
import ru.hehnev.model.repository.Repository;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductServiceImpl implements ProductService{
    private final Repository repository;

    public ProductServiceImpl() {
        this.repository = new ProductRepository();
    }


    @Override
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Продукта с таким id: = " + id + "нет"));
    }

    @Override
    public void insert(String title, Integer cost) {
        repository.insert(title, cost);
    }

    @Override
    public void update(Long id, String title, Integer cost) {
        repository.update(id, title, cost);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public List<Product> getProducts() {
        return repository.getProducts();
    }
}
