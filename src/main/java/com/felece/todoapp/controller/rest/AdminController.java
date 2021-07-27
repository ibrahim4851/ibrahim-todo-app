package com.felece.todoapp.controller.rest;

import com.felece.todoapp.dto.UserDto;
import com.felece.todoapp.entity.User;
import com.felece.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class AdminController {

    @Autowired
    private UserService userService;


    @GetMapping("/admin/users")
    public List<User> findUsers(){
        List<User> users = userService.findAll();
        return users;
    }
    @PostMapping("/admin/users/adduser")
    public User addUser(@RequestBody UserDto dto){
        User user = userService.addUser(dto);
        return user;
    }

    @PutMapping("/admin/updateuser/{userid}")
    @ResponseBody
    public User updateUser(@PathVariable(value = "userid")Long id){
        return userService.updateUser(id);
    }


    @DeleteMapping("/admin/deleteuser/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
