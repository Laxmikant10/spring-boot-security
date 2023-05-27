package com.lax.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    //Basic Authentication in Spring-boot Security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()

                //Get the cookies XSRF-TOKEN value
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()

                .authorizeRequests()
                // for "/public/**" url it will not ask for username and password
                //.antMatchers("/public/**").permitAll()

                //Only user how have admin role can access the "/public/**" URL
                .antMatchers("/sign-in/**").permitAll()
                .antMatchers("/public/**").hasRole("USER")
                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                //Basic Auth
                //.httpBasic();

                //Form based Auth
                .formLogin()

                //Form based login custom pages
                .loginPage("/sign-in")
                .loginProcessingUrl("/do-login")
                .defaultSuccessUrl("/users/");

        return http.build();
    }

    //Custom username and password
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        List<UserDetails> userDetailsList = new ArrayList<>();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("lax")
                .password("password")
                .roles("USER")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("anu")
                .password("pode")
                .roles("ADMIN")
                .build();

        userDetailsList.add(user);
        userDetailsList.add(user2);

        return new InMemoryUserDetailsManager(userDetailsList);
    }
}
