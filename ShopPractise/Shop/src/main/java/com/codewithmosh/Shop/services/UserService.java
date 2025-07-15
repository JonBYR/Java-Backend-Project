package com.codewithmosh.Shop.services;

import com.codewithmosh.Shop.entities.Address;
import com.codewithmosh.Shop.entities.Profile;
import com.codewithmosh.Shop.entities.User;
import com.codewithmosh.Shop.repositories.AddressRepository;
import com.codewithmosh.Shop.repositories.ProfileRepository;
import com.codewithmosh.Shop.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager em;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    @Transactional //makes all transactions (save and em.contains) as part of one transactional statement
    public void ShowEntityStates() {
        var user = new User();
        user.setEmail("Guy@guys.com");
        user.setName("Guy");
        user.setPassword("NotVerySecureInnit");
        if (em.contains(user)) {
            System.out.println("User already exists");
        }
        else {
            userRepository.save(user);
        }
    }
    @Transactional
    public void ShowRelatedEntities() {
        var profile = profileRepository.findById(7L).orElseThrow();
        System.out.println("User: " + profile.getUser().getEmail());
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println("Address: " + address.getPostcode());
    }

    public void PersistEntities() {
        var user = new User();
        user.setEmail("address@example.com");
        user.setName("Joel");
        user.setPassword("QuiteSecureInnit");
        var address =  new Address();
        address.setPostcode("TN8 6AA");
        address.setCity("Lincoln");
        address.setStreet("Guy");
        user.AddAddress(address);
        userRepository.save(user);
    }
    @Transactional
    public void DeleteRelated() {
        //userRepository.deleteById(7L); //delete a user
        var user = userRepository.findById(18L).orElseThrow();
        var address = user.getAddress(user.getId());
        if (address != null) {
            user.RemoveAddress(address);
        }
        userRepository.save(user);
    }
    @Transactional
    public void fetchUser() {
        var user = userRepository.findByEmail("address@example.com").orElseThrow();
        System.out.println(user);
    }

    public void fetchWithAddresses() {
        var users =  userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }
    @Transactional
    public void getLoyalty() {
        var profiles =  profileRepository.aboveLoyalty(9);
        for (var profile : profiles) {
            System.out.println(profile.getId() + " " + profile.getEmail());
        }
    }
}
