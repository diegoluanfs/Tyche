package com.tyche.domain.controllers;

import com.tyche.domain.user.User;
import com.tyche.dtos.RespUserDTO;
import com.tyche.dtos.UserCreateDTO;
import com.tyche.dtos.UserUpdateDTO;
import com.tyche.dtos.UserStatusUpdateDTO;
import com.tyche.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateDTO user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> userUpdate(@RequestBody UserUpdateDTO user){
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/status")
    public ResponseEntity<User> userStatusUpdate(@RequestBody UserStatusUpdateDTO user){
        User updatedUser = userService.userStatusUpdate(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RespUserDTO>> getAllUsers(){
        List<RespUserDTO> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id){
        Optional<User> user = this.userService.findById(id);
        return user.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
