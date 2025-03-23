package com.iguruu.mydb.ServiceImpl;

import com.iguruu.mydb.Dto.LoginDto;
import com.iguruu.mydb.Dto.UserDto;
import com.iguruu.mydb.Entity.User;
import com.iguruu.mydb.Repository.UserRepository;
import com.iguruu.mydb.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // ✅ Use PasswordEncoder
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ FIX: Change to PasswordEncoder

    @Override
    public String register(UserDto userDto) {
        if (userRepository.existsByUserName(userDto.getUserName())) {
            return "Error: Username is already taken!";
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return "Error: Email is already registered!";
        }
        if (userDto.getFullName() == null || userDto.getFullName().trim().isEmpty()) {
            return "Error: Full name cannot be empty!";
        }

        User newUser = new User();
        newUser.setUserName(userDto.getUserName());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword())); // ✅ Hash password
        newUser.setEmail(userDto.getEmail());
        newUser.setFullName(userDto.getFullName());

        userRepository.save(newUser);
        return "User registered successfully!";
    }

    @Override
    public String login(LoginDto loginDto) {
        Optional<User> userOpt = userRepository.findByUserName(loginDto.getUserName());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                return "Login successful!";
            } else {
                return "Error: Invalid username or password!";
            }
        } else {
            return "Error: Invalid username or password!";
        }
    }
}
