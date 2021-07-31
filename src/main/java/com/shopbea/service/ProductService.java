package com.shopbea.service;


import com.shopbea.model.Category;
import com.shopbea.model.Product;
import com.shopbea.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Optional getProductById(Long id){
        return productRepository.findById(id);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void removeProductById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getAllProductsByCategoryId(long id){
        return productRepository.findAllByCategory_Id(id);
    }
}
