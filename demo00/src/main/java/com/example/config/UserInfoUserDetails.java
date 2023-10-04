/**
 UserInfoUserDetails is an implementation of the UserDetails interface which represents
 the user details required by Spring Security for authentication and authorization.
 This class is used to map the user information retrieved from the database to a UserDetails
 object. The UserDetails object is used by Spring Security to authenticate and authorize users
 during runtime.

 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */
package com.example.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {
    private final String name;
    private final String password;
    private final List<GrantedAuthority> authorities;
    /**
     * Constructor to create a new instance of UserInfoUserDetails using the provided userInfo.
     * It sets the user's name, password, and authorities (roles) based on the values from the given UserInfo object.
     * @param userInfo an object of the UserInfo class representing user information
     */
    public UserInfoUserDetails(UserInfo userInfo) {
        name=userInfo.getName();
        password=userInfo.getPassword();
        authorities= Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    /**
     * Returns the authorities (roles) granted to the user.
     * @return a collection of GrantedAuthority objects representing the user's roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    /**
     * Returns the user's password.
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return password;
    }
    /**
     * Returns the user's name.
     * @return the user's name
     */
    @Override
    public String getUsername() {
        return name;
    }
    /**
     * Indicates whether the user's account has expired.
     * @return true if the user's account has not expired, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * Indicates whether the user's account is locked.
     * @return true if the user's account is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * Indicates whether the user's credentials (password) have expired.
     * @return true if the user's credentials have not expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * Indicates whether the user is enabled or disabled.
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}