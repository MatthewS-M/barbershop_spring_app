/**
 This class implements the UserDetailsService interface and is responsible for retrieving the user's details
 based on the provided username. It uses the UserInfoRepository to retrieve the user's details from the database.
 If the user is found, it creates a new instance of UserInfoUserDetails using the retrieved user's information and
 returns it. If the user is not found, it throws a UsernameNotFoundException.

 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */
package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;
    /**
     Loads the user's details based on the provided username.
     @param username the username of the user whose details need to be retrieved
     @return an instance of UserDetails containing the user's details
     @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}