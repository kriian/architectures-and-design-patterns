package ru.hehnev.controller;

import ru.hehnev.model.entity.Product;
import ru.hehnev.model.service.ProductService;
import ru.hehnev.model.service.ProductServiceImpl;

public class ProductController {

    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductServiceImpl();
    }

    public String getMethodFindById(String request) {
        Product product = productService.findById(Long.parseLong(request.split("/")[2]));
        return ProductSerializer.json(product);
    }

    public String getMethodGetProducts() {
        return ProductSerializer.json(productService.getProducts());
    }

    public void postMethodInsert(String request) {
        String[] elements = request.split("/");
        productService.insert(
                elements[2],
                Integer.parseInt(elements[3]));
    }

    public void putMethodUpdate(String request) {
        String[] elements = request.split("/");
        productService.update(
                Long.parseLong(elements[2]),
                elements[3],
                Integer.parseInt(elements[4]));
    }

    public void deleteMethod(String request) {
        productService.delete(Long.parseLong(request.split("/")[2]));
    }
}
