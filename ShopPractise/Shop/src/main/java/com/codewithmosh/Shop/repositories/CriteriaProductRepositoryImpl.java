package com.codewithmosh.Shop.repositories;

import com.codewithmosh.Shop.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository //allows class to be created at runtime
public class CriteriaProductRepositoryImpl implements CriteriaProductRepository {
    //use this only if you want to reduce the number of query methods written, as this allows fields to be null
    //meaning that the query can handle data dynamically
    @PersistenceContext
    private final EntityManager em;
    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal min, BigDecimal max) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class); //this is the part of the query that selects from a table
        List<Predicate> predicates = new ArrayList<>(); //Predicates are a set of conditions
        if (name != null) { //gets name field from product and check if it is like the name field passed into query
            predicates.add(cb.like(root.get("name"), "%" + name + "%"));
        }
        if(min != null) { //gets price field and checks if it is greater than min value passed
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), min));
        }
        if(max != null) { //gets price field and checks if it is less than max value passed
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), max));
        }
        //selects all entities that match the predicate
        cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return em.createQuery(cq).getResultList(); //returns all entities that match the custom query created
    }
}
