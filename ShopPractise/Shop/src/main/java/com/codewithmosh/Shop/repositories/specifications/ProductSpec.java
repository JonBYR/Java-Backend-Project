package com.codewithmosh.Shop.repositories.specifications;

import com.codewithmosh.Shop.entities.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpec {
    public static Specification<Product> hasName(String name) {
        return (Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) ->
            cb.like(root.get("name"), "%" + name + "%");
    }
    public static Specification<Product> PriceGreaterThan(BigDecimal price) {
        return (Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) ->
                cb.greaterThanOrEqualTo(root.get("price"), price);
    }
    public static Specification<Product> PriceLessThan(BigDecimal price) {
        return (Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) ->
                cb.lessThanOrEqualTo(root.get("price"), price);
    }
}
