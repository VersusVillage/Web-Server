package vv.versusvillage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vv.versusvillage.domain.User;
import vv.versusvillage.exception.UserNotFoundException;
import vv.versusvillage.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾지 못했습니다. id: " + id));
    }

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<User> getUserByNickname(@PathVariable String nickname) {
        Optional<User> user = userService.findUserByNickname(nickname);
        return user.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾지 못했습니다: " + nickname));
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        String newUser = userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
