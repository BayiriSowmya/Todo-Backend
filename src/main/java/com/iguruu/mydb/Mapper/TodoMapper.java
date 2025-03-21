package com.iguruu.mydb.Mapper;

import com.iguruu.mydb.Dto.TodoDto;
import com.iguruu.mydb.Entity.Todo;

public class TodoMapper {

	public static TodoDto mapToDto(Todo todo) {
		return new TodoDto(
				todo.getId(),
				todo.getTitle(),
				todo.getDescription(),
				todo.isCompleted()
				);
				
	}
	
	public static Todo maptoEntity(TodoDto todoDto) {
		return new Todo(
				todoDto.getId(),
				todoDto.getTitle(),
				todoDto.getDescription(),
				todoDto.isCompleted()
				
				);
	}
	
}
