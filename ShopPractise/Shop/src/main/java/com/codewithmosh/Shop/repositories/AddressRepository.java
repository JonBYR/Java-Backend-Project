package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.entities.Address;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAddressByCityLike(String City);
    List<Address> findTop5ByCityStartingWith(String CityStart);
}