package dev.rakshit.boilerplate.controllers;

import dev.rakshit.boilerplate.exceptions.BadRequestException;
import dev.rakshit.boilerplate.exceptions.NotFoundException;
import dev.rakshit.boilerplate.models.User;
import dev.rakshit.boilerplate.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userID}")
    public ResponseEntity<User> getUser(@PathVariable UUID userID) {
        log.info("Get user request came for userid : {}", userID);
        try {
            return userService.getUser(userID)
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElseThrow(() -> new BadRequestException("No user found with userID : " + userID));
        } catch (Exception err) {
            log.error("{} {}", err.getMessage(), err.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
        log.info("Get users request came");
        try {
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception err) {
            log.error("{} {}", err.getMessage(), err.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        String email = user.getEmail();
        log.info("Save user request came for email : {}", email);
        try {
            return Optional.ofNullable(userService.createUser(user))
                    .map(savedUser -> new ResponseEntity<>(savedUser, HttpStatus.CREATED))
                    .orElseThrow(() -> new BadRequestException("Unable to create user with email : " + email));
        } catch (Exception err) {
            log.error("{} {}", err.getMessage(), err.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}