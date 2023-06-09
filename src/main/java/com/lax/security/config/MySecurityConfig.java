package com.lax.security.config;


import com.lax.security.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    //Basic Authentication in Spring-boot Security
    /*@Bean
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
    }*/

    //Custom username and password
    /*@Bean
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
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
                .antMatchers("/public/**").hasRole("NORMAL")
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
    }


    //Using DB to load the User and use
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication().withUser("anu").password("pode").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("lax").password("lax").roles("USER");*/
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
