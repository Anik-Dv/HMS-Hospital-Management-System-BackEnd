package com.hms.anikdv.code.security;

import com.hms.anikdv.code.entities.User;
import com.hms.anikdv.code.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * In This Class for Load Person Data
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     * @param username
     * @return user
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("CustomUserDetails class : "+ username);
        User user = this.userRepository.findByName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username:! "+username));
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.getRoles().stream()
//                        .map((role)-> new SimpleGrantedAuthority(role.getName()))
//                        .collect(Collectors.toList()));
        return user;
    }
}
