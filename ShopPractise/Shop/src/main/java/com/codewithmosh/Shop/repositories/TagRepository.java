package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.entities.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}