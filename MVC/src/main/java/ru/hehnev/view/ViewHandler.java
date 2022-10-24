package ru.hehnev.view;

import ru.hehnev.controller.ProductController;

public class ViewHandler {

    private final ProductController productController;

    public ViewHandler() {
        this.productController = new ProductController();
    }

    public String handler(String request) {
        if (request.startsWith("/findById/")) {
            return productController.getMethodFindById(request);
        } else if (request.startsWith("/getProducts")) {
            return productController.getMethodGetProducts();
        } else if (request.startsWith("/insert/")) {
            productController.postMethodInsert(request);
        } else if (request.startsWith("/update/")) {
            productController.putMethodUpdate(request);
        } else if (request.startsWith("/delete/")) {
            productController.deleteMethod(request);
        }
        return productController.getMethodGetProducts();
    }
}
