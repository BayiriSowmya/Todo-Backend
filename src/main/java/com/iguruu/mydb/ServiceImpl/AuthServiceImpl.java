package com.iguruu.mydb.ServiceImpl;

import com.iguruu.mydb.Dto.LoginDto;
import com.iguruu.mydb.Dto.UserDto;
import com.iguruu.mydb.Entity.User;
import com.iguruu.mydb.Repository.UserRepository;
import com.iguruu.mydb.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String register(UserDto userDto) {
        if (userRepository.existsByUserName(userDto.getUserName())) {
            return "Username already taken!";
        }

        User newUser = new User();
        newUser.setUserName(userDto.getUserName());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword())); // ✅ Hashing password
        newUser.setEmail(userDto.getEmail());
        userRepository.save(newUser);

        return "User registered successfully!";
    }

    @Override
    public String login(LoginDto loginDto) {
        Optional<User> userOpt = userRepository.findByUserName(loginDto.getUserName());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // ✅ Fix: Compare hashed password correctly
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                return "Login successful!";
            } else {
                return "Invalid credentials";
            }
        } else {
            return "Invalid credentials";
        }
    }
}
