package com.barcodegenerator.barcodegenerator.service;

import com.barcodegenerator.barcodegenerator.exception.UserNotFoundException;
import com.barcodegenerator.barcodegenerator.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
