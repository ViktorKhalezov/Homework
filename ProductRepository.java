package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {


    private List<Product> products;


    public ProductRepository() {

    }

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1, "car", 5000.0));
        products.add(new Product(2, "computer", 1500.0));
        products.add(new Product(3, "phone", 500.0));
        products.add(new Product(4, "refrigerator", 700.0));
        products.add(new Product(5, "kettle", 35.0));
    }


  public void showAvailableProducts() {
        for(Product product : products) {
            System.out.println(product);
        }
        System.out.println();
    }

    public Product getProductById(int id) {
        for(int i = 0; i < products.size(); i++) {
            if(id == products.get(i).getId()) {
              return products.get(i);
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public boolean containsId(int id) {
        for(Product product : products) {
            if(id == product.getId()) {
                return true;
            }
        }
        return false;
    }

}
