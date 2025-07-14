package com.codewithmosh.Shop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor //default constructor
@Builder
@Entity
@Table(name = "categories")
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;
    @Column(nullable = false, name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<Product>();

    public void AddProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }
    public void RemoveProduct(Product product) {
        products.remove(product);
        product.setCategory(null);
    }

}
