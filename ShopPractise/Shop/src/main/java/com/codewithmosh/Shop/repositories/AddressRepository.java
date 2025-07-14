package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}