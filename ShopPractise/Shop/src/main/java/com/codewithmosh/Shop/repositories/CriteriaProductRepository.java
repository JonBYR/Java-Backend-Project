package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CriteriaProductRepository {
    List<Product> findProductsByCriteria(String name, BigDecimal min, BigDecimal max);
}
