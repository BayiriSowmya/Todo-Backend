package com.iguruu.mydb.Service;

import org.springframework.stereotype.Service;

import com.iguruu.mydb.Dto.LoginDto;
import com.iguruu.mydb.Dto.UserDto;

@Service
public interface AuthService {

	String register(UserDto userDto);

	String login(LoginDto loginDto);

}
