/**
 The UserService class provides methods for managing user information, including adding new users to the system.
 This class is responsible for encrypting the user's password and saving the user information to the
 database via the UserInfoRepository.
 @Service identifies a service component in the Spring framework. As a service component, it can
 be injected into other Spring-managed components using the @Autowired
 annotation.
 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */
package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * Adds a new user to the UserInfoRepository. The user's password is encoded using the PasswordEncoder.
     * @param userInfo The UserInfo object representing the user to be added.
     */
    public void addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
    }
}
