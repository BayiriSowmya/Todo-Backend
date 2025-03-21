package com.iguruu.mydb.ServiceImpl;

import com.iguruu.mydb.Entity.User;
import com.iguruu.mydb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();

        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            Collections.emptyList() // No roles for now
        );
    }
}
