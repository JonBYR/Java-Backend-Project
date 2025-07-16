package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SecondProductRepository extends JpaRepository<Product, Long>, CriteriaProductRepository, JpaSpecificationExecutor<Product> {
//JpaRepository is needed for matching by example
}
