package dev.rakshit.boilerplate.controllers;

import dev.rakshit.boilerplate.models.Result;
import dev.rakshit.boilerplate.models.User;
import dev.rakshit.boilerplate.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static dev.rakshit.boilerplate.utils.ResponseUtil.*;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userID}")
    public ResponseEntity<Result> getUser(@PathVariable UUID userID) {
        log.info("Get user request came for userid : {}", userID);
        return getResponse(
                () -> userService.getUser(userID),
                "No user found with userID : " + userID,
                HttpStatus.OK,
                HttpStatus.NOT_FOUND
        );
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Result> deleteUser(@PathVariable UUID userID) {
        log.info("Delete user request came for userid : {}", userID);
        return getResponse(
                () -> userService.deleteUser(userID),
                "No user found with userID : " + userID,
                HttpStatus.OK,
                HttpStatus.NOT_FOUND
        );
    }

    @GetMapping()
    public ResponseEntity<Result> getUser() {
        log.info("Get users request came");
        return getResponse(
                () -> userService.getUsers(),
                null,
                HttpStatus.OK,
                null
        );
    }

    @PostMapping()
    public ResponseEntity<Result> createUser(@RequestBody User user) {
        String email = user.getEmail();
        log.info("Save user request came for email : {}", email);
        return getResponse(
                () -> userService.createUser(user),
                "Unable to create user with email : " + email,
                HttpStatus.CREATED,
                HttpStatus.BAD_REQUEST
        );
    }

}