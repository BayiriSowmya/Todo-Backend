package com.iguruu.mydb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iguruu.mydb.Dto.TodoDto;
import com.iguruu.mydb.Service.TodoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/Todo")
public class TodoController {
	
	@Autowired
	TodoService todoservice;
	
	@PostMapping("/add")
	public ResponseEntity<TodoDto>addTask(@RequestBody TodoDto todoDto){
		TodoDto saved = todoservice.addTask(todoDto);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TodoDto>getTaskById(@PathVariable("id") Long taskId){
		TodoDto todoDto = todoservice.getTaskById(taskId);
		return ResponseEntity.ok(todoDto);
		
	}
	
     @GetMapping("/getAll")
     public ResponseEntity<List<TodoDto>> getAllTask(){
         List<TodoDto> task = todoservice.getAllTask();
         return ResponseEntity.ok(task);
     }
     @PutMapping("/{id}")
     public ResponseEntity<TodoDto> updateTask(@PathVariable("id") Long taskId,
                                                       @RequestBody TodoDto updatedTask){
           TodoDto todoDto = todoservice.updateTask(taskId, updatedTask);
           return ResponseEntity.ok(todoDto);
     }
     
     @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long taskId){
         todoservice.deleteTask(taskId);
         return ResponseEntity.ok("Task deleted successfully!.");
     }
}
