package com.codewithmosh.Shop.services;

import com.codewithmosh.Shop.entities.Category;
import com.codewithmosh.Shop.entities.Product;
import com.codewithmosh.Shop.repositories.CategoryRepository;
import com.codewithmosh.Shop.repositories.ProductRepository;
import com.codewithmosh.Shop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class ProductService {
    public CategoryRepository categoryRepository;
    public ProductRepository productRepository;
    public UserRepository userRepository;
    @Transactional
    public void CreateProduct() {
        var category = new Category();
        category.setName("Fruit");
        category.setId(java.lang.Byte.valueOf("1"));
        var product = new Product();
        product.setName("Apple");
        product.setPrice(new BigDecimal(23));
        product.setCategory(category);
        productRepository.save(product);
        category.AddProduct(product);
    }
    @Transactional
    public void newProduct() {
        var category = categoryRepository.findById(java.lang.Byte.valueOf("1")).orElseThrow();
        var product = new Product();
        product.setName("Pineapple");
        product.setPrice(new BigDecimal(4));
        category.AddProduct(product);
        productRepository.save(product);
    }
    @Transactional
    public void addToWishList() {
        var user = userRepository.findById(18L).orElseThrow();
        var products = productRepository.findAll();
        products.forEach(user::AddProduct);
        userRepository.save(user);
    }
    @Transactional
    public void removeFromWishList() {
        productRepository.deleteById(4L);

    }
    @Transactional //always have this for custom queries
    public void CustomUpdate() {
        productRepository.UpdatePriceByCategory(new BigDecimal(24), (byte)1);
    }

    @Transactional
    public void CategoryFinder() {
        var cat = categoryRepository.findById((byte)1).orElseThrow();
        productRepository.findByCategory(cat);
    }
}
