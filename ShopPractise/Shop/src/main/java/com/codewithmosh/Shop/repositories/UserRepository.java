package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
