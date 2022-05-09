package com.artexplorer.proiectpad.service;

import com.artexplorer.proiectpad.model.User;
import com.artexplorer.proiectpad.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByUsername(username);
    }

    public UserDetails register(User user)
    {
        boolean usernameTaken = userRepository.findByUsername(user.getUsername()).isPresent();

        if(usernameTaken)
            throw new IllegalStateException("Username taken");

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user = userRepository.save(user);

        return user;
    }

    public User findUserByUsername(String username)
    {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with the username %s not found!", username)));
    }
}
