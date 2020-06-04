package vn.edu.ntu.trantiencao.model;

public class Product {
    String name;
    int price;
    String desc;
    int img;

    public Product(String name, int price, String desc, int img) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public int getImg() {
        return img;
    }
}
