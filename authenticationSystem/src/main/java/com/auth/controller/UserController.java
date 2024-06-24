package com.auth.controller;


import com.auth.model.Role;
import com.auth.model.User;
import com.auth.model.UserRole;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
//@CrossOrigin("*")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");

        //encoding password with bcrypt
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("Helpdesk");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user,roles);
    }

    @GetMapping("/getUserByUsername/{username}")
    public User getUserByUsername(@PathVariable String username){
        return this.userService.getUserByUsername(username);
    }

    @DeleteMapping("/deleteByUserId/{userId}")
    public void deleteById(@PathVariable Long userId){
        this.userService.deleteUser(userId);
    }
}

