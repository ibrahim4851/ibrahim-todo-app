package com.felece.todoapp.controller;



import com.felece.todoapp.dto.UserDto;
import com.felece.todoapp.entity.User;
import com.felece.todoapp.service.CustomUserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AdminController {



    private CustomUserDetailsService userService;

    @GetMapping("/admin/users")
    public void findUsers(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
    }

    @PostMapping("/admin/users/adduser")
    public User addUser(@RequestBody UserDto dto){
        User user = userService.addUser(dto);
        return user;
    }

    @PutMapping("/admin/updateuser/{userid}")
    @ResponseBody
    public void updateUser(@PathVariable("userid")Long id){
        userService.updateUser(id);
    }


    @DeleteMapping("/admin/deleteuser/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
