package com.artexplorer.proiectpad.service;

import com.artexplorer.proiectpad.model.Museum;
import com.artexplorer.proiectpad.model.User;
import com.artexplorer.proiectpad.model.UserInfo;
import com.artexplorer.proiectpad.model.enUserRole;
import com.artexplorer.proiectpad.repository.UserInfoRepository;
import com.artexplorer.proiectpad.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MuseumService museumService;

    public UserService(UserRepository userRepository, UserInfoRepository userInfoRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MuseumService museumService) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.museumService = museumService;

        User user1 = new User("daniel", "daniel", "daniel@gmail.com", enUserRole.ADMIN);

        User user2 = new User("radu", "radu", "radu@gmail.com", enUserRole.ADMIN);

        User user3 = new User("raul", "raul", "raul@gmail.com", enUserRole.ADMIN);

        String encodedPassword = bCryptPasswordEncoder.encode(user1.getPassword());
        user1.setPassword(encodedPassword);

        encodedPassword = bCryptPasswordEncoder.encode(user2.getPassword());
        user2.setPassword(encodedPassword);

        encodedPassword = bCryptPasswordEncoder.encode(user3.getPassword());
        user3.setPassword(encodedPassword);

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user3 = userRepository.save(user3);

        user1.setUserInfo(userInfoRepository.save(
                new UserInfo(
                        user1.getId(),
                        user1.getUsername(),
                        user1.getEmail(),
                        user1.getUserRole()
                )
        ));
        user2.setUserInfo(userInfoRepository.save(
                new UserInfo(
                        user2.getId(),
                        user2.getUsername(),
                        user2.getEmail(),
                        user2.getUserRole()
                )
        ));
        user3.setUserInfo(userInfoRepository.save(
                new UserInfo(
                        user3.getId(),
                        user3.getUsername(),
                        user3.getEmail(),
                        user3.getUserRole()
                )
        ));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByUsername(username);
    }

    public UserDetails register(User user) {
        boolean usernameTaken = userRepository.findByUsername(user.getUsername()).isPresent();

        if(usernameTaken)
            throw new IllegalStateException("Username taken");

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user = userRepository.save(user);

        user.setUserInfo(userInfoRepository.save(
                new UserInfo(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getUserRole()
                )
        ));
        user = userRepository.save(user);
        return user;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with the username %s not found!", username)));
    }
}
