package com.example.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication.dto.JwtResponse;
import com.example.authentication.entity.User;
import com.example.authentication.repository.UserRepository;
import com.example.authentication.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name=" REST APIs for Authentication resourse",
description=" REST APIs- Create Register,Create Login")
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService; // Add this for loading user details

    @Operation(summary = "CREATE Register REST APIs",
    		description="CREATE Register REST APIs used to save register in a database")
    @ApiResponse(
    		responseCode="201",
    		description="HTTP Status 201 CREATED"
    		)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @Operation(summary = "CREATE Login REST APIs",
    		description="CREATE Login REST APIs used to save login in a database")
    @ApiResponse(
    		responseCode="201",
    		description="HTTP Status 201 CREATED"
    		)
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

            // Generate JWT token
            String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
