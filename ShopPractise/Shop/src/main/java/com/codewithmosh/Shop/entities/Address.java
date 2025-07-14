package com.codewithmosh.Shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor //default constructor
@Builder
@Entity
@Table(name = "addresses")
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "street")
    private String street;

    @Column(nullable = false, name = "city")
    private String city;

    @Column(nullable = false, name = "postcode")
    private String postcode;

    @ManyToOne
    @JoinColumn(name = "userid")
    @ToString.Exclude
    private User user;

}
