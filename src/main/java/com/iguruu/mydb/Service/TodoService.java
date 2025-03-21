package com.iguruu.mydb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iguruu.mydb.Dto.TodoDto;

@Service
public interface TodoService {

	TodoDto addTask(TodoDto todoDto);

	TodoDto getTaskById(Long taskId);
	
    List<TodoDto> getAllTask();

	TodoDto updateTask(Long taskId, TodoDto updatedTask);

	void deleteTask(Long taskId);

	

}
