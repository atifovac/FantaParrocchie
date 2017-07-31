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

    @PostMapping("/new")
    public Long newUser(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email) {
        return userService.newUser(username, password, email);
    }

    @PostMapping("/{username}/update")
    public Long updateUser(@PathVariable("username") String oldUsername,
                           @RequestParam("nuovoUsername") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email) {
        return userService.updateUser(oldUsername, username, password, email);
    }

    @PostMapping("/{username}/delete")
    public void updateUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
    }

    @PostMapping("/login")
    public User login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }
}
