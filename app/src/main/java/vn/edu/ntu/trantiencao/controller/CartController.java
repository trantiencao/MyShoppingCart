package vn.edu.ntu.trantiencao.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.trantiencao.model.Product;
import vn.edu.ntu.trantiencao.myshoppingcart.R;

public class CartController extends Application implements ICartController {
    List<Product> dsProduct = new ArrayList<>();
    List<Product> shoppingCart = new ArrayList<>();

    public CartController() {
        dsProduct.add(new Product("Áo Gucci Unisex",2499999,"free size", R.drawable.ic_action_addcart_foreground));
        dsProduct.add(new Product("Quân Âu Gucci",2499999,"phong cách", R.drawable.ic_action_addcart_foreground));
        dsProduct.add(new Product("Ví Gucci",1999999,"ví da khủng long", R.drawable.ic_action_addcart_foreground));
    }

    @Override
    public List<Product> getProducts() {
        return dsProduct;
    }

    @Override
    public boolean addToCart(Product product) {
        if(this.shoppingCart.contains(product))
            return false;
        this.shoppingCart.add(product);
        return true;
    }

    @Override
    public List<Product> getShoppingCart() {
        return this.shoppingCart;
    }

    @Override
    public void clearShoppingCart() {
        shoppingCart.clear();
    }
}
