package com.example.testing.Service;

import com.example.testing.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product saveProduct( Product product);



    Product getProductById(Long id);

    Product updateProduct( Product product);

    void deleteProductById(Long id);

    List<Product> findByKeyword(String keyword);

     List<Product> filteredProducts(String search);

}
