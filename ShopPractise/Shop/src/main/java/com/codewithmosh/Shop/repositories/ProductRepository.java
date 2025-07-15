package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.dtos.ProductSummary;
import com.codewithmosh.Shop.dtos.ProductSummaryClass;
import com.codewithmosh.Shop.entities.Category;
import com.codewithmosh.Shop.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name); //automatically generates a query that will get all from products table
    //@Query("select p from Product where p.price between :min and :max order by p.name") JDQL version
    @Query(value = "select * from products where p.price between :min and :max order by p.name", nativeQuery = true) //Query can be defined by sql query
    List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :id")
    void UpdatePriceByCategory(@Param("newPrice")  BigDecimal newPrice, @Param("id") byte id);
    //no need to use @Query as the method is simple
    List<ProductSummaryClass> findByCategory(Category category); //using ProductSummary means the only data retrieved
    //is the product id and the name
    //using class means that there is added encapsulation, rather than from the interface
    //for custom queries, interface is better as there is less code
}