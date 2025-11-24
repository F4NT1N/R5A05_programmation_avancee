package com.example.r505.User.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.example.r505.User.model.User;
import com.example.r505.User.model.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> addNewUser(@RequestBody UserRequest userRequest) {
        
        if ( userRequest == null || userRequest.getUsername() == null || userRequest.getPassword() == null || userRequest.getRole() == null) {
            return ResponseEntity.badRequest().body("Invalid user data.");
        }
        User n = new User();
        n.setUsername(userRequest.getUsername());
        n.setPassword(userRequest.getPassword());
        n.setRole(userRequest.getRole());
        userRepository.save(n);

        return ResponseEntity.ok("User saved successfully.");
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable String id, @RequestBody UserRequest userRequest) {
        if ( userRequest == null || userRequest.getUsername() == null || userRequest.getPassword() == null || userRequest.getRole() == null) {
            return ResponseEntity.badRequest().body("Invalid user data.");
        }
        User n = new User();
        userRepository.save(n);
        
        return ResponseEntity.ok("User updated successfully.");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(404).body("User not found.");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
