package com.codecool.funstudybackend.controller;


import com.codecool.funstudybackend.entity.ApplicationUser;
import com.codecool.funstudybackend.security.JwtTokenServices;
import com.codecool.funstudybackend.view.UserCredentials;
import com.codecool.funstudybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    public UserController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserRepository users) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> registration(@RequestBody UserCredentials user) {
        ApplicationUser applicationUser = ApplicationUser.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .date(user.getDate())
                .build();

        userRepository.save(applicationUser);
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public Map<Object, Object> login(@RequestBody UserCredentials user) {
//        ApplicationUser userByEmail = userRepository.findUserByEmail(user.getEmail());
//        boolean loggedIn = passwordEncoder.matches(user.getPassword(), userByEmail.getPassword());
//        return ResponseEntity.ok(loggedIn);
        try {
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(user.getEmail(), roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", user.getEmail());
            model.put("roles", roles);
            model.put("token", token);

            return model;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
