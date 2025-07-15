package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.dtos.UserSummary;
import com.codewithmosh.Shop.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    //when using partial dtos objects, need to use the as keyword in query, where as is the name of the variables in the entity
    @EntityGraph(attributePaths = "user")
    @Query("select u.id as id, u.email as email from Profile p, User u where p.loyaltyPoints > :points and p.id = u.id order by u.email")
    List<UserSummary> aboveLoyalty(@Param("points") int points);
}