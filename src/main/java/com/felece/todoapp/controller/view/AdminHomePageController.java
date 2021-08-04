package com.felece.todoapp.controller.view;

import com.felece.todoapp.dto.UserDto;
import com.felece.todoapp.entity.User;
import com.felece.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminHomePageController {

    @Autowired
    UserService userService;

    @GetMapping("admin/home")
    public String adminHome(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "adduser";
    }

    @PostMapping("admin/home/adduser")
    @ResponseBody
    public User addUser(@RequestBody UserDto dto){
        User user = userService.addUser(dto);
        return user;
    }

    @DeleteMapping("/admin/home/deleteuser/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}