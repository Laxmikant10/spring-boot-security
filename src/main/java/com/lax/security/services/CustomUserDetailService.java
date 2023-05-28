package com.lax.security.services;

import com.lax.security.Repository.UserRepository;
import com.lax.security.model.CustomUserDetail;
import com.lax.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.getById(username);

        if (user == null) {
            throw new UsernameNotFoundException("No USER");
        }
        return new CustomUserDetail(user);
    }
}
