package com.codewithmosh.Shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor //default constructor
@Builder
@Entity
@ToString
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "bio")
    private String bio;
    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;
    @Column(nullable = false, name = "date_of_birth")
    private String dateOfBirth;

    @Column(nullable = false, name = "loyalty_points")
    private int loyaltyPoints;
    @OneToOne
    @JoinColumn(name = "id") //join column used here as this is the foreign key
    @MapsId //use same column for primary and foreign key
    @ToString.Exclude
    private User user;
}
