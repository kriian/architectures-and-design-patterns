package ru.hehnev.model.repository;

import ru.hehnev.model.entity.Product;

import java.util.*;

public class ProductRepository implements Repository{

    private static final List<Product> products;

    static {
        products = new ArrayList<>() {{
            add(new Product(1L, "Milk", 72));
            add(new Product(2L, "Bread", 46));
            add(new Product(3L, "Cheese", 850));
        }};
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insert(String title, Integer cost) {
        Long incrementIdProduct = (long) (products.size() + 1);
        products.add(new Product(incrementIdProduct, title, cost));
    }

    @Override
    public void update(Long id, String title, Integer cost) {
        Product product = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Продукта с таким id: = " + id + "нет"));
        products.remove(product);
        product.setTitle(title);
        product.setCost(cost);
        products.add(product);
    }

    @Override
    public void delete(Long id) {
        products.removeIf(product -> product.getId().equals(id));

    }

    @Override
    public List<Product> getProducts() {
        products.sort(Comparator.comparingLong(Product::getId));
        return products;

    }
}
