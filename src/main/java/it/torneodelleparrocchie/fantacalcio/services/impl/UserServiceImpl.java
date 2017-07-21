package it.torneodelleparrocchie.fantacalcio.services.impl;
/**
 * Created by dsalvatore on 22/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.User;
import it.torneodelleparrocchie.fantacalcio.repositories.UserRepository;
import it.torneodelleparrocchie.fantacalcio.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String username) {
        return userRepository.findOne(username);
    }

    @Override
    public List<User> getUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User newUser(String username, String password, String email) {
        /*User user = UserSaver.create(null, userRepository)
                .username(username)
                .password(password)
                .email(email)
                .build();
        return userRepository.save(user);*/
        logger.error("not yet implemented");
        return null;
    }

    @Override
    public User updateUser(String oldUsername, String username, String password, String email) {
        User user = userRepository.findOne(oldUsername);
        if (user == null) {
            throw new IllegalArgumentException(String.format("User %s didn't exist", oldUsername));
        }
        /*return UserSaver.create(user, userRepository)
                .username(username)
                .password(password)
                .email(email)
                .build();*/
        logger.error("not yet implemented");
        return null;
    }

    @Override
    public void deleteUser(String username) {
        userRepository.delete(username);
    }
}
