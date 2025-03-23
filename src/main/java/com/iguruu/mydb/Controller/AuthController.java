package com.iguruu.mydb.Controller;

import com.iguruu.mydb.Config.JwtUtil;
import com.iguruu.mydb.Dto.LoginDto;
import com.iguruu.mydb.Dto.UserDto;
import com.iguruu.mydb.Dto.AuthResponse;
import com.iguruu.mydb.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*") // Allows frontend requests
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * ✅ Register new user
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {
        System.out.println("Received Registration Data: " + userDto); // Debugging
        String response = authService.register(userDto);
        return ResponseEntity.ok(response);
    }

    /**
     * ✅ Login and generate Access & Refresh Tokens
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword())
            );

            // Generate JWT tokens
            String accessToken = jwtUtil.generateToken(loginDto.getUserName(), true);
            String refreshToken = jwtUtil.generateToken(loginDto.getUserName(), false);

            // Return structured response
            AuthResponse authResponse = new AuthResponse(accessToken, refreshToken);
            return ResponseEntity.ok(authResponse);
            
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    /**
     * ✅ Refresh Access Token using Refresh Token
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAccessToken(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken == null || !refreshToken.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Refresh token is missing or invalid");
        }
        
        refreshToken = refreshToken.substring(7); // Remove "Bearer " prefix
        
        if (!jwtUtil.isTokenValid(refreshToken)) {
            return ResponseEntity.status(403).body("Invalid refresh token");
        }

        String username = jwtUtil.extractUsername(refreshToken);
        String newAccessToken = jwtUtil.generateToken(username, true);

        AuthResponse authResponse = new AuthResponse(newAccessToken, refreshToken);
        return ResponseEntity.ok(authResponse);
    }

    /**
     * ✅ Check authentication status
     */
    @GetMapping("/check-auth")
    public ResponseEntity<String> checkAuth() {
        return ResponseEntity.ok("Authenticated");
    }

    /**
     * ✅ Handle validation errors globally
     */
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationErrors(jakarta.validation.ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().forEach(violation ->
            errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
}
