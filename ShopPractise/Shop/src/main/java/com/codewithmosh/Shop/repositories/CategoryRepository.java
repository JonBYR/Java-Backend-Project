package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}