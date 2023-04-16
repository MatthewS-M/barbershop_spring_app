//package com.example.JWT;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//
//    private final JwtAuthenticationFilter jwtAuthFilter;
//    private final AuthenticationProvider authenticationProvider;
//    private final LogoutHandler logoutHandler;
//
//    @Autowired
//    private CustomAuthenticationSuccessHandler successHandler;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/**").permitAll()
////            .requestMatchers("/")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout()
//                .logoutUrl("/auth/logout")
////            .addLogoutHandler(logoutHandler)
////            .logoutUrl("/auth/logout").permitAll()
//                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//        ;
//        return http.build();
//    }
////  @Bean
////  public UserDetailsService userDetailsService() {
////    UserDetails admin =
////            User.withDefaultPasswordEncoder()
////                    .username("Matthew")
////                    .password("192837")
////                    .roles(ApplicationUserRole.ADMIN.name())
////                    .build();
////
////    return new InMemoryUserDetailsManager(admin);
////  }
//}