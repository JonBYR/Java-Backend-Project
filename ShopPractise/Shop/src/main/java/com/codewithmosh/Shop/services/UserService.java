package com.codewithmosh.Shop.services;

import com.codewithmosh.Shop.entities.User;
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
}
