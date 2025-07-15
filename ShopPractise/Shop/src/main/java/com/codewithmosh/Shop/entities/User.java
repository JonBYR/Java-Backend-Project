package com.codewithmosh.Shop.entities;

import com.codewithmosh.Shop.repositories.TagRepository;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor //default constructor
@Builder
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true) //use persist to ensure when new user is made in code, address entity is also made
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    public void AddAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }
    public void RemoveAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }
    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default //needed if using Builder annotation
    private Set<Tag> tags = new HashSet<>();
    public void AddTags(String tag, TagRepository tagRepository) {
        Tag tagObj = new Tag(tag);
        tags.add(tagObj);
        tagObj.getUsers().add(this);
        tagRepository.save(tagObj);
    }
    public void RemoveTags(Tag tag) {
        tags.remove(tag);
        tag.getUsers().remove(this);
    }
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Profile profile;
    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns =  @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();
    public void AddProduct(Product product) {
        products.add(product);
    }
    public Address getAddress(long userId) {
        for (Address address : addresses) {
            if (address.getUser().getId() == userId) {
                return address;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "email = " + email + ")";
    }
}
