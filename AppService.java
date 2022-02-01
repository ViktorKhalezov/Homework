package ru.geekbrains;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class AppService {

    private final ProductRepository productRepository;


    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }


    public Product getProductById(Integer id) {
       return productRepository.getProductById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }


}
