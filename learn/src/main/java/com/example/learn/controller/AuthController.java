package com.example.learn.controller;

import com.example.learn.model.User;
import com.example.learn.repository.UserRepository;
import com.example.learn.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    final private AuthenticationManager authenticationManager;
    final private UserDetailsService userDetailsService;
    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    @Autowired
    final private JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user)
    {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return ResponseEntity.ok("user registered successfully");
        }
        catch(DataIntegrityViolationException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: username already exists");
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: unable to register user");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user)
    {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());

            Map<String,String> response = new HashMap<>();
            response.put("token", jwt);
            return ResponseEntity.ok(response);
        }
        catch(BadCredentialsException e)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: invalid username or password");
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: unable to authenticate");
        }
    }





}
