/**
 The SecurityConfig class is a configuration class that configures Spring Security settings for the
 web application.
 @Configuration indicates that it is a Spring configuration class.
 @EnableWebSecurity is used to enable Spring Security's web security support
 @EnableMethodSecurity is used to enable method security support.
 The class provides several bean methods, including a UserDetailsService bean, a PasswordEncoder
 bean, and an AuthenticationProvider bean, which are used to provide the necessary components for
 Spring Security's authentication and authorization process.
 SecurityFilterChain method is used to filter and secure HTTP requests for the web application.
 It is responsible for authenticating and authorizing incoming requests and ensuring that they
 meet the configured security requirements. This class represents an important part of the
 Spring Security framework, and is responsible  for securing the web application and
 ensuring that it is protected against unauthorized access and other security threats.

 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */

package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    /**
     Creates and returns a new instance of the UserInfoUserDetailsService class.
     @return A UserDetailsService object that is used to retrieve user information.
     */
  @Bean
  public UserDetailsService userDetailsService() {
      return new UserInfoUserDetailsService();
  }
  /**
   Creates and returns a new instance of the BCryptPasswordEncoder class.
   @return A PasswordEncoder object that is used to encode user passwords.
  */
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  /**
    Configures the security filter chain for the application.
   @param http An HttpSecurity object that is used to configure the security filter chain.
   @return A SecurityFilterChain object that represents the security filter chain.
  */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      /**
        Configures the security filter chain for the application.
       @param http An HttpSecurity object that is used to configure the security filter chain.
       @return A SecurityFilterChain object that represents the security filter chain.
       @throws Exception If an error occurs while configuring the security filter chain.
       */
    return
            // Configures the CSRF protection for the application
            http.csrf().disable()
            // Configures the authorization rules for the application
            .authorizeHttpRequests()
            .requestMatchers("/auth/**").permitAll()
            .and()
            .authorizeHttpRequests().requestMatchers("/**").authenticated()
            .and()
            // Configures the form login settings for the application
            .formLogin()
                .loginPage("/login_page")
                .defaultSuccessUrl("/")
                .permitAll()
            .and()
            // Configures the logout settings for the application
            .logout()
            //    .logoutUrl("/logout")
                .logoutSuccessUrl("/login_page")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            .and()
            .exceptionHandling()
            // handles the "FORBIDDEN" page, redirects to custom 403 page
            .accessDeniedPage("/403")
            .and()// Builds the security filter chain
            .build();
  }

  /**
   Creates and returns a new instance of the DaoAuthenticationProvider class.
   @return An AuthenticationProvider object that is used to provide authentication for the application.
  */
  @Bean
  public AuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
      authenticationProvider.setUserDetailsService(userDetailsService());
      authenticationProvider.setPasswordEncoder(passwordEncoder());
      return authenticationProvider;
  }
}
