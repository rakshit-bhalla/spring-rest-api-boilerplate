package dev.rakshit.boilerplate.services;

import dev.rakshit.boilerplate.models.User;
import dev.rakshit.boilerplate.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(UUID userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        userOptional.ifPresent(user -> log.info("User get : {}", user));
        return userOptional;
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        log.info("User created : {}", savedUser);
        return savedUser;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
