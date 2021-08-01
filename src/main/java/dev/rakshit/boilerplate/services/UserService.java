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

    /**
     * Returns User by taking UserID
     *
     * @param userID UserID of the user
     * @return User
     */
    public User getUser(UUID userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            log.info("User get : {}", user);
            return user;
        }
        return null;
    }

    /**
     * Returns User by adding it into the db
     *
     * @param user User
     * @return User
     */
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        log.info("User created : {}", savedUser);
        return savedUser;
    }

    /**
     * Returns a list of all users
     *
     * @return List of User
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Returns User by deleting it using UserID from the db
     *
     * @param userID UserID of the user
     * @return User
     */
    public User deleteUser(UUID userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.deleteById(userID);
            log.info("User deleted : {}", user);
            return user;
        }
        return null;
    }

}
