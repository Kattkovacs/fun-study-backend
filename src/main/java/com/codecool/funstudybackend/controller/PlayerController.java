package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.entity.ApplicationUser;
import com.codecool.funstudybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin
@RequestMapping("/")
@RestController
public class PlayerController {

    @Autowired
    UserRepository userRepository;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    @GetMapping("players")
    public List<ApplicationUser> getPlayers() throws IOException {
        return userRepository.findApplicationUserByActive(true);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("player/{id}")
    public ResponseEntity<Boolean> deletePlayer(@PathVariable String id) throws IOException {
//        userRepository.deleteById(Long.valueOf(id));
        Optional<ApplicationUser> user = userRepository.findById(Long.valueOf(id));
        if (user.isPresent()) {
            user.get().setActive(false);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
