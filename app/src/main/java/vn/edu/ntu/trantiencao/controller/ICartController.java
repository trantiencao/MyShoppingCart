package vn.edu.ntu.trantiencao.controller;

import java.util.List;

import vn.edu.ntu.trantiencao.model.Product;

public interface ICartController {
    List<Product> getProducts();
    boolean addToCart(Product product);
    List<Product> getShoppingCart();
    void clearShoppingCart();
}
