package com.felece.todoapp.controller.view;

import com.felece.todoapp.dto.TodoDto;
import com.felece.todoapp.dto.UserDto;
import com.felece.todoapp.entity.Todo;
import com.felece.todoapp.entity.User;
import com.felece.todoapp.service.TodosService;
import com.felece.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminView {

    @Autowired
    private UserService userService;
    @Autowired
    private TodosService todosService;

    @GetMapping(path = "users")
    public String findUsersPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("user/todos")
    public String getUserTodo(/*@PathVariable("userid")Long id,*/ Model model) {
        List<Todo> todos = todosService.findAll();
        model.addAttribute("todos", todos);
        return "login";
    }

    private static List<String> rolesList;
    static {
        rolesList = new ArrayList<>();
        rolesList.add("ADMIN");
        rolesList.add("USER");
    }

    @GetMapping(path = "admin/adduser")
    private String getUserForm(Model model) {
        model.addAttribute("roleslist", rolesList);
        return "adduser";
    }

    @PostMapping("admin/adduser")
    public String addUser(@ModelAttribute("userDto") UserDto dto, Model model){
        User user = userService.addUser(dto);
        model.addAttribute("user", user);
        return "adduser";
    }

}
