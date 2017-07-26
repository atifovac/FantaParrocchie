package it.torneodelleparrocchie.fantacalcio.controllers;
/**
 * Created by dsalvatore on 22/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.User;
import it.torneodelleparrocchie.fantacalcio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @GetMapping("/")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @PutMapping("/")
    public Long newUser(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email) {
        return userService.newUser(username, password, email);
    }

    @PostMapping("/{oldUsername}")
    public Long updateUser(@PathVariable("oldUsername") String oldUsername,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email) {
        return userService.updateUser(oldUsername, username, password, email);
    }

    @DeleteMapping("/{username}")
    public void updateUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
    }
}
