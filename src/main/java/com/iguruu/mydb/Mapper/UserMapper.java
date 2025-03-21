package com.iguruu.mydb.Mapper;



import com.iguruu.mydb.Dto.UserDto;
import com.iguruu.mydb.Entity.User;

public class UserMapper {

	
	 public static UserDto mapToUserDto(User user) {
	        return new UserDto(
	            user.getId(),
	            user.getFullName(),
	            user.getUserName(),
	            user.getPassword(),
	            user.getEmail()
	            
	        );
	    }

	  
	    public static User mapToUser(UserDto userDto) {
	        return new User(
	            userDto.getId(),
	            userDto.getFullName(),
	            userDto.getUserName(),
	            userDto.getPassword(),
	            userDto.getEmail(),
	            null  
	        );
	    }
}
