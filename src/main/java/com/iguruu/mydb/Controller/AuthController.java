package com.iguruu.mydb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.iguruu.mydb.Dto.LoginDto;
import com.iguruu.mydb.Dto.UserDto;
import com.iguruu.mydb.Service.AuthService;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*") // Allow requests from React frontend
@Validated
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {
        String response = authService.register(userDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto) {
        String response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/check-auth")
    public ResponseEntity<String> checkAuth() {
        return ResponseEntity.ok("Authenticated");
    }
    // Custom validation error handler
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationErrors(jakarta.validation.ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().forEach(violation -> 
            errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
}
