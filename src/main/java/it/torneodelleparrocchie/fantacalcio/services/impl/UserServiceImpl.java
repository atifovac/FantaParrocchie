package it.torneodelleparrocchie.fantacalcio.services.impl;
/**
 * Created by dsalvatore on 22/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.User;
import it.torneodelleparrocchie.fantacalcio.repositories.UserRepository;
import it.torneodelleparrocchie.fantacalcio.savers.UserSaver;
import it.torneodelleparrocchie.fantacalcio.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Long newUser(String username, String password, String email) {
        logger.info("Creating a new user");
        Long userId = UserSaver.create(null, userRepository)
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .save();
        logger.info("Saved with id {}", userId);
        return userId;
    }

    @Override
    public Long updateUser(String oldUsername, String username, String password, String email) {
        User user = userRepository.findByUsername(oldUsername);
        if (user == null) {
            throw new IllegalArgumentException(String.format("User %s didn't exist", oldUsername));
        }
        return UserSaver.create(user, userRepository)
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .save();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.delete(username);
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || passwordEncoder.matches(password, user.getPassword())) {
            logger.warn("Username o password errati");
            return null;
        }
        return user;
    }
}
