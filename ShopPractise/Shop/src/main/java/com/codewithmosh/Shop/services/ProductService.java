package com.codewithmosh.Shop.services;

import com.codewithmosh.Shop.entities.Category;
import com.codewithmosh.Shop.entities.Product;
import com.codewithmosh.Shop.repositories.CategoryRepository;
import com.codewithmosh.Shop.repositories.ProductRepository;
import com.codewithmosh.Shop.repositories.SecondProductRepository;
import com.codewithmosh.Shop.repositories.UserRepository;
import com.codewithmosh.Shop.repositories.specifications.ProductSpec;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    public CategoryRepository categoryRepository;
    public ProductRepository productRepository;
    public UserRepository userRepository;
    public SecondProductRepository secondProductRepository;
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

    @Transactional
    public void QueryByExample() {
        var product = new Product();
        product.setName("Apple");

        var matched = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        //matching object specified that any database objects containing Apple will be matched
        var example = Example.of(product, matched); //example is the product object, this will be used to search for all
        //product objects with the name "Apple"
        //optional second field contains the example matcher
        var produce = secondProductRepository.findAll(example);
        produce.forEach(System.out::println);
        //limitation - searching for num values is only exact, cannot query by range
        //no usage with maps, collections or nested values
    }
    public void DynamicQuery()
    {
        var products = secondProductRepository.findProductsByCriteria(null, new BigDecimal(2), new BigDecimal(30));
        products.forEach(System.out::println);
    }
    public void GetProductsBySpecification(String name, BigDecimal min, BigDecimal max) {

        Specification<Product> spec = (root, query, builder) -> null;
        if (name != null) {
            spec.and(ProductSpec.hasName(name)); //adds this predicate to the specification
        }
        if(min != null) {
            spec.and(ProductSpec.PriceGreaterThan(min));
        }
        if(max != null) {
            spec.and(ProductSpec.PriceLessThan(max));
        }
        secondProductRepository.findAll(spec).forEach(System.out::println);
    }
    public void GetSortedProducts() {
        var sort = Sort.by("name").and(Sort.by("price").descending());
        secondProductRepository.findAll(sort).forEach(System.out::println);
    }
    public void GetPagedProducts(int page, int size) { //page is the page number of the database, size is the size of the page
        PageRequest pageRequest = PageRequest.of(page, size); //page starts at zero
        Page<Product> pageData = secondProductRepository.findAll(pageRequest);
        List<Product> products = pageData.getContent();
        products.forEach(System.out::println);
    }
}
