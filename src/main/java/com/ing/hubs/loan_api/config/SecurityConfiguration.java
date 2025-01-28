package com.ing.hubs.loan_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("admin"))
                .authorities(ROLE_ADMIN)
                .build();

        UserDetails user1 = User.builder()
                .username("JohnDoe")
                .password(bCryptPasswordEncoder().encode("1234"))
                .authorities(ROLE_USER)
                .build();

        UserDetails user2 = User.builder()
                .username("JaneSmith")
                .password(bCryptPasswordEncoder().encode("1234"))
                .authorities(ROLE_USER)
                .build();

        UserDetails user3 = User.builder()
                .username("MichaelJohnson")
                .password(bCryptPasswordEncoder().encode("1234"))
                .authorities(ROLE_USER)
                .build();

        UserDetails user4 = User.builder()
                .username("EmilyDavis")
                .password(bCryptPasswordEncoder().encode("1234"))
                .authorities(ROLE_USER)
                .build();

        UserDetails user5 = User.builder()
                .username("WilliamBrown")
                .password(bCryptPasswordEncoder().encode("1234"))
                .authorities(ROLE_USER)
                .build();

        return new InMemoryUserDetailsManager(admin, user1, user2, user3, user4, user5);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Customize authorization as needed
                )
        .formLogin(withDefaults());
        return http.build();
    }

}
