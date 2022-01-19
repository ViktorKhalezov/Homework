package ru.geekbrains;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class Cart {
        private Map<Product, Integer> cart;

        @Autowired
        private ProductRepository repository;


        public Cart(ProductRepository repository) {
            cart = new HashMap<>();
            this.repository = repository;
        }


        public void addProduct(int id) {
            if(containsId(id) == false && repository.containsId(id) == true) {
                cart.put(repository.getProductById(id), 1);
            } else {
                System.out.println("Введен некорректный id");
            }
        }

        public void addAllProducts() {
                List<Product> products = repository.getAllProducts();
                for(int i = 0; i < products.size(); i++) {
                    addProduct(products.get(i).getId());
                }
        }

        public void deleteProduct(int id) {
            if(containsId(id) == true) {
                cart.remove(repository.getProductById(id));
            } else {
                System.out.println("Введен некорректный id");
            }
        }

        public void changeQuantity(int id, int newQuantity) {
            if(containsId(id) == true) {
                cart.put(repository.getProductById(id), newQuantity);
            } else {
                System.out.println("Введен некорректный id");
            }
        }

        public double getSum() {
            double sum = 0.0;
            for(Product product : cart.keySet()) {
                sum += product.getPrice() * cart.get(product);
            }
            return sum;
        }

        public void cleanCart() {
            cart.clear();
        }

        public void showRepositoryProducts() {
            repository.showAvailableProducts();
        }

        public void showProductsInCart() {
            if(cart.size() > 0) {
                for (Product product : cart.keySet()) {
                    System.out.println(product.toString() + " - " + cart.get(product));
                }

            } else {
                System.out.println("Корзина пуста");
            }
            System.out.println();
        }

        public boolean containsId(int id) {
            for(Product product : cart.keySet()) {
                if(id == product.getId()) {
                    return true;
                }
            }
            return false;
        }

}
