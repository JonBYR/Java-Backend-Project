package com.codewithmosh.Shop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor //default constructor
@Builder
@Entity
@ToString
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

    @OneToMany(mappedBy = "user")
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
    public void AddTags(String tag) {
        Tag tagObj = new Tag(tag);
        tags.add(tagObj);
        tagObj.getUsers().add(this);
    }
    public void RemoveTags(Tag tag) {
        tags.remove(tag);
        tag.getUsers().remove(this);
    }
    @OneToOne(mappedBy = "user")
    private Profile profile;
}
