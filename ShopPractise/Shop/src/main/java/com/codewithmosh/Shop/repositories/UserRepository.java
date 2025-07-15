package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = {"tags", "addresses"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "addresses") //removes a O(N^2) loop from occuring with the N+1 problem
    @Query("select u from User u")
    List<User> findAllWithAddresses();
}
