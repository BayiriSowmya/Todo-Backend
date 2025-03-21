package com.iguruu.mydb.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.iguruu.mydb.Dto.TodoDto;
import com.iguruu.mydb.Entity.Todo;
import com.iguruu.mydb.Exception.ResourceNotFoundException;
import com.iguruu.mydb.Mapper.TodoMapper;
import com.iguruu.mydb.Repository.TodoRepository;
import com.iguruu.mydb.Service.TodoService;


@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoRepository todoRepository;
	
	@Override
	public TodoDto addTask(TodoDto todoDto) {
             Todo todo = TodoMapper.maptoEntity(todoDto);
             Todo saved= todoRepository.save(todo);
     		return TodoMapper.mapToDto(saved);
     	}
             
	@Override
	public TodoDto getTaskById(Long taskId) {
		Todo todo = todoRepository.findById(taskId).orElseThrow(
				()->new ResourceNotFoundException("task i not exist with id"+ taskId));
				
	      return TodoMapper.mapToDto(todo);
	}
	

	@Override
	public List<TodoDto> getAllTask() {
		List<Todo> Tasks = todoRepository.findAll();
		return Tasks.stream().map((todo)->TodoMapper.mapToDto(todo)).collect(Collectors.toList());
				
	}

	@Override
	public TodoDto updateTask(Long taskId, TodoDto updatedTask) {
		Todo todo = todoRepository.findById(taskId).orElseThrow(
				() -> new ResourceNotFoundException("task is not exists with given id: " + taskId));
       
        todo.setTitle(updatedTask.getTitle());
        todo.setDescription(updatedTask.getDescription());
        todo.setCompleted(updatedTask.isCompleted());
		
        Todo updatedTaskObj = todoRepository.save(todo);

		return TodoMapper.mapToDto(updatedTaskObj);

		
	}

	@Override
	public void deleteTask(Long taskId) {
		Todo todo = todoRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("task is not exists with given id: " + taskId)
        );

        todoRepository.deleteById(taskId);
    }

		
	}



	


