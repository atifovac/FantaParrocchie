package it.torneodelleparrocchie.fantacalcio.services;

import it.torneodelleparrocchie.fantacalcio.entities.User;

import java.util.List;

/**
 * Created by dsalvatore on 22/06/17.
 */

public interface UserService {

    User getUser(String username);

    List<User> getUserList();

    User newUser(String username, String password, String email);

    User updateUser(String oldUsername, String username, String password, String email);

    void deleteUser(String username);
}
