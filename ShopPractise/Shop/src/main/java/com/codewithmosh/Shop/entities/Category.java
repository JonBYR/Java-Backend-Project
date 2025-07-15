package com.codewithmosh.Shop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id", nullable = false)
    private Byte id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    public void AddProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }
    public void RemoveProduct(Product product) {
        products.remove(product);
        product.setCategory(null);
    }
}