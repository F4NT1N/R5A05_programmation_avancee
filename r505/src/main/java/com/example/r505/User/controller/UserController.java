package com.example.r505.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.r505.User.model.User;
import com.example.r505.User.model.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public @ResponseBody String addNewUser(
        @RequestParam String username,
        @RequestParam String password,
        @RequestParam String role) {
        
        User n = new User();
        n.setUsername(username);
        n.setPassword(password);
        n.setRole(Enum.valueOf(com.example.r505.User.model.Role.class, role));
        userRepository.save(n);

        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        if (userRepository.count() == 0) {
            User n1 = new User();
            n1.setUsername("moderator1");
            n1.setPassword("pass1");
            n1.setRole(com.example.r505.User.model.Role.MODERATOR);
            userRepository.save(n1);

            User n2 = new User();
            n2.setUsername("publisher1");
            n2.setPassword("pass2");
            n2.setRole(com.example.r505.User.model.Role.PUBLISHER);
            userRepository.save(n2);
        }
        return userRepository.findAll();
    }
    
    

}
