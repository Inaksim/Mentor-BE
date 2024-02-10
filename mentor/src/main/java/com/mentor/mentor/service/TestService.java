package com.mentor.mentor.service;

import com.mentor.mentor.model.User;
import com.mentor.mentor.repo.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public User createNewUser(User user) {
        String hashPas = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPas);
        return userRepository.save(user);
    }

}
