package ru.geekbrains;

public class Product {
    private int id;
    private String title;
    private double price;


    public Product(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product {" +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", price = " + price +
                '}';
    }
}
