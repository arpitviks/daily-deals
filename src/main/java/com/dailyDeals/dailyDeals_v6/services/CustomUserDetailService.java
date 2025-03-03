package com.dailyDeals.dailyDeals_v6.services;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.User;
import com.dailyDeals.dailyDeals_v6.repositories.interfaces.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).
                orElseThrow(()->
                        new CustomGlobalException("User not found", false,true)
                );
        List<SimpleGrantedAuthority> authorities = Stream.of(new SimpleGrantedAuthority("ROLE_"+user.getUserRole())).toList();
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getUserRole())
                .authorities(authorities)
                .build();
    }
}
